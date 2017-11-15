package com.bz.movier.presentation.favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bz.movier.R;
import com.bz.movier.common.util.NullUtil;
import com.bz.movier.common.view.GridDividerDecoration;
import com.bz.movier.data.model.Movie;
import com.bz.movier.presentation.base.BaseFragment;
import com.bz.movier.presentation.nowshowing.MovieAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cuong Pham on 11/11/2017.
 */
public class FavoriteFragment extends BaseFragment implements FavoriteView {
    @Inject
    FavoritePresenter mFavoritePresenter;
    @BindView(R.id.rcMovies)
    RecyclerView rcMovies;
    @BindView(R.id.tvNoItem)
    TextView tvNoItem;

    private MovieAdapter mMovieAdapter;

    public static Fragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null){
            return null;
        }
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
            ButterKnife.bind(this, mView);
            initView();
            initData();
        }
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){
        mFavoritePresenter.onViewAttached(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        rcMovies.addItemDecoration(new GridDividerDecoration(getContext()));
        rcMovies.setLayoutManager(layoutManager);
    }

    private void initData(){
        mFavoritePresenter.getFavoriteMovie();
    }

    @Override
    public void bindMovies(List<Movie> movies) {
        if (NullUtil.isNullOrEmpty(movies)){
            mMovieAdapter = null;
            rcMovies.setVisibility(View.GONE);
            tvNoItem.setVisibility(View.VISIBLE);
            return;
        }
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(movies, movie -> {
                mFavoritePresenter.updateMovieOnDb(movie);
            });
            rcMovies.setAdapter(mMovieAdapter);
            rcMovies.setVisibility(View.VISIBLE);
            tvNoItem.setVisibility(View.GONE);
        }else {
            mMovieAdapter.replaceData(movies);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFavoritePresenter.destroy();
    }
}
