package com.example.hilltapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hilltapp.adapters.PokemonsAdapter;
import com.example.hilltapp.databinding.ActivityMainBinding;
import com.example.hilltapp.model.Pokemon;
import com.example.hilltapp.viewmodels.PokemonViewModel;
import com.example.hilltapp.R;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


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
        activityMainBinding.setLifecycleOwner(this);
        pokemonViewModel.getFavPokemon();
        pokemonViewModel.getFavList().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                if (pokemons!=null)
                    if (pokemons.size()>0)
                Toast.makeText(MainActivity.this,+pokemons.size()+"",Toast.LENGTH_LONG).show();
            }
        });
         pokemonViewModel.getData().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                if (pokemons != null)

                    pokemonsAdapter.notifiyList(pokemons);
            }
        });

        setupSwipe();
     }
    private void setupSwipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition = viewHolder.getAdapterPosition();
                Pokemon swipedPokemon = pokemonsAdapter.getPokemon(swipedPokemonPosition);
                pokemonViewModel.insertPokemon(swipedPokemon);

                Toast.makeText(MainActivity.this, "pokemon added to database", Toast.LENGTH_SHORT).show();
                pokemonsAdapter.notifyDataSetChanged();
             }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(activityMainBinding.rv);
    }


}