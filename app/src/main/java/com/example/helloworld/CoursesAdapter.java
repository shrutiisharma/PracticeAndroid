package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ProductViewHolder> {

    //For the context
    private final Context context;

    //For the list of courses data
    private final List<String> courses;
    private List<String> visibleCourses;


    /**
     * Constructor
     * To initiate the object with
     * @param context context for inflating purpose
     * @param courses list of courses data
     */
    public CoursesAdapter(Context context, List<String> courses) {
        this.context = context;
        this.courses = courses;
        this.visibleCourses = courses;
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
        holder.b.textView.setText(visibleCourses.get(position));

        //use a class for complex layout data binding & event handling
    }

    @Override
    //Returns the number of data items/size to display
    public int getItemCount() {
        //Return the length of the list
        return visibleCourses.size();
    }



    /**
     * To filter the list
     * @param query for search action
     */
    public void filter(String query) {

        //No query, show all items
        if (query.trim().isEmpty()){
            visibleCourses = courses;
            notifyDataSetChanged();
            return;
        }

        //filter & add to visibleCourses
        List<String> temp = new ArrayList<>();
        query = query.toLowerCase();

        for (String course : courses) {
            if (course.toLowerCase().contains(query))
                temp.add(course);
        }

        visibleCourses = temp;

        //Refresh list
        notifyDataSetChanged();
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
