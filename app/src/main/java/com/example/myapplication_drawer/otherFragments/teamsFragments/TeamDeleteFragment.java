package com.example.myapplication_drawer.otherFragments.teamsFragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Team;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDeleteFragment extends Fragment {

    //Variables for notification
    private NotificationManager mNotificationManager;
    private NotificationManagerCompat mNotificationManagerCompat;

    String TAG = "myFrag";

    public PendingIntent mDeletePendingIntent;
    private static final int REQUEST_CODE = 2323;

    int NotificationNum = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamDeleteFragment newInstance(String param1, String param2) {
        TeamDeleteFragment fragment = new TeamDeleteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_team_delete, container, false);
        submit = root.findViewById(R.id.team_delete_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteToDBTeam(root)){
                    Toast.makeText(root.getContext(),"Η ομάδα διαγράφτηκε.",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                    //Call Notification method
                    createNotification();
                }
            }
        });
        return root;
    }

    public boolean deleteToDBTeam(View root){
        EditText txt1 = root.findViewById(R.id.team_delete_id_txt);
        try{
            if(txt1.getText() != null ){
                Team team = new Team();
                team.setId(Integer.parseInt(txt1.getText().toString()));
                MainActivity.myDB.mydao().deleteTeam(team);
            }else{return false;}
            txt1.setText(null);

            return  true;
        }catch (Exception ex){
            Log.i("ErrorDB",ex.getMessage());
            return false;
        }
    }

    //Start notification code

    @Override
    public void onResume() {
        super.onResume();
        //updateNumberOfNotifications();
    }

    @Override
    public void onViewCreated(@NonNull View submit, Bundle savedInstanceState) {
        super.onViewCreated(submit, savedInstanceState);
        //for the number of active notifications
        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        //to send notifications and everything else.
        mNotificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());


        // Create a PendingIntent to be fired upon deletion of a Notification.
        Intent deleteIntent = new Intent(MainActivity.ACTION_NOTIFICATION_DELETE);
        mDeletePendingIntent = PendingIntent.getBroadcast(getActivity(),
                REQUEST_CODE, deleteIntent, 0);


        // Supply actions to the button that is displayed on screen.
//
    }


    // Creates an intent that will be triggered when a message is marked as read.
    private Intent getMessageReadIntent(int id) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    }

    // Creates an Intent that will be triggered when a voice reply is received.
    private Intent getMessageReplyIntent(int conversationId) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    }

    void createNotification() {
        // A pending Intent for reads
        PendingIntent readPendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(),
                NotificationNum,
                getMessageReadIntent(NotificationNum),
                PendingIntent.FLAG_UPDATE_CURRENT);

//

        // Building a Pending Intent for the reply action to trigger
        PendingIntent replyIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(),
                NotificationNum,
                getMessageReplyIntent(NotificationNum),
                PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), MainActivity.id)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getActivity().getApplicationContext().getResources(), R.drawable.ic_error))
                .setContentText("Σφάλμα Διαγραφής Ομάδας!")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Σφάλμα Διαγραφής!")
                .setContentIntent(readPendingIntent)
                .setDeleteIntent(mDeletePendingIntent)
                .setChannelId(MainActivity.id)
                .extend(new NotificationCompat.CarExtender()
                        .setColor(ContextCompat.getColor(getContext(), R.color.black))
                );

        mNotificationManagerCompat.notify(NotificationNum, builder.build());
        NotificationNum++;
    }
//End notification code

}