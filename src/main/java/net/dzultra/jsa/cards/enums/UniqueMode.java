package net.dzultra.jsa.cards.enums;

public enum UniqueMode {
    CARDS("cards"),
    ART("art"),
    PRINTS("prints");

    private final String name;

    UniqueMode(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
