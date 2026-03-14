package net.dzultra.jsa.bulk_data;

public enum BulkDataType {
    ORACLE_CARDS("/oracle-cards"),
    UNIQUE_ARTWORK("/unique-artwork"),
    DEFAULT_CARDS("/default-cards"),
    ALL_CARDS("/all-cards"),
    RULINGS("/rulings"),
    ;
    private final String typeEndpoint;

    BulkDataType(String typeEndpoint) {
        this.typeEndpoint = typeEndpoint;
    }

    public String getTypeEndpoint() {
        return typeEndpoint;
    }
}
