package net.dzultra.jsa.cards.enums;

/**
 * Represents sort direction options for Scryfall API queries.
 * <p>
 * This enum defines how results should be ordered after applying a sort key,
 * such as ascending or descending order.
 */
public enum SortMode {

    /**
     * Automatically determines the best sort direction based on the query context.
     */
    AUTO("auto"),

    /**
     * Sort results in ascending order (A → Z, low → high).
     */
    ASCEND("asc"),

    /**
     * Sort results in descending order (Z → A, high → low).
     */
    DESCEND("dec");

    /**
     * The string value used in API query parameters.
     */
    private final String direction;

    SortMode(String dir) {
        this.direction = dir;
    }

    /**
     * Returns the API value representing this sort direction.
     *
     * @return the string used in Scryfall requests (e.g. "asc", "dec", "auto")
     */
    public String getValue() {
        return direction;
    }
}