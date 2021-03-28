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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private Repository repository;

    private MutableLiveData<ArrayList<Pokemon>> data;
    private MutableLiveData<ArrayList<Pokemon>> favouriteList;

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
        favouriteList=new MutableLiveData<>(new ArrayList<>());
        data=new MutableLiveData<>(new ArrayList<>());

    }

    public MutableLiveData<ArrayList<Pokemon>> getData() {
        return data;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPoKemons()
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

 }
