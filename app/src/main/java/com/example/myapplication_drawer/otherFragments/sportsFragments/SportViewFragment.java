package com.example.myapplication_drawer.otherFragments.sportsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Sport;
import com.example.myapplication_drawer.otherFragments.adapters.SportAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SportViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SportViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SportViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SportViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SportViewFragment newInstance(String param1, String param2) {
        SportViewFragment fragment = new SportViewFragment();
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

    private List<String> names;
    private RecyclerView.LayoutManager layoutManager;
    private List<Sport> sport ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sport_view, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.sport_resycler);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        names = Arrays.asList(getResources().getStringArray(R.array.Greeknames));
        sport = MainActivity.myDB.mydao().getSports();
        SportAdapter adapter = new SportAdapter((ArrayList<Sport>) sport);
        recyclerView.setAdapter(adapter);

        return root;
    }
}