package com.example.helloworld;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helloworld.databinding.ActivityDemoBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    ActivityDemoBinding b;

    CoursesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        b = ActivityDemoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("RecyclerView Demo");
        
        setUpRecyclerView();

    }

    /**
     * To setup Recycler View
     */
    private void setUpRecyclerView() {

        //Data : List of Strings
        List<String> courses = new ArrayList<>(Arrays.asList(
                "BTech - Biochemical Engineering",
                "BTech - Textile Engineering",
                "BTech - Ceramic Engineering",
                "BTech - Instrumentation Engineering",
                "BTech - Mechatronics Engineering",
                "BTech - Telecommunication Engineering",
                "BTech - Automobile Engineering",
                "BTech - Production Engineering",
                "BTech - Mining Engineering",
                "BTech - Genetic Engineering",
                "Masters of Computer Management",
                "Bachelor of Education",
                "Bachelor of Visual Communication",
                "Bachelor of Design",
                "Bachelor of Financial Markets",
                "Bachelor of Science",
                "BSc Actuarial Sciences",
                "BSc - Agriculture",
                "Acting and Film-making",
                "BSc - Anthropology",
                "BSc - Electronics",
                "BSc - Geology",
                "BSc - Horticulture",
                "BSc - Microbiology",
                "BSc - Zoology",
                "Bachelor of Physical Education",
                "Bachelor of Audiology & Speech Language Pathology",
                "Master of Law",
                "LLM - Criminal Law",
                "LLM - Cyber Law",
                "LLM - International Law",
                "LLM - Labour Law",
                "Bachelor of Medicine and Bachelor of Surgery",
                "Bachelor of Optometry"));

        adapter = new CoursesAdapter(this, courses);

        b.list.setLayoutManager(new LinearLayoutManager(this));

        b.list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView)menu.findItem(R.id.searchIcon).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
