package net.dzultra.jsa.cards.enums;

/**
 * Represents sorting order options for card search results in the Scryfall API.
 * <p>
 * These values determine how results are ordered when performing queries such as
 * card searches or listings.
 */
public enum OrderMode {

    /** Sort by card name. */
    NAME("name"),

    /** Sort by set code. */
    SET("set"),

    /** Sort by release date. */
    RELEASED("released"),

    /** Sort by rarity level. */
    RARITY("rarity"),

    /** Sort by color identity. */
    COLOR("color"),

    /** Sort by price in USD. */
    USD("usd"),

    /** Sort by price in TIX (Magic Online currency). */
    TIX("tix"),

    /** Sort by price in EUR. */
    EUR("eur"),

    /** Sort by converted mana cost (CMC). */
    CMC("cmc"),

    /** Sort by power value. */
    POWER("power"),

    /** Sort by toughness value. */
    TOUGHNESS("toughness"),

    /** Sort by EDHREC popularity rank. */
    EDHREC("edhrec"),

    /** Sort by Penny Dreadful format ranking. */
    PENNY("penny"),

    /** Sort by artist name. */
    ARTIST("artist"),

    /** Sort by review status or relevance. */
    REVIEW("review");

    /**
     * The string value used in API query parameters.
     */
    private final String value;

    OrderMode(String value) {
        this.value = value;
    }

    /**
     * Returns the API query value for this sort mode.
     *
     * @return the string representation used in Scryfall requests
     */
    public String getValue() {
        return value;
    }
}