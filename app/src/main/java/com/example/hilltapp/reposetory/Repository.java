package com.example.hilltapp.reposetory;

import androidx.lifecycle.LiveData;

import com.example.hilltapp.db.PokemonDao;
import com.example.hilltapp.model.Pokemon;
import com.example.hilltapp.network.PokemoApiService;
import com.example.hilltapp.model.Pokemons;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private PokemoApiService pokemonApiService;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(PokemoApiService pokemonApiService, PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<Pokemons> getPokemons(){
        return pokemonApiService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon){pokemonDao.insertPokemon(pokemon);}

    public void deletePokemon(String pokemonName){pokemonDao.deletePokemon(pokemonName);}

    public LiveData<List<Pokemon>> getFavPokemon(){
        return pokemonDao.getPokemons();
    }
}
