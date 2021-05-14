//package com.example.myapplication_drawer.ui.Querys;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.navigation.Navigation;
//
//import com.example.myapplication_drawer.R;
//import com.example.myapplication_drawer.ui.athlete.AthleteViewModel;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link QuerysFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class QuerysFragment extends Fragment {
//
//
//
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public QuerysFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment QuerysFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static QuerysFragment newInstance(String param1, String param2) {
//        QuerysFragment fragment = new QuerysFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    Button button1;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    private QuerysViewModel querysViewModel;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        querysViewModel =
//                new ViewModelProvider(this).get(QuerysViewModel.class);
//              // Inflate the layout for this fragment
//        View root =  inflater.inflate(R.layout.fragment_querys, container, false);
//
//        button1 = root.findViewById(R.id.query1);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_querysFragment_to_athleteMaleNamesFragment);
//            }
//        });
//
//
//
//        return root;
//    }
//
//}
package com.example.myapplication_drawer.ui.Querys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication_drawer.MainActivity;
import com.example.myapplication_drawer.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuerysFragment extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView querytextView, querytextresult;
    Button bnqueryrun;
    int test;
    public QuerysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_querys, container, false);
        final String[] queryArray = getResources().getStringArray(R.array.queries_description_array);
        querytextView = view.findViewById(R.id.txtquery);
        spinner = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                querytextView.setText(queryArray[position]);
                test = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        querytextresult = view.findViewById(R.id.txtqueryresult);
        bnqueryrun = view.findViewById(R.id.queryrun);
        bnqueryrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querytextresult.setText("test" + test);
                String result ="";
                switch (test){
                    case 1:
                        List<String> strings1 = MainActivity.myDB.mydao().getMalesName();
                        for (String i: strings1) {
                            result = result + "\n Athlete's Name: " + i + "\n";
                        }
                        querytextresult.setText(result);
                        break;

                    case 2:
                        List<String> strings2 = MainActivity.myDB.mydao().getNameTeamCountry();
                        for (String i: strings2) {
                            result = result + "\nName From Greek Teams: " + i + "\n";
                        }
                        querytextresult.setText(result);
                        break;

                    case 3:
                        List<String> strings3 = MainActivity.myDB.mydao().getSoloAthleteName();
                        for (String i: strings3) {
                            result = result + "\nName From Greek Teams: " + i + "\n";
                        }
                        querytextresult.setText(result);
                        break;

                    case 4:
                        List<String> strings4 = MainActivity.myDB.mydao().getAthleteNameGreekTeams();
                        for (String i: strings4) {
                            result = result + "\n Athletes Names From Greek Teams: " + i + "\n";
                        }
                        querytextresult.setText(result);
                        break;

                         case 5:
                            List<String> strings5 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown1();
                            List<String> strings6 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown2();
                            List<String> strings7 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown3();
                             List<String> strings8 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown4();
                             List<String> strings9 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown5();
                             List<String> strings10 = MainActivity.myDB.mydao().getAthleteNameCountryAndTown6();
                            for (String i: strings5 ) {
                                for (String j: strings6){
                                    for (String k: strings7){
                                        for (String x: strings8){
                                            for (String o: strings9){
                                                for (String p: strings10){
                                                    result = result + "\n Greek Athletes Names And Towns: "+ "\n Athlete Name: " + i
                                                            + "\n Team Country: " + j + "\n Team Touwn: "+ k +
                                                            "\n Sport Kind: " + x + "\n Spore Name:"+ o + "\n Team Name: "+ p + "\n";
                                                }

                                            }

                                        }
                                    }
                                }
                            }

                            querytextresult.setText(result);
                            break;


//                    case 6:
//                        List<ResultStringInt> resultStringInts1 = MainActivity.myDB.mydao().getasdad();
//                        for (ResultStringInt i: resultStringInts1) {
//                            String team_name = i.getField1();
//                            String athlete_name = i.getField2();
//                            String team_country = i.getField3();
//
//                            result = result + "\n Sailor's Name: " + team_name + "\n Id: " + athlete_name + "\n"+ team_country + "\n";
//                        }
//                        querytextresult.setText(result);
//                        break;
                }
            }
        });
        return view;
    }
}
