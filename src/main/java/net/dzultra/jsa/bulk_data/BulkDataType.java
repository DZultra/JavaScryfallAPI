package net.dzultra.jsa.bulk_data;

/**
 * Represents the available bulk data types provided by the Scryfall API.
 * <p>
 * Each enum constant corresponds to a specific bulk dataset that can be retrieved
 * from the bulk data endpoint, such as full card lists, rulings, or artwork data.
 */
public enum BulkDataType {

    /** Oracle card data with updated rules text. */
    ORACLE_CARDS("/oracle-cards"),

    /** Dataset containing unique card artwork entries. */
    UNIQUE_ARTWORK("/unique-artwork"),

    /** Default comprehensive card dataset used for most applications. */
    DEFAULT_CARDS("/default-cards"),

    /** Complete card dataset including all printings. */
    ALL_CARDS("/all-cards"),

    /** Comprehensive rules text and card rulings dataset. */
    RULINGS("/rulings");

    /**
     * The API endpoint path associated with this bulk data type.
     */
    private final String typeEndpoint;

    /**
     * Constructs a {@code BulkDataType} with its associated endpoint path.
     *
     * @param typeEndpoint the relative API endpoint for this bulk dataset
     */
    BulkDataType(String typeEndpoint) {
        this.typeEndpoint = typeEndpoint;
    }

    /**
     * Returns the API endpoint path for this bulk data type.
     *
     * @return the endpoint string used for API requests
     */
    public String getTypeEndpoint() {
        return typeEndpoint;
    }
}