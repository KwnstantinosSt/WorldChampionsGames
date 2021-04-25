package com.example.myapplication_drawer.otherFragments.gamesFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_drawer.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GamesDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesDeleteFragment newInstance(String param1, String param2) {
        GamesDeleteFragment fragment = new GamesDeleteFragment();
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
        View root = inflater.inflate(R.layout.fragment_games_delete, container, false);
        Button btn1 = root.findViewById(R.id.games_delete_submit);
        Button btn2 = root.findViewById(R.id.game_delete_atomiko_submit);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = root.findViewById(R.id.games_delete_gameid_txt);
                db.collection("OmadikaGames").document(text.getText().toString()).delete().addOnCompleteListener(task -> {
                    Toast.makeText(root.getContext(),"Ο Ομαδικός Αγώνας διγράφτηκε.",Toast.LENGTH_LONG).show();
                    text.setText(null);
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = root.findViewById(R.id.gameID_atomiko_txt);
                db.collection("AtomikaGames").document(text.getText().toString()).delete().addOnCompleteListener(task -> {
                    Toast.makeText(root.getContext(),"Ο Ατομικός Αγώνας διγράφτηκε.",Toast.LENGTH_LONG).show();
                    text.setText(null);
                });
            }
        });

        return root;
    }
}