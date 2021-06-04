package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.helloworld.databinding.ListItemBinding;

import java.util.List;

public class CoursesAdapter extends ArrayAdapter<String> {

    //For the context
    private final Context context;

    //For the list of courses data
    private final List<String> courses;

    /**
     * To initiate the object with
     * @param context context for inflating purpose
     * @param resource list item resource
     * @param courses list of courses data
     */
    public CoursesAdapter(@NonNull Context context, int resource, @NonNull List<String> courses) {
        super(context, resource, courses);
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    //Return view after inflating & binding data
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Declare the binding object
        ListItemBinding b;


        //Check for the view availability for reuse

        //Inflated view not available, so inflate.
        if (convertView == null) {
            b = ListItemBinding.inflate(LayoutInflater.from(context));
            convertView = b.getRoot();
        }

        //Inflated view available, so reuse.
        else {
            b = ListItemBinding.bind(convertView);
        }

        //Bind the data
        b.textView.setText(courses.get(position));

        //return
        return convertView;
    }

}
