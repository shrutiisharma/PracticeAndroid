package com.example.helloworld.models;

import java.util.ArrayList;
import java.util.List;

public class Course {

    public String name;             //name of course
    public boolean isChecked;       //checkBox
                                    // false by default so only name passed in constructor

    public Course(String name) {
        this.name = name;
    }


    public static List<Course> listFromCoursesStrings(List<String> coursesStrings){
        List<Course> courses = new ArrayList<>();

        for (String courseName : coursesStrings){
            courses.add(new Course(courseName));
        }

        return courses;
    }
}
