package com.bz.movier.domain.interactor;

import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class BaseUseCase<Q extends BaseUseCase.RequestValues, R> implements UseCase {
    protected ThreadExecutor mExecutor;
    protected PostExecutionThread mPostExcecutor;
    protected Q mRequestValues;
    Disposable disposable = new CompositeDisposable();

    public BaseUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread){
        this.mExecutor = executor;
        this.mPostExcecutor = postExecutionThread;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setRequestValues(Q mRequestValues) {
        this.mRequestValues = mRequestValues;
    }

    protected abstract Flowable<R> createResultApi(Q requestValues)throws Exception;

    @Override
    public void execute(DisposableSubscriber subscriber) throws Exception {
        this.createResultApi(mRequestValues)
                .subscribeOn(Schedulers.from(mExecutor))
                .observeOn(mPostExcecutor.getScheduler())
                .subscribe(subscriber);
    }

    public void dispose(){
       disposable.dispose();
    }

    public interface RequestValues {

    }
}
