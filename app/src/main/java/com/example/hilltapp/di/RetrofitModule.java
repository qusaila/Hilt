package com.example.hilltapp.di;


import com.example.hilltapp.network.PokemoApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {


    @Provides
    @Singleton
    public static PokemoApiService pokemoApiService() {
        return new Retrofit.Builder()

                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PokemoApiService.class);
    }
}
