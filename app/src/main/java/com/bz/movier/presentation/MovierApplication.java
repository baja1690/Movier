package com.bz.movier.presentation;

import android.app.Activity;
import android.app.Application;

import com.bz.movier.presentation.di.AppModule;
import com.bz.movier.presentation.di.DaggerMovierAppComponent;
import com.bz.movier.common.util.Config;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Cuong Pham on 11/8/17.
 */

public class MovierApplication extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        realmConfig();
        DaggerMovierAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    private void realmConfig(){
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(Config.DB_NAME)
                    .schemaVersion(Config.VERSION_DB)
                    .build();
            Realm.setDefaultConfiguration(config);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
