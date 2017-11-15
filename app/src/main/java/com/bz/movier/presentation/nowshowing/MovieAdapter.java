package com.bz.movier.presentation.nowshowing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bz.movier.R;
import com.bz.movier.common.util.ImageUtil;
import com.bz.movier.common.util.NullUtil;
import com.bz.movier.common.util.UtilConverter;
import com.bz.movier.data.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private List<Movie> mMovies;
    private Context mContext;
    private OnMovieClickedListener mListener;

    public MovieAdapter(List<Movie> movies, OnMovieClickedListener listener) {
        this.mMovies = movies;
        mListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext().getApplicationContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent,false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        int imgLikeId = movie.isLike() ? R.drawable.ic_favorite_black : R.drawable.ic_favorite_border_black;
        holder.tvLikeCount.setCompoundDrawablesWithIntrinsicBounds(imgLikeId, 0, 0, 0);

        if (!NullUtil.isNullOrEmpty(movie.getPosterPath())) {
            String url = UtilConverter.convertImageUrl(movie.getPosterPath());
            ImageUtil.showImage(mContext, holder.imgMovie, holder.progress, url);
        }
        holder.tvLikeCount.setText(String.valueOf(movie.getVoteCount()));
        holder.tvLikeCount.setOnClickListener(v -> {
            movie.setLike(movie.isLike() ? false : true);
            notifyItemChanged(position);
            mListener.onLikeClicked(movie);
        });
    }

    @Override
    public int getItemCount() {
        return NullUtil.isNullOrEmpty(mMovies) ? 0 : mMovies.size();
    }

    public void replaceData(List<Movie> movies) {
        this.mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void addData(List<Movie> movies) {
        int curPosition = getItemCount();
        mMovies.addAll(movies);
        notifyItemRangeChanged(curPosition, movies.size());
    }

    public void updateItem(Movie movie) {
        for (int i = 0 ;i < mMovies.size(); i++){
            if (movie.getId() == mMovies.get(i).getId()){
                mMovies.set(i,movie);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public List<Movie> getList() {
        return mMovies;
    }

    public boolean isEmpty() {
        return NullUtil.isNullOrEmpty(mMovies);
    }

    public interface OnMovieClickedListener{
        void onLikeClicked(Movie movie);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvLikeCount)
        TextView tvLikeCount;
        @BindView(R.id.progress)
        ProgressBar progress;
        @BindView(R.id.imgMovie)
        ImageView imgMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}