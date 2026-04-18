package net.dzultra.jsa.cards.enums;

/**
 * Represents the different identifier types that can be used
 * to retrieve a card via the Scryfall API.
 * <p>
 * Each enum value maps to a specific API path segment used
 * when constructing card lookup endpoints.
 */
public enum CardIDType {

    /**
     * Card lookup by Multiverse ID (Gatherer database).
     */
    MULTIVERSE("multiverse/"),

    /**
     * Card lookup by Magic Online (MTGO) ID.
     */
    MTGO("mtgo/"),

    /**
     * Card lookup by MTG Arena ID.
     */
    ARENA("arena/"),

    /**
     * Card lookup by TCGplayer product ID.
     */
    TCGPLAYER("tcgplayer/"),

    /**
     * Card lookup by Cardmarket ID.
     */
    CARDMARKET("cardmarket/"),

    /**
     * Direct Scryfall ID lookup (no prefix required).
     */
    SCRYFALL("");

    /**
     * The path segment used in the API endpoint for this identifier type.
     */
    private final String pathSegment;

    CardIDType(String pathSegment) {
        this.pathSegment = pathSegment;
    }

    /**
     * Returns the API path segment associated with this identifier type.
     *
     * @return the URL path segment used for card lookup requests
     */
    public String getPathSegment() {
        return pathSegment;
    }
}