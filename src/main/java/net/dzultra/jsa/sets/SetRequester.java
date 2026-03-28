package net.dzultra.jsa.sets;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;

import java.net.URI;
import java.util.UUID;

public class SetRequester {
    protected Gson gson = new Gson();
    public final ScryfallClient client;

    public SetRequester(ScryfallClient client) {
        this.client = client;
    }

    public MTGSetList getAllSets() {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/");
        return SetSearchExecutor.executeSetListSearch(this.client, this.gson, uri);
    }

    public MTGSet getSetByCode(String code) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/" + code);
        return SetSearchExecutor.executeSetSearch(this.client, this.gson, uri);
    }

    public MTGSet getSetByTCGPlayerID(int id) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/tcgplayer/" + id);
        return SetSearchExecutor.executeSetSearch(this.client, this.gson, uri);
    }

    public MTGSet getSetByUUID(UUID id) {
        URI uri = URI.create(this.client.getBaseUrl() + "/sets/" + id);
        return SetSearchExecutor.executeSetSearch(this.client, this.gson, uri);
    }
}
