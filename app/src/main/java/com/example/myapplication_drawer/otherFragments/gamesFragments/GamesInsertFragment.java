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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.classes.SportTeamJoin;
import com.example.myapplication_drawer.firestoreClasses.OmadikaGames;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesInsertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesInsertFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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
    public ArrayAdapter<String> adapter;


    public GamesInsertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesInsertFragment newInstance(String param1, String param2) {
        GamesInsertFragment fragment = new GamesInsertFragment();
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
        View root =  inflater.inflate(R.layout.fragment_games_insert, container, false);
        List<String> sport_names= MainActivity.myDB.mydao().getSportsNameJoinTeam();
      //  List<String> team_names= MainActivity.myDB.mydao().getTeamNamejoinSport();
        spinner1 = (Spinner) root.findViewById(R.id.spinnerSport);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner) root.findViewById(R.id.spinnerOmadaA);
        spinner3 = (Spinner) root.findViewById(R.id.spinnerOmadaB);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item,sport_names );
  //      ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item,team_names );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   //     adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
   //     spinner2.setAdapter(adapter2);
   //     spinner3.setAdapter(adapter2);

        Button submit = root.findViewById(R.id.games_insert_omadiko_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertOmadikoGame(root)){
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

    public boolean insertOmadikoGame(View root){
        EditText txt1 = root.findViewById(R.id.games_insert_omadiko_date_txt);
        EditText txt2 = root.findViewById(R.id.games_insert_omadiko_town_txt);
        EditText txt3 = root.findViewById(R.id.games_insert_omadiko_country_txt);
        Spinner txt4 = root.findViewById(R.id.spinnerSport);
        Spinner txt5 = root.findViewById(R.id.spinnerOmadaA);
        Spinner txt6 = root.findViewById(R.id.spinnerOmadaB);
        EditText txt7 = root.findViewById(R.id.games_insert_omadiko_skorA_txt);
        EditText txt8 = root.findViewById(R.id.games_insert_omadiko_skorB_txt);
        EditText txt9 = root.findViewById(R.id.gamesOmadikoID);
        try{
            if(txt1.getText() != null && txt2.getText() != null && txt3.getText() != null && txt4.getSelectedItem() != null && txt5.getSelectedItem() != null && txt6.getSelectedItem() != null && txt7.getText() != null  && txt8.getText() != null){
                OmadikaGames omadika = new OmadikaGames();
                omadika.setDate(txt1.getText().toString());
                omadika.setCity(txt2.getText().toString());
                omadika.setCountry(txt3.getText().toString());
                omadika.setSport(txt4.getSelectedItem().toString());
                omadika.setOmadaA(txt5.getSelectedItem().toString());
                omadika.setOmadaB(txt6.getSelectedItem().toString());
                omadika.setScoreA(Integer.parseInt(txt7.getText().toString()));
                omadika.setScoreB(Integer.parseInt(txt8.getText().toString()));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
            /*    db.collection("OmadikaGames").add(omadika).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(root.getContext(),"Ο Αγώνας καταχωρήθηκε.",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                });*/

                db.collection("OmadikaGames").document(txt9.getText().toString()).set(omadika).addOnCompleteListener(task -> {
                    Toast.makeText(root.getContext(),"Ο Αγώνας καταχωρήθηκε.",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                });


            }else{return false;}
            txt1.setText(null);
            txt2.setText(null);
            txt3.setText(null);
            txt4.setSelection(0);
            txt5.setSelection(0);
            txt6.setSelection(0);
            txt7.setText(null);
            txt8.setText(null);
            txt9.setText(null);
            return  true;
        }catch (Exception ex){
            Log.i("ErrorDB",ex.getMessage());
            return false;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            String selectedItem = String.valueOf(parent.getItemAtPosition(position));
            List<String> team_names= MainActivity.myDB.mydao().getTeamNamejoinSport(selectedItem);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,team_names );
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner3.setAdapter(adapter2);

        }catch (Exception e){
            Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                .setContentText("Μόλς έγινε μια νέα εισαγωγή Ομαδικού Αγώνα!")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Εισαγωγή Ομαδικού Ομαδικού.")
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