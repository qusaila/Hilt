package com.example.hilltapp.network;

import com.example.hilltapp.model.Pokemons;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemoApiService {
@GET("pokemon")
Observable<Pokemons> getPokemons();
}
