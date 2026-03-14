package net.dzultra.jsa.exceptions;

import net.dzultra.jsa.bulk_data.BulkData;

public class BulkDataResponseException extends RuntimeException {
    public BulkDataResponseException(BulkData bulkData) {
        super("Failed to get a valid response from the Catalog endpoint: " + bulkData.endpoint +"\n" + bulkData.response);
    }
}
