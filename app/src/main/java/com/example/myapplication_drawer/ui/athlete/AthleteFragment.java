package com.example.myapplication_drawer.ui.athlete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.myapplication_drawer.R;

public class AthleteFragment extends Fragment {

    private AthleteViewModel athleteViewModel;
    private Button insert,update,delete,view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        athleteViewModel =
                new ViewModelProvider(this).get(AthleteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_athlete, container, false);

        insert = root.findViewById(R.id.athete_insert_btn);
        update = root.findViewById(R.id.athete_update_btn);
        delete = root.findViewById(R.id.athete_delete_btn);
        view = root.findViewById(R.id.athete_view_btn);

         insert.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_athlete_to_nav_athlete_insert);
           }
          });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_athlete_to_nav_athlete_update);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_athlete_to_nav_athlete_delete);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_athlete_to_nav_athlete_view);
            }
        });

     
        return root;
    }
}