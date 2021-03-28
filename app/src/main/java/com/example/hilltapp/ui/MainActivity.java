package com.example.hilltapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hilltapp.adapters.PokemonsAdapter;
import com.example.hilltapp.databinding.ActivityMainBinding;
import com.example.hilltapp.model.Pokemon;
import com.example.hilltapp.viewmodels.PokemonViewModel;
import com.example.hilltapp.R;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    PokemonViewModel pokemonViewModel;
    PokemonsAdapter pokemonsAdapter;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        pokemonViewModel.getPokemons();
        pokemonsAdapter = new PokemonsAdapter(pokemonViewModel.getData().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activityMainBinding.rv.setLayoutManager(linearLayoutManager);
        activityMainBinding.rv.setAdapter(pokemonsAdapter);
        pokemonViewModel.getData().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                if (pokemons != null)

                    pokemonsAdapter.notifiyList(pokemons);
            }
        });

     }


}