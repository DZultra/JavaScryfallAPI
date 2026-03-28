package net.dzultra.jsa.symbology;

import com.google.gson.Gson;
import net.dzultra.jsa.DataTypeRecord;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.TypeRecord;
import net.dzultra.jsa.exceptions.SymbologyResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class Symbology {
    protected final ScryfallClient client;
    protected Gson gson = new Gson();
    public String endpoint = "/symbology/";
    public String response;


    public Symbology(ScryfallClient client) {
        this.client = client;
    }

    private String getResponse(URI uri) {
        try {
            this.response = this.client.httpClient.send(this.client.requestBuilder.GET().uri(uri).build(), HttpResponse.BodyHandlers.ofString()).body();
            if(ResponseValidator.isValidSymbologyResponse(gson, response)) {
                return response;
            } else {
                throw new SymbologyResponseException(this);
            }
        } catch (IOException | InterruptedException e) {
            throw new SymbologyResponseException(this);
        }
    }

    public SymbologyResponse getCardSymbols() {
        return gson.fromJson(this.getResponse(URI.create(this.client.getBaseUrl() + this.endpoint)), SymbologyResponse.class);
    }

    public ManaCostResponse getManaCost(String manaCost) {
        return gson.fromJson(this.getResponse(URI.create(this.client.getBaseUrl() + this.endpoint + "parse-mana?cost=" + manaCost)), ManaCostResponse.class);
    }
}
