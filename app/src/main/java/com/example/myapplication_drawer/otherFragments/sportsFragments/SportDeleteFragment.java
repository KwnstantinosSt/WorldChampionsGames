package com.example.myapplication_drawer.otherFragments.sportsFragments;

import android.os.Bundle;

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
import com.example.myapplication_drawer.classes.Sport;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SportDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SportDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SportDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SportDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SportDeleteFragment newInstance(String param1, String param2) {
        SportDeleteFragment fragment = new SportDeleteFragment();
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
        View root = inflater.inflate(R.layout.fragment_sport_delete, container, false);
        submit = root.findViewById(R.id.sport_delete_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteToDBSport(root)){
                    Toast.makeText(root.getContext(),"Το Άθλημα διαγράφτηκε.",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(root.getContext(),"Κάποιο σφάλμα συνέβη.",Toast.LENGTH_LONG).show();
                }
            }
        });
        return  root;
    }

    public boolean deleteToDBSport(View root){
        EditText txt1 = root.findViewById(R.id.sport_delete_id_txt);
        try{
            if(txt1.getText() != null ){
                Sport sport = new Sport();
                sport.setId(Integer.parseInt(txt1.getText().toString()));
                MainActivity.myDB.mydao().deleteSport(sport);
            }else{return false;}
            txt1.setText(null);
            return  true;
        }catch (Exception ex){
            Log.i("ErrorDB",ex.getMessage());
            return false;
        }
    }

}