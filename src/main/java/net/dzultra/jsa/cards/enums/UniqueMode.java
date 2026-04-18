package net.dzultra.jsa.cards.enums;

/**
 * Represents uniqueness filtering options for Scryfall search queries.
 * <p>
 * This controls how duplicate results are handled when querying cards,
 * such as whether to return unique cards, unique artwork, or all prints.
 */
public enum UniqueMode {

    /**
     * Returns unique cards (default behavior based on card identity).
     */
    CARDS("cards"),

    /**
     * Returns unique artwork entries, grouping cards by art instead of printing.
     */
    ART("art"),

    /**
     * Returns unique printings of cards, including all versions.
     */
    PRINTS("prints");

    /**
     * The string value used in API query parameters.
     */
    private final String name;

    UniqueMode(String name) {
        this.name = name;
    }

    /**
     * Returns the API value used for this uniqueness mode.
     *
     * @return the string representation used in Scryfall queries
     */
    public String getValue() {
        return name;
    }
}