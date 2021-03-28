package com.example.hilltapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemons implements Serializable {
    public ArrayList<Pokemon> getResult() {
        return results;
    }

    public void setResult(ArrayList<Pokemon> result) {
        this.results = result;
    }

    ArrayList<Pokemon>results;

}
