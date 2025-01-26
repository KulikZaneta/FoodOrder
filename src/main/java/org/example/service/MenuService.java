package org.example.service;

import org.example.model.Cuisines;
import org.example.model.Drink;
import org.example.model.Lunch;

import java.util.*;

public class MenuService {
    private final Map<Cuisines, List<Lunch>> lunches;
    private final List<Drink> drinks;

    public MenuService() {
        lunches = new HashMap<>();
        drinks = new ArrayList<>();

        lunches.put(Cuisines.PL, Arrays.asList(
                new Lunch("Bigos", "Sernik", 25.0),
                new Lunch("Pierogi", "Makowiec", 20.0)
        ));

        lunches.put(Cuisines.IT, Arrays.asList(
                new Lunch("Pizza", "Tiramisu", 30.0),
                new Lunch("Pasta", "Panna Cotta", 28.0)
        ));

        lunches.put(Cuisines.MX, Arrays.asList(
                new Lunch("Tacos", "Flan", 22.0),
                new Lunch("Burritos", "Churros", 26.0)
        ));

        drinks.add(new Drink("Water", 5.0));
        drinks.add(new Drink("Coffee", 7.0));
        drinks.add(new Drink("Tea", 6.0));
        drinks.add(new Drink("Apple juice", 8.0));
    }

    public List<Lunch> getLunches(Cuisines cuisine) {
        return lunches.getOrDefault(cuisine, Collections.emptyList());
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}


