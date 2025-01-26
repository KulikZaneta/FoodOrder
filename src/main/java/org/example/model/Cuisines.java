package org.example.model;

public enum Cuisines {
    PL("Polish cuisine"),
    IT("Italian cuisine"),
    MX("Mexican cuisine");

    private final String description;

    Cuisines(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
