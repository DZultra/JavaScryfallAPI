package net.dzultra.jsa.bulk_data;

import com.google.gson.Gson;
import net.dzultra.jsa.DataTypeRecord;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import net.dzultra.jsa.exceptions.BulkDataResponseException;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class BulkData {
    protected final ScryfallClient client;
    protected Gson gson = new Gson();
    public String endpoint = "/bulk-data/";
    public String response;

    public BulkData(ScryfallClient client) {
        this.client = client;
    }

    private String getResponse(URI uri) {
        try {
            this.response = this.client.httpClient.send(this.client.requestBuilder.GET().uri(uri).build(), HttpResponse.BodyHandlers.ofString()).body();
            if(isValidBulkDataResponse(response)) {
                return response;
            } else {
                throw new BulkDataResponseException(this);
            }
        } catch (IOException | InterruptedException e) {
            throw new BulkDataResponseException(this);
        }
    }

    public BulkDataResponse getBulkData() {
        return gson.fromJson(this.getResponse(createURI("")), BulkDataResponse.class);
    }

    public BulkDataResponse.BulkDataObjectRecord getBulkData(BulkDataType type) {
        return gson.fromJson(this.getResponse(createURI(type.getTypeEndpoint())), BulkDataResponse.BulkDataObjectRecord.class);
    }

    public BulkDataResponse.BulkDataObjectRecord getBulkData(String uuid) {
        return gson.fromJson(this.getResponse(createURI(uuid)), BulkDataResponse.BulkDataObjectRecord.class);
    }

    public boolean isValidBulkDataResponse(String response) {
        return (gson.fromJson(response, TypeRecord.class).type().equals("list")
                        && gson.fromJson(response, DataTypeRecord.class).data()[0].type().equals("bulk_data")
        ) || gson.fromJson(response, TypeRecord.class).type().equals("bulk_data");
    }

    private URI createURI(@Nullable String additionalEndpoint) {
        this.endpoint += additionalEndpoint;
        return URI.create(this.client.getBaseUrl() + this.endpoint);
    }
}
