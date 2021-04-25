package com.example.myapplication_drawer.otherFragments.gamesFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.firestoreClasses.OmadikaGames;
import com.example.myapplication_drawer.otherFragments.adapters.AthleteAdapter;
import com.example.myapplication_drawer.otherFragments.adapters.GamesOmadikaAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<OmadikaGames> omadikaGames = new ArrayList<OmadikaGames>();
    private RecyclerView.LayoutManager layoutManager;

    public GamesViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesViewFragment newInstance(String param1, String param2) {
        GamesViewFragment fragment = new GamesViewFragment();
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
        View root = inflater.inflate(R.layout.fragment_games_view, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("OmadikaGames").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("test", document.getId() + " => " + document.getData());
                        OmadikaGames game = document.toObject(OmadikaGames.class);
                        game.setGameId(document.getId());
                        omadikaGames.add(game);

                    }

                    RecyclerView recyclerView = root.findViewById(R.id.viewRes_games1);
                    layoutManager = new LinearLayoutManager(root.getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    GamesOmadikaAdapter adapter = new GamesOmadikaAdapter(omadikaGames);
                    recyclerView.setAdapter(adapter);
            }else{
                Log.w("test", "Error getting documents.", task.getException());
                }
            }
        });


        return  root;
    }
}