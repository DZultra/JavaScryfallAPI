package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.CardSearchException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class CardSearchExecutor {
    public static CardSearchObject executeCardSearch(CardRequester requester, ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!requester.isValidResponseDouble(response, "list", "card")) throw new CardSearchException(uri, response);

        try {
            return gson.fromJson(response, CardSearchObject.class);
        } catch (Exception e) {
            throw new CardSearchException(uri, response);
        }
    }

    public static Card executeSingleCardSearch(CardRequester requester, ScryfallClient client, Gson gson ,URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!requester.isValidResponseSingle(response, "card")) {
            throw new CardSearchException(uri, response);
        }

        try {
            return gson.fromJson(response, Card.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardSearchException(uri, response);
        }
    }
}
