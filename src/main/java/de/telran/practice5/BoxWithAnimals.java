package de.telran.practice5;

import de.telran.practice1.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxWithAnimals<TYPE extends Animal> {
    private List<TYPE> animals;

    public List<TYPE> getAnimals() {
        return animals;
    }

    public void setAnimals(List<TYPE> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public BoxWithAnimals(List<TYPE> animals) {
        this.animals = new ArrayList<>(animals);
    }

    public BoxWithAnimals(TYPE... animals) {
        this.animals = new ArrayList<>(Arrays.asList(animals));
    }

    public void shift(BoxWithAnimals<? super TYPE> dest) {
        if (this == dest) return;
        dest.animals.addAll(animals);
        animals.clear();
    }
}
