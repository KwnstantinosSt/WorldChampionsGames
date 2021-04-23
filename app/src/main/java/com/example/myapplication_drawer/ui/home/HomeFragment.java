package com.example.myapplication_drawer.ui.home;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication_drawer.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.home_txt1);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //Toast.makeText(root.getContext(),navigationView.getCheckedItem().toString(), Toast.LENGTH_LONG).show();
       // Toast.makeText(root.getContext(),"test",Toast.LENGTH_LONG).show();
        //  AppBarLayout test = root.findViewById(R.id.app_bar);
        //  test.setBackground(new ColorDrawable(getResources().getColor(R.color.mat_black)));

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
      //  Toast.makeText(view.getContext(),"test",Toast.LENGTH_LONG).show();
      //  AppBarLayout test = view.findViewById(R.id.app_bar);
     //   test.setBackground(new ColorDrawable(getResources().getColor(R.color.mat_black)));
    }


}