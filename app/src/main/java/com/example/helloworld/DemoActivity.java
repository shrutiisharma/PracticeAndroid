package com.example.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.databinding.ActivityDemoBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    ActivityDemoBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("ListView Demo");

        //setUpSimpleListView();
        setUpCustomTextViewListView();

    }



    /**
     * To setup simple ListView
     */
    private void setUpSimpleListView() {

        //Data : List of Strings
        List<String> courses = new ArrayList<>(Arrays.asList(
                "B.Tech - Biochemical Engineering",
                "B.Tech - Textile Engineering",
                "B.Tech - Ceramic Engineering",
                "B.Tech - Instrumentation Engineering",
                "B.Tech - Mechatronics Engineering",
                "B.Tech - Telecommunication Engineering",
                "B.Tech - Automobile Engineering",
                "B.Tech - Production Engineering",
                "B.Tech - Mining Engineering",
                "B.Tech - Genetic Engineering",
                "Masters of Computer Management",
                "Bachelor of Education",
                "Bachelor of Visual Communication",
                "Bachelor of Design",
                "Bachelor of Financial Markets",
                "Bachelor of Science",
                "B.Sc Actuarial Sciences",
                "B.Sc - Agriculture",
                "Acting and Film-making",
                "B.Sc - Anthropology",
                "B.Sc - Electronics",
                "B.Sc - Geology",
                "B.Sc - Horticulture",
                "B.Sc - Microbiology",
                "B.Sc - Zoology",
                "Bachelor of Physical Education",
                "Bachelor of Audiology & Speech Language Pathology",
                "Master of Law",
                "LL.M - Criminal Law",
                "LL.M - Cyber Law",
                "LL.M - International Law",
                "LL.M - Labour Law",
                "Bachelor of Medicine and Bachelor of Surgery",
                "Bachelor of Optometry"));

        //Create adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this /*Context*/

                //Layout for each Item
                , android.R.layout.simple_list_item_1

                //Data
                , courses);


        //Set the adapter to the list view
        b.list.setAdapter(adapter);
    }



    /**
     * To setup custom TextView ListView
     */
    private void setUpCustomTextViewListView() {

        //Data : List of Strings
        List<String> courses = new ArrayList<>(Arrays.asList(
                "B.Tech - Biochemical Engineering",
                "B.Tech - Textile Engineering",
                "B.Tech - Ceramic Engineering",
                "B.Tech - Instrumentation Engineering",
                "B.Tech - Mechatronics Engineering",
                "B.Tech - Telecommunication Engineering",
                "B.Tech - Automobile Engineering",
                "B.Tech - Production Engineering",
                "B.Tech - Mining Engineering",
                "B.Tech - Genetic Engineering",
                "Masters of Computer Management",
                "Bachelor of Education",
                "Bachelor of Visual Communication",
                "Bachelor of Design",
                "Bachelor of Financial Markets",
                "Bachelor of Science",
                "B.Sc Actuarial Sciences",
                "B.Sc - Agriculture",
                "Acting and Film-making",
                "B.Sc - Anthropology",
                "B.Sc - Electronics",
                "B.Sc - Geology",
                "B.Sc - Horticulture",
                "B.Sc - Microbiology",
                "B.Sc - Zoology",
                "Bachelor of Physical Education",
                "Bachelor of Audiology & Speech Language Pathology",
                "Master of Law",
                "LL.M - Criminal Law",
                "LL.M - Cyber Law",
                "LL.M - International Law",
                "LL.M - Labour Law",
                "Bachelor of Medicine and Bachelor of Surgery",
                "Bachelor of Optometry"));

        //Create adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this /*Context*/

                //Layout for each Item
                , R.layout.list_item

                //Data
                , courses);


        //Set the adapter to the list view
        b.list.setAdapter(adapter);
    }

}
