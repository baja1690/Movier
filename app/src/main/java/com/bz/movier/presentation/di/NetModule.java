package com.bz.movier.presentation.di;

import com.bz.movier.data.remote.retrofit.RestServiceEndPoint;
import com.bz.movier.common.util.Config;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cuong Pham on 11/9/17.
 */
@Module
public class NetModule {
    public NetModule() {
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public RestServiceEndPoint provideRestServiceEndPoint(Retrofit retrofit){
        return retrofit.create(RestServiceEndPoint.class);
    }
}