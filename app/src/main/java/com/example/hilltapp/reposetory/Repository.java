package com.example.hilltapp.reposetory;

import androidx.lifecycle.LiveData;

 import com.example.hilltapp.model.Pokemon;
import com.example.hilltapp.network.PokemoApiService;
import com.example.hilltapp.model.Pokemons;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private PokemoApiService pokemoApiService;
     @Inject
    public Repository(PokemoApiService pokemoApiService ) {
         this.pokemoApiService = pokemoApiService;
    }

    public Observable<Pokemons> getPoKemons() {
        return pokemoApiService.getPokemons();
    }


}
