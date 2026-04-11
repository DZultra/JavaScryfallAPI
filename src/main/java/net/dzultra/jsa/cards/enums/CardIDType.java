package net.dzultra.jsa.cards.enums;

public enum CardIDType {
    MULTIVERSE("multiverse/"),
    MTGO("mtgo/"),
    ARENA("arena/"),
    TCGPLAYER("tcgplayer/"),
    CARDMARKET("cardmarket/"),
    SCRYFALL("");

    private final String pathSegment;

    CardIDType(String pathSegment) {
        this.pathSegment = pathSegment;
    }

    public String getPathSegment() {
        return pathSegment;
    }
}
