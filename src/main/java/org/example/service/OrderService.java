package org.example.service;

import org.example.model.Cuisines;
import org.example.model.Drink;
import org.example.model.Lunch;

import java.util.*;

public class OrderService {
    private final MenuService menuService;
    private final Scanner scanner;

    public OrderService(MenuService menuService, Scanner scanner) {
        this.menuService = menuService;
        this.scanner = scanner;
    }

    public void takeOrder() {
        displayWelcomeMessage();

        List<Lunch> orderedLunches = new ArrayList<>();
        List<Drink> orderedDrinks;

        String choice = askForLunchOrDrink();
        if (choice.equals("lunch")) {
            orderedLunches = orderLunches();
            orderedDrinks = askForDrinks();
        } else {
            orderedDrinks = orderDrinks();
        }

        printOrderSummary(orderedLunches, orderedDrinks);
        double totalCost = calculateTotalCost(orderedLunches, orderedDrinks);
        System.out.println("\nTotal cost: " + totalCost + " zł");

        thankCustomer();
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to our restaurant! " +
                "\nWe offer a wide selection of lunches and drinks.");
    }

    private String askForLunchOrDrink() {
        return getValidInput("Would you like to order a lunch or a drink? (lunch/drink): ", Arrays.asList("lunch", "drink"));
    }

    private String getValidInput(String question, List<String> validOptions) {
        while (true) {
            System.out.println(question);
            String input = scanner.nextLine().trim().toLowerCase();
            if (validOptions.contains(input)) {
                return input;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private List<Lunch> orderLunches() {
        List<Lunch> lunchesList = new ArrayList<>();

        while (true) {
            Cuisines cuisines = chooseCuisine();
            Lunch lunch = chooseLunch(cuisines);
            lunchesList.add(lunch);

            String continueOrder = getValidInput("Would you like to order another lunch? (yes/no): ", Arrays.asList("yes", "no"));
            if (!continueOrder.equals("yes")) {
                break;
            }
        }
        return lunchesList;
    }

    private Cuisines chooseCuisine() {
        System.out.println("Available cuisines:");
        for (Cuisines cuisine : Cuisines.values()) {
            System.out.println(cuisine.ordinal() + 1 + ": " + cuisine.getDescription());
        }

        while (true) {
            System.out.println("Choose a cuisine by entering its number:");
            try {
                int cuisineNumber = Integer.parseInt(scanner.nextLine().trim()) - 1;
                return Cuisines.values()[cuisineNumber];
            } catch (Exception e) {
                System.out.println("Invalid choice. Please select a valid cuisine number.");
            }
        }
    }

    private Lunch chooseLunch(Cuisines cuisines) {
        List<Lunch> availableLunches = menuService.getLunches(cuisines);

        System.out.println("Available lunches for " + cuisines.getDescription() + ":");
        for (int i = 0; i < availableLunches.size(); i++) {
            System.out.println((i + 1) + ": " + availableLunches.get(i).getNameOfDish() + " with " + availableLunches.get(i).getNameOfDessert() + " (" + availableLunches.get(i).getPrice() + " zł)");
        }

        while (true) {
            System.out.println("Choose a lunch by entering its number:");
            try {
                int lunchNumber = Integer.parseInt(scanner.nextLine().trim()) - 1;
                return availableLunches.get(lunchNumber);
            } catch (Exception e) {
                System.out.println("Invalid choice. Please select a valid lunch number.");
            }
        }
    }


    private List<Drink> askForDrinks() {
        String wantDrink = getValidInput("Would you like something to drink? (yes/no): ", Arrays.asList("yes", "no"));
        if (wantDrink.equals("yes")) {
            return orderDrinks();
        } else {
            return new ArrayList<>();
        }
    }

    private List<Drink> orderDrinks() {
        List<Drink> drinkList = new ArrayList<>();
        while (true) {
            Drink drink = chooseDrink();
            if (drink != null) {
                Drink newDrink = new Drink(drink.getNameOfDrink(), drink.getPrice());
                askForExtras(newDrink);
                drinkList.add(newDrink);
            }

            String allDoneDrink = getValidInput("Is that all with the drinks? (yes/no): ", Arrays.asList("yes", "no"));
            if (allDoneDrink.equals("yes")) {
                break;
            }
        }
        return drinkList;
    }

    private Drink chooseDrink() {
        List<Drink> availableDrinks = menuService.getDrinks();

        System.out.println("Available drinks:");
        for (int i = 0; i < availableDrinks.size(); i++) {
            System.out.println((i + 1) + ": " + availableDrinks.get(i).getDescription() + " (" + availableDrinks.get(i).getPrice() + " zł)");
        }

        while (true) {
            System.out.println("Choose a drink by entering its number:");
            try {
                int drinkNumber = Integer.parseInt(scanner.nextLine().trim()) - 1;
                return availableDrinks.get(drinkNumber);
            } catch (Exception e) {
                System.out.println("Invalid choice. Please select a valid drink number.");
            }
        }
    }

    private void askForExtras(Drink drink) {
        String ice = getValidInput("Would you like to add ice? (yes/no): ", Arrays.asList("yes", "no"));
        if (ice.equals("yes")) {
            drink.setIceIncluded(true);
        }

        String lemon = getValidInput("Would you like to add lemon? (yes/no): ", Arrays.asList("yes", "no"));
        if (lemon.equals("yes")) {
            drink.setLemonIncluded(true);
        }

        System.out.println("Drink " + drink.getDescription());
    }

    private void printOrderSummary(List<Lunch> lunches, List<Drink> drinks) {
        System.out.println("\nYour order summary:");

        if (lunches.isEmpty()) {
            System.out.println("No lunches in order.");
        } else {
            System.out.println("Lunches:");
            for (Lunch lunch : lunches) {
                System.out.println("- " + lunch.getNameOfDish() + " - " + lunch.getNameOfDessert() + " (" + lunch.getPrice() + " zł)");
            }
        }

        if (drinks.isEmpty()) {
            System.out.println("No drinks in order.");
        } else {
            System.out.println("Drinks:");
            for (Drink drink : drinks) {
                System.out.println("- " + drink.getDescription() + " (" + drink.getPrice() + " zł)");
            }
        }
    }

    private double calculateTotalCost(List<Lunch> lunches, List<Drink> drinks) {
        double total = 0;

        if (lunches != null) {
            total += lunches.stream().mapToDouble(Lunch::getPrice).sum();
        }

        if (drinks != null) {
            total += drinks.stream().mapToDouble(Drink::getPrice).sum();
        }

        return total;
    }

    private void thankCustomer() {
        System.out.println("Thank you for your order!");
    }
}
