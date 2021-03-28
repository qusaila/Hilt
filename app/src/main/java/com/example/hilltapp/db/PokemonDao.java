package com.example.hilltapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

 import com.example.hilltapp.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    public void insertPokemon(Pokemon pokemon);

    @Query("delete from pokemonTable where name =:pokemonName")
    public void deletePokemon(String pokemonName);

    @Query("select * from pokemonTable")
    public LiveData<List<Pokemon>> getPokemons();
}