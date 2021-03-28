package com.example.hilltapp.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hilltapp.model.Pokemon;
import com.example.hilltapp.model.Pokemons;
import com.example.hilltapp.reposetory.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private Repository repository;

    private MutableLiveData<ArrayList<Pokemon>> data;
    private LiveData<List<Pokemon>> favList = null;
    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
         data=new MutableLiveData<>(new ArrayList<>());

    }

    public MutableLiveData<ArrayList<Pokemon>> getData() {
        return data;
    }


    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<Pokemons, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(Pokemons pokemonResponse) throws Throwable {
                        ArrayList<Pokemon> list = pokemonResponse.getResult();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> data.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }
    public LiveData<List<Pokemon>> getFavList() {
        return favList;
    }

    public void insertPokemon(Pokemon pokemon) {
        repository.insertPokemon(pokemon);

    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }


    public void getFavPokemon() {
        favList = repository.getFavPokemon();
    }
 }
