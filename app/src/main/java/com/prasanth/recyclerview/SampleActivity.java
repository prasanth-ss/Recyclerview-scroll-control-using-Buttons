package com.prasanth.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Prasanth on 5/11/2017.
 */

public class SampleActivity extends AppCompatActivity{

    private ArrayList<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

//        Bundle bundle = getIntent().getExtras();
        Bundle bundle = getIntent().getExtras();
//        list = bundle.getParcelableArrayList("data");
        list = (ArrayList<Movie>) bundle.getSerializable("data");
        Log.e("list", "onCreate: " + list.toString());
    }


    }





