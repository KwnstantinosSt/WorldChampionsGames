package com.example.myapplication_drawer.otherFragments.gamesFragments;

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
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.firestoreClasses.AtomikaGames;
import com.example.myapplication_drawer.firestoreClasses.OmadikaGames;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesInsertAtomikoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesInsertAtomikoFragment extends Fragment {

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
    public Spinner spinner1;
    public Spinner spinner2;
    public Spinner spinner3;
    LinearLayout layout;
    public ArrayList<String> list1 = new ArrayList<String>();
    public ArrayList<Double> list2 = new ArrayList<Double>();


    public GamesInsertAtomikoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesInsertAtomikoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesInsertAtomikoFragment newInstance(String param1, String param2) {
        GamesInsertAtomikoFragment fragment = new GamesInsertAtomikoFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_games_insert_atomiko, container, false);
        layout = root.findViewById(R.id.container2);

        List<String> sport_names= MainActivity.myDB.mydao().getSportsjoinAtleteNames();
        spinner1 = (Spinner) root.findViewById(R.id.gamesAtomikoSports_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item,sport_names );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        Button btn1 = root.findViewById(R.id.gamesAtomiko_add_athlete);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.card,null);
                Spinner spinner = view.findViewById(R.id.spinner_athlete);
                List<String> sport_names= MainActivity.myDB.mydao().getAthletejoinSportNames(spinner1.getSelectedItem().toString());
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,sport_names );
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);
                layout.addView(view);
            }
        });

        Button submit = root.findViewById(R.id.gamesAtomiko_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertAtomikoGame(root)){
                    Toast.makeText(root.getContext(),"Ο Αγώνας καταχωρήθηκε.",Toast.LENGTH_LONG).show();
                    //Call Notification method
                    createNotification();
                }else{
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                }
            }
        });



        return root;
    }

    public boolean insertAtomikoGame(View root){
        EditText txt9 = root.findViewById(R.id.gamesAtomikoId_txt);
        EditText txt1 = root.findViewById(R.id.gamesAtomikoDate_txt);
        EditText txt2 = root.findViewById(R.id.gamesAtomikoTown_txt);
        EditText txt3 = root.findViewById(R.id.gamesAtomikoCountry_txt);
        Spinner txt4 = root.findViewById(R.id.gamesAtomikoSports_spinner);

       // Toast.makeText(root.getContext(),"layout: " + String.valueOf( layout.getChildCount()),Toast.LENGTH_SHORT).show();

        try {
            for (int i = 0; i < layout.getChildCount(); i++) {
                Spinner spinner = layout.getChildAt(i).findViewById(R.id.spinner_athlete);
                list1.add(spinner.getSelectedItem().toString());
                EditText text = layout.getChildAt(i).findViewById(R.id.epidosi_txt);
                list2.add(Double.parseDouble(text.getText().toString()));
            }
        }catch (Exception e){
            Toast.makeText(root.getContext(),"Δώστε έγκυρη επίδοση",Toast.LENGTH_SHORT).show();
            list2.removeAll(list2);
            list1.removeAll(list1);
            return false;
        }


        for(int j=0;j<list1.size();j++){
            Log.i("test",list1.get(j));
        }


        try{
            if(txt1.getText() != null && txt2.getText() != null && txt3.getText() != null && txt4.getSelectedItem() != null && txt9.getText() != null){
                AtomikaGames atomika = new AtomikaGames();
                atomika.setDate(txt1.getText().toString());
                atomika.setCity(txt2.getText().toString());
                atomika.setCountry(txt3.getText().toString());
                atomika.setSport(txt4.getSelectedItem().toString());
                atomika.setAthletes(list1);
                atomika.setEpidoseis(list2);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("AtomikaGames").document(txt9.getText().toString()).set(atomika).addOnCompleteListener(task -> {
                    Toast.makeText(root.getContext(),"Ο Αγώνας καταχωρήθηκε.",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                });

            }else{return false;}

            txt1.setText(null);
            txt2.setText(null);
            txt3.setText(null);
            txt9.setText(null);
            for(int i=0;i<layout.getChildCount();i++){
                Spinner spinner = layout.getChildAt(i).findViewById(R.id.spinner_athlete);
                spinner.setSelection(0);
                EditText text = layout.getChildAt(i).findViewById(R.id.epidosi_txt);
                text.setText(null);
            }


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
                        getActivity().getApplicationContext().getResources(), R.mipmap.home_background))
                .setContentText("Μόλς έγινε μια νέα εισαγωγή Ατομικού Αγώνα!")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Εισαγωγή Ομαδικού Ατομικού. ")
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