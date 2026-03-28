package net.dzultra.jsa.cards.enums;

public enum OrderMode {
    NAME("name"),
    SET("set"),
    RELEASED("released"),
    RARITY("rarity"),
    COLOR("color"),
    USD("usd"),
    TIX("tix"),
    EUR("eur"),
    CMC("cmc"),
    POWER("power"),
    TOUGHNESS("toughness"),
    EDHREC("edhrec"),
    PENNY("penny"),
    ARTIST("artist"),
    REVIEW("review");

    private final String value;

    OrderMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
