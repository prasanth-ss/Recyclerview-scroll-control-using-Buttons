package com.prasanth.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public  List<Movie> movieListAll=new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();

    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private static int displayedposition = 0;
    private Button up_btn,down_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        up_btn = (Button) findViewById(R.id.up_btn);
        down_btn = (Button) findViewById(R.id.down_btn);
        RelativeLayout R_lay=(RelativeLayout) findViewById(R.id.rec_layout);

        mAdapter = new MoviesAdapter(this,movieList);

        recyclerView.setHasFixedSize(true);
         final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
//        llm.scrollToPositionWithOffset(displayedposition , movieList.size());
//        mLayoutManager.scrollToPositionWithOffset(2, 20);
//        recyclerView.scrollToPosition();
        recyclerView.setAdapter(mAdapter);
//
//        up_btn.setVisibility(View.GONE);
//        down_btn.setVisibility(View.GONE);

          int totalItemCount = mLayoutManager.getItemCount();
        int visibleItemCount = mLayoutManager.getChildCount();
        Log.e("main", "count:   visibleItemCount "+ visibleItemCount);
        Log.e("main", "count: totalItemCount  "+ totalItemCount);


        if(totalItemCount > visibleItemCount  ) {
            up_btn.setVisibility(View.VISIBLE);
            down_btn.setVisibility(View.VISIBLE);
        }
//        int count= mAdapter.getItemCount();
//        Log.e("main", "count: "+ count );



        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(mLayoutManager.findLastVisibleItemPosition()-1);
//                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
//                {
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//                    {
//                        if(dy > 0) //check for scroll down
//                        {
//                            int visibleItemCount = mLayoutManager.getChildCount();
//                            int totalItemCount = mLayoutManager.getItemCount();
//                            int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
//
//                            Log.e("main", "count:   visibleItemCount "+ visibleItemCount);
//                            Log.e("main", "count: totalItemCount  "+ totalItemCount);
//                            Log.e("main", "count: pastVisiblesItems "+ pastVisiblesItems);
//                            if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
//                            {
//
//                                Log.e("...", "Last Item Wow !");
//                                //Do pagination.. i.e. fetch new data
//                            }
//
//                        }
//                    }
//                });

//                recyclerView.);
//                recyclerView.smoothScrollToPosition(mLayoutManager.size() - 1);
            }
        });

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(mLayoutManager.findFirstVisibleItemPosition()+1);
            }
        });





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);

//               int count= mAdapter.getItemCount();
//                int positions=mAdapter.selectedItem;
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), positions + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }

    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

//        movie.setAllitems(movieList);
             movieListAll.addAll(movieList);
        mAdapter.notifyDataSetChanged();
    }

}
