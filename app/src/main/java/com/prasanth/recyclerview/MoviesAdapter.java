package com.prasanth.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private Context context;
    // Start with first item selected
    public int selectedItem ;
    int selectedPosition=-1;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public LinearLayout R_lay;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            R_lay= (LinearLayout) view.findViewById(R.id.list_item);

//            R_lay.getLayoutParams().width = (int) (Utils.getScreenWidth(itemView.getContext()) / 2);
//            R_lay.getLayoutParams().height = (int) (Utils.getScreenWidth(itemView.getContext()) / 2);

        }
    }


    public MoviesAdapter(Context context,List<Movie> moviesList) {
        this.context=context;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());

        holder.itemView.post(new Runnable()
        {
            @Override
            public void run()
            {

                int cellWidth = holder.itemView.getWidth();// this will give you cell width dynamically
                int cellHeight = holder.itemView.getHeight();// this will give you cell height dynamically

            }
        });

        selectedItem=position;
        if(selectedPosition==position)
            holder.itemView.setBackgroundColor(Color.parseColor("#50000000"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,SampleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) moviesList);
                intent.putExtras(bundle);
                context.startActivity(intent);
//                movie.setSelected(true);
//                removeUnSelectedItem();
            }
        });


    }

    private void setAnimation(View viewToAnimate, int position) {

        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > selectedPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            selectedPosition = position;
        }
    }


    private void removeUnSelectedItem(){
    List<Movie> temMovieList = new ArrayList<>();
    for (Movie movie:moviesList){
        if(movie.isSelected()){
            temMovieList.add(movie);
        }
    }

    moviesList.clear();
    moviesList.addAll(temMovieList);
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
