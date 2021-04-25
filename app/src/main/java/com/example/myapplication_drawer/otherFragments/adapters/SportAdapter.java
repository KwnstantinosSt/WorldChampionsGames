package com.example.myapplication_drawer.otherFragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_drawer.R;
import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.classes.Sport;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder>  {

    private ArrayList<Sport> localDataSet = new ArrayList<Sport>();

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
    public SportAdapter(ArrayList<Sport> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        TextView textview = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_view_layout, viewGroup, false);

        return new ViewHolder(textview);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.setText("Κωδικός Αθλήματος: " + localDataSet.get(position).getId() + "\n" + "Όνομα Αθλήματος: " + localDataSet.get(position).getName() + "\n" + "Είδος Αθλήματος: " +  localDataSet.get(position).getKind() + "\n" + "Φύλλο Αθλήματος: " +  localDataSet.get(position).getSex());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
