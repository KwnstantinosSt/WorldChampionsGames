package com.example.myapplication_drawer.otherFragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.firestoreClasses.AtomikaGames;
import com.example.myapplication_drawer.firestoreClasses.OmadikaGames;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GamesAtomikaAdapter extends RecyclerView.Adapter<GamesAtomikaAdapter.ViewHolder> {
    private ArrayList<AtomikaGames> localDataSet = new ArrayList<AtomikaGames>();

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
    public GamesAtomikaAdapter(ArrayList<AtomikaGames> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public GamesAtomikaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        TextView textview = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_view_layout, viewGroup, false);

        return new GamesAtomikaAdapter.ViewHolder(textview);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GamesAtomikaAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ArrayList<String> athletes = localDataSet.get(position).getAthletes();
        ArrayList<Double> epidoseis = localDataSet.get(position).getEpidoseis();
        String output = "Κωδικός Αγώνα: " + localDataSet.get(position).getGameId() + "\n" +"Ημερομηνία Αγώνα: " + localDataSet.get(position).getDate() + "\n" + "Πόλη: " + localDataSet.get(position).getCity() + "\n" + "Χώρα: " +  localDataSet.get(position).getCountry() + "\n" + "Άθλημα: " +  localDataSet.get(position).getSport() + "\n" + "Αθλητές: " + athletes + "\n" + "Επιδόσεις: " + epidoseis;
        viewHolder.textView.setText(output);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
