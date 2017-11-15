package com.bz.movier.presentation.nowshowing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bz.movier.R;
import com.bz.movier.common.view.GridDividerDecoration;
import com.bz.movier.common.view.LoadingDialogFragment;
import com.bz.movier.data.model.Movie;
import com.bz.movier.data.model.MovieResponse;
import com.bz.movier.presentation.base.BaseFragment;
import com.bz.movier.presentation.base.PresenterLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;

/**
 * Created by Cuong Pham on 11/9/17.
 */

public class NowShowingFragment extends BaseFragment implements NowShowingView {
    private static final int LOADER_ID = 1;
    @Inject
    Lazy<NowShowingPresenter> nowShowingPresenter;
    NowShowingPresenter presenter;
    @BindView(R.id.rcMovies)
    RecyclerView rcMovies;
    @BindView(R.id.btnReload)
    Button btnReload;

    private MovieAdapter mMovieAdapter;

    public static Fragment newInstance() {
        return new NowShowingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null){
            return null;
        }
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_movie, container, false);
            ButterKnife.bind(this, mView);
            initView();
            Loader<NowShowingPresenter> loader = getLoaderManager().getLoader(LOADER_ID);
            if (loader == null){
                initLoader();
            }else {
                presenter = ((PresenterLoader<NowShowingPresenter>)loader).getPresenter();
                onPresenterCreatedORestored(presenter);
                presenter.onViewAttached(this);
            }
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initLoader(){
        getLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<NowShowingPresenter>() {
            @Override
            public Loader<NowShowingPresenter> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(getContext(),nowShowingPresenter.get());
            }

            @Override
            public void onLoadFinished(Loader<NowShowingPresenter> loader, NowShowingPresenter presenter) {
                onPresenterCreatedORestored(presenter);
                initData();
            }

            @Override
            public void onLoaderReset(Loader<NowShowingPresenter> loader) {
                NowShowingFragment.this.nowShowingPresenter = null;
            }
        });
    }

    private void onPresenterCreatedORestored(NowShowingPresenter presenter) {
       this.presenter = presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mMovieAdapter!=null) {
            presenter.onOrientationChange(mMovieAdapter.getList());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void initView(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        rcMovies.setLayoutManager(layoutManager);
        rcMovies.addItemDecoration(new GridDividerDecoration(getContext()));
        rcMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    presenter.loadMore();
                }
            }
        });
    }

    public void initData(){
        presenter.onViewAttached(this);
        presenter.getMovies();
    }

    @Override
    public void bindMovies(MovieResponse movieResponse) {
        if (movieResponse == null || movieResponse.getMovies()==null) return;
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(movieResponse.getMovies(), movie -> {
                presenter.updateMovieOnDb(movie);
            });
            rcMovies.setAdapter(mMovieAdapter);
            rcMovies.setVisibility(View.VISIBLE);
            btnReload.setVisibility(View.GONE);
        }else {
            mMovieAdapter.addData(movieResponse.getMovies());
        }
    }

    @Override
    public void reBindMovies(List<Movie> movies) {
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(movies, movie -> {
                presenter.updateMovieOnDb(movie);
            });
            rcMovies.setAdapter(mMovieAdapter);
            rcMovies.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btnReload)
    void onReloadClicked(){
        presenter.getMovies();
        btnReload.setVisibility(View.GONE);
    }

    @Override
    public void updateMovie(Movie movie) {
        mMovieAdapter.updateItem(movie);
    }

    @Override
    public void onLoadingError() {
        if (mMovieAdapter == null || mMovieAdapter.isEmpty()){
            btnReload.setVisibility(View.VISIBLE);
        }
        Toast.makeText(getContext(),"Loading error!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBottomLoading() {
        LoadingDialogFragment.showLoading(getFragmentManager(), getString(R.string.message_loading));
    }

    @Override
    public void hideBottomLoading() {
        LoadingDialogFragment.dismissLoading(getFragmentManager());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        presenter.onViewDetached();
    }
}