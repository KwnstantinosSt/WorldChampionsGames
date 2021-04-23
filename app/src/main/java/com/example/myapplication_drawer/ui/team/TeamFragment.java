package com.example.myapplication_drawer.ui.team;

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

public class TeamFragment extends Fragment {

    private TeamViewModel teamViewModel;
    private Button btn1,btn2,btn3,btn4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        teamViewModel =
                new ViewModelProvider(this).get(TeamViewModel.class);
        View root = inflater.inflate(R.layout.fragment_team, container, false);


        btn1 = root.findViewById(R.id.team_insert_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_team_to_nav_team_inserts);

            }
        });

        btn2 = root.findViewById(R.id.team_update_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_team_to_teamUpdateFragment);

            }
        });

        btn3 = root.findViewById(R.id.team_delete_btn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_team_to_teamDeleteFragment);

            }
        });

        btn4 = root.findViewById(R.id.team_view_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_nav_team_to_teamViewFragment);

            }
        });


        return root;
    }
}