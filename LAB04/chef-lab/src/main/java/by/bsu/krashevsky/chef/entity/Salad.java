package by.bsu.krashevsky.chef.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Салат - набор овощей.
 */
public class Salad {

    private final String name;
    private final List<Vegetable> vegetables = new ArrayList<>();

    public Salad(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    public List<Vegetable> getVegetables() {
        return Collections.unmodifiableList(vegetables);
    }

    @Override
    public String toString() {
        return "Salad{name='" + name + "', vegetables=" + vegetables.size() + "}";
    }
}
