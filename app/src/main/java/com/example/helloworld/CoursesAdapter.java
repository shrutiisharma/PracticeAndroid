package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.databinding.ListItemBinding;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ProductViewHolder> {

    //For the context
    private Context context;

    //For the list of courses data
    private List<String> courses;

    /**
     * Constructor
     * To initiate the object with
     * @param context context for inflating purpose
     * @param courses list of courses data
     */
    public CoursesAdapter(Context context, List<String> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    //Inflates layout & returns viewHolder for given type
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate layout
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(context), parent, false);

        //Create & return viewHolder
        return new ProductViewHolder(binding);
    }

    @Override
    //Binds data of given position to the view in viewHolder
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.b.textView.setText(courses.get(position));

        //use a class for complex layout data binding & event handling
    }

    @Override
    //Returns the number of data items/size to display
    public int getItemCount() {
        //Return the length of the list
        return courses.size();
    }

    /**
     * ViewHolder
     * Represents view holder for the recycler view
     */
    static class ProductViewHolder extends RecyclerView.ViewHolder{
        //Declare view binding object
        ListItemBinding b;

        /**
         * To give binding to the holder
         * @param b binding of the view
         */
        public ProductViewHolder(ListItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}
