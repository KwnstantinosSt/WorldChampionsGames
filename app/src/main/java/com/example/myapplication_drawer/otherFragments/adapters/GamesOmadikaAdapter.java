package com.example.myapplication_drawer.otherFragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.firestoreClasses.OmadikaGames;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GamesOmadikaAdapter extends RecyclerView.Adapter<GamesOmadikaAdapter.ViewHolder> {
    private ArrayList<OmadikaGames> localDataSet = new ArrayList<OmadikaGames>();

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
    public GamesOmadikaAdapter(ArrayList<OmadikaGames> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public GamesOmadikaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        TextView textview = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_view_layout, viewGroup, false);

        return new GamesOmadikaAdapter.ViewHolder(textview);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GamesOmadikaAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.setText("Ημερομηνία Αγώνα: " + localDataSet.get(position).getDate() + "\n" + "Πόλη: " + localDataSet.get(position).getCity() + "\n" + "Χώρα: " +  localDataSet.get(position).getCountry() + "\n" + "Άθλημα: " +  localDataSet.get(position).getSport() + "\n" + "Ομάδα Α: " +  localDataSet.get(position).getOmadaA() + "\n" + "Ομάδα Β: " +  localDataSet.get(position).getOmadaB() + "\n" + "Σκορ Α: " +  localDataSet.get(position).getScoreA()+ "\n" + "Σκορ Β: " +  localDataSet.get(position).getScoreB());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
