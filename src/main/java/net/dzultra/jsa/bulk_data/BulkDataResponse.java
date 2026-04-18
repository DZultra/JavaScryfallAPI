package net.dzultra.jsa.bulk_data;

/**
 * Represents a bulk data response returned by the Scryfall API.
 * <p>
 * Bulk data responses contain metadata and a list of downloadable datasets such as
 * full card databases, rulings, and other large-scale resources.
 *
 * @param object   the type of the returned object (typically {@code "list"})
 * @param has_more indicates whether additional pages of results are available
 * @param data     an array of {@link BulkDataObjectRecord} entries describing available bulk datasets
 */
public record BulkDataResponse(
        String object,
        boolean has_more,
        BulkDataObjectRecord[] data
) {

    /**
     * Represents a single bulk data object provided by the Scryfall API.
     * <p>
     * Each object describes a downloadable dataset including metadata such as
     * size, format, and download location.
     *
     * @param object          the type of object (typically {@code "bulk_data"})
     * @param id              the unique identifier of the bulk data object
     * @param type            the dataset type (e.g., {@code "default_cards"})
     * @param updated_at      the timestamp of the last update
     * @param uri             the API URI for this bulk data object
     * @param name            the human-readable name of the dataset
     * @param description     a description of the dataset contents
     * @param size            the size of the dataset in bytes (as a string from the API)
     * @param download_uri    the direct download URI for the dataset
     * @param content_type    the MIME type of the dataset file
     * @param content_encoding the encoding format of the dataset file
     */
    public record BulkDataObjectRecord(
            String object,
            String id,
            String type,
            String updated_at,
            String uri,
            String name,
            String description,
            String size,
            String download_uri,
            String content_type,
            String content_encoding
    ) {}
}