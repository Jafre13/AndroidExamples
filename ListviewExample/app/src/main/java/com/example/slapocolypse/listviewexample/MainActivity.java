package com.example.slapocolypse.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lw;
    CustomAdapter adapter;
    ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        list = new ArrayList<>();

        list.add(new Item("Jakob",1));
        list.add(new Item("Mads",2));
        list.add(new Item("Erik",3));
        lw = (ListView) findViewById(R.id.random_list);
        adapter = new CustomAdapter(this,R.layout.item_layout,list);
        lw.setAdapter(adapter);




    }
}
