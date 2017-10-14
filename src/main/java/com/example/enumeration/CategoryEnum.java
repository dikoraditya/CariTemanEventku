package com.example.enumeration;

/**
 * Created by kevinnkurniawan on 10/14/17.
 */
public enum CategoryEnum {

    TECHNOLOGY("Technology"),
    SPORT("Sport"),
    MUSIC("Music");

    private final String category;

    CategoryEnum(final String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
