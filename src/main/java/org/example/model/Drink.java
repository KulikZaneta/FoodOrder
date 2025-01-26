package org.example.model;

public class Drink {
    private String nameOfDrink;

    private double price;

    private boolean isIceIncluded;

    private boolean isLemonIncluded;

    public Drink(String drink, Double price) {
        this.nameOfDrink = drink;
        this.price = price;
    }

    public String getNameOfDrink() {
        return nameOfDrink;
    }

    public void setNameOfDrink(String nameOfDrink) {
        this.nameOfDrink = nameOfDrink;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIceIncluded() {
        return isIceIncluded;
    }

    public void setIceIncluded(boolean iceIncluded) {
        isIceIncluded = iceIncluded;
    }

    public boolean isLemonIncluded() {
        return isLemonIncluded;
    }

    public void setLemonIncluded(boolean lemonIncluded) {
        isLemonIncluded = lemonIncluded;
    }

    public String getDescription() {
        String description = nameOfDrink;
        if (isIceIncluded) {
            description += " with ice";
        }
        if (isLemonIncluded) {
            description += " and lemon";
        }
        return description;
    }

}

