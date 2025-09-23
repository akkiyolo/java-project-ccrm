package edu.ccrm.domain;

/**
 * Enum for Semester with constructor & field.
 */
public enum Semester {
    SPRING("Spring"),
    SUMMER("Summer"),
    FALL("Fall");

    private final String display;

    Semester(String display) { this.display = display; }

    public String getDisplay() { return display; }

    @Override
    public String toString() { return display; }
}
