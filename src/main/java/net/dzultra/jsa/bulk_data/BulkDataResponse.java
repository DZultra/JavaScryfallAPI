package net.dzultra.jsa.bulk_data;

public record BulkDataResponse(
    String object,
    boolean has_more,
    BulkDataObjectRecord[] data
) {
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
