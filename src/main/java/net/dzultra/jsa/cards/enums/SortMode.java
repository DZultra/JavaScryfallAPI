package net.dzultra.jsa.cards.enums;

public enum SortMode {
    AUTO("auto"),
    ASCEND("asc"),
    DESCEND("dec");

    private final String direction;

    SortMode(String dir) {
        this.direction = dir;
    }

    public String getValue() {
        return direction;
    }
}
