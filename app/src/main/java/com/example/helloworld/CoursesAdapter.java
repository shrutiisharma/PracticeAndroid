package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.databinding.ListItemBinding;
import com.example.helloworld.models.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ProductViewHolder> {

    //For the context
    private final Context context;

    //For the list of courses data
    private final List<Course> courses;
    private List<Course> visibleCourses;


    /**
     * Constructor
     * To initiate the object with
     * @param context context for inflating purpose
     * @param courses list of courses data
     */
    public CoursesAdapter(Context context, List<Course> courses) {
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

        Course course = visibleCourses.get(position);

        //bind name
        holder.b.checkbox.setText(course.name);


        //handle check changes
        holder.b.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                course.isChecked = isChecked;
            }
        });

        //restore previous checked state
        holder.b.checkbox.setChecked(course.isChecked);
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
        List<Course> temp = new ArrayList<>();
        query = query.toLowerCase();

        for (Course course : courses) {
            if (course.name.toLowerCase().contains(query))
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
