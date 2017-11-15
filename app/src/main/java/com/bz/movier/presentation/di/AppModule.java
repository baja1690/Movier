package com.bz.movier.presentation.di;

import android.app.Application;
import android.content.Context;

import com.bz.movier.data.local.RealmRepository;
import com.bz.movier.domain.executor.JobExecutor;
import com.bz.movier.domain.executor.PostExecutionThread;
import com.bz.movier.domain.executor.ThreadExecutor;
import com.bz.movier.domain.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Cuong Pham on 11/9/17.
 */
@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Application appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return this.appContext;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(){
        return new UIThread();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(){
        return new JobExecutor();
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    public RealmRepository provideRealmDb(Realm realm){
        return new RealmRepository(realm);
    }
}
