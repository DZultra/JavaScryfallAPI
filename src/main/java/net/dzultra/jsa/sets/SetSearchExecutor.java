package net.dzultra.jsa.sets;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.SetSearchException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class SetSearchExecutor {
    public static MTGSetList executeSetListSearch(ScryfallClient client, Gson gson, URI uri) {
        String response = sendHTTPRequest(client, uri);
        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "set")) throw new SetSearchException(uri, response);
        return parseGson(MTGSetList.class, gson, response);
    }

    public static MTGSet executeSetSearch(ScryfallClient client, Gson gson, URI uri) {
        String response = sendHTTPRequest(client, uri);
        if (!ResponseValidator.isValidResponseSingle(gson, response, "set")) throw new SetSearchException(uri, response);
        return parseGson(MTGSet.class, gson, response);
    }

    private static String sendHTTPRequest(ScryfallClient client, URI uri) {
        try {
            return client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new SetSearchException(uri, null);
        }
    }

    private static <T extends SetRecord> T parseGson(Class<T> classType, Gson gson, String response) {
        try {
            return gson.fromJson(response, classType);
        } catch (Exception e) {
            throw new SetSearchException(null, response);
        }
    }
}
