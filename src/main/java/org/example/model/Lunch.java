package org.example.model;

public class Lunch {
    private String nameOfDish;

    private String nameOfDessert;

    private double price;

    public Lunch(String nameOfDish, String nameOfDessert, double price) {
        this.nameOfDish = nameOfDish;
        this.nameOfDessert = nameOfDessert;
        this.price = price;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public String getNameOfDessert() {
        return nameOfDessert;
    }

    public void setNameOfDessert(String nameOfDessert) {
        this.nameOfDessert = nameOfDessert;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Lunch{" +
                "nameOfDish='" + nameOfDish + '\'' +
                ", nameOfDessert='" + nameOfDessert + '\'' +
                ", price=" + price +
                '}';
    }
}

