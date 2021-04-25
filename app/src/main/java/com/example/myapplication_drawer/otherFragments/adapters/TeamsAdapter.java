package com.example.myapplication_drawer.otherFragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Team;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private ArrayList<Team> localDataSet = new ArrayList<Team>();

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(TextView itemview) {
            super(itemview);
            textView = itemview;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public TeamsAdapter(ArrayList<Team> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        TextView textview = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_view_layout, viewGroup, false);

        return new TeamsAdapter.ViewHolder(textview);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TeamsAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.setText("Κωδικός Ομάδας: " + localDataSet.get(position).getId() + "\n" + "Όνομα: " + localDataSet.get(position).getName() + "\n" + "Όνομα Γηπέδου: " +  localDataSet.get(position).getField_name() + "\n" + "Πόλη: " +  localDataSet.get(position).getTown() + "\n" + "Χώρα: " +  localDataSet.get(position).getCountry() + "\n" + "Έτος Ίδρυσης: " +  localDataSet.get(position).getDate() + "\n" + "Κωδικός Αθλήματος: " +  localDataSet.get(position).getSport_id());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
