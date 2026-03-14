package net.dzultra.jsa.catalogs;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import net.dzultra.jsa.exceptions.CatalogResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class Catalog {
    protected Gson gson = new Gson();
    protected final ScryfallClient client;
    public final URI uri;
    private final CatalogType catalogType;
    private final String response;

    public Catalog(ScryfallClient client, CatalogType catalogType) {
         this.client = client;
         this.catalogType = catalogType;
         this.uri = URI.create(this.client.getBaseUrl() + this.catalogType.getEndpoint());
        try {
            this.response = this.client.httpClient.send(this.client.requestBuilder.GET().uri(this.uri).build(), HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new CatalogResponseException(this);
        }
    }

    public String getResponse() {
        return this.response;
    }

    public CatalogResponse getCatalog() {
        if(isValidResponse()) {
            return gson.fromJson(this.getResponse(), CatalogResponse.class);
        } else {
            throw new CatalogResponseException(this);
        }
    }

    public boolean isValidResponse() {
        return gson.fromJson(this.getResponse(), TypeRecord.class).type().equals("catalog");
    }

    public CatalogType getCatalogType() {
        return this.catalogType;
    }
}
