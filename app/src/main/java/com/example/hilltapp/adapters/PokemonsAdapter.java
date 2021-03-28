package com.example.hilltapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hilltapp.R;
import com.example.hilltapp.databinding.PokemonRowBinding;
import com.example.hilltapp.model.Pokemon;

import java.util.ArrayList;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.PokemonHolder> {
    ArrayList<Pokemon> pokemons;

    public PokemonsAdapter(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonRowBinding pokemonRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pokemon_row, parent, false);
        return new PokemonHolder(pokemonRowBinding);
    }
public void notifiyList(ArrayList<Pokemon>pokemons){
        this.pokemons=pokemons;
        notifyDataSetChanged();
}
    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.bind(pokemon);
    }
public Pokemon getPokemon(int pos){
        return pokemons.get(pos);
}
    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class PokemonHolder extends RecyclerView.ViewHolder {
        PokemonRowBinding pokemonRowBinding;

        public PokemonHolder(@NonNull PokemonRowBinding itemView) {
            super(itemView.getRoot());
            this.pokemonRowBinding = itemView;

        }

        public void bind(Pokemon pokemon) {
            pokemonRowBinding.setPokemon(pokemon);
            pokemonRowBinding.executePendingBindings();
        }
    }
}
