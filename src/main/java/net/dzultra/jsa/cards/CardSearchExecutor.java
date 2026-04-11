package net.dzultra.jsa.cards;

import com.google.gson.Gson;
import net.dzultra.jsa.ResponseValidator;
import net.dzultra.jsa.ScryfallClient;
import net.dzultra.jsa.exceptions.CardCollectionException;
import net.dzultra.jsa.exceptions.CardNameException;
import net.dzultra.jsa.exceptions.CardSearchException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CardSearchExecutor {
    public static CardList executeCardSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "card")) throw new CardSearchException(uri, response);

        try {
            return gson.fromJson(response, CardList.class);
        } catch (Exception e) {
            throw new CardSearchException(uri, response);
        }
    }

    public static Card executeSingleCardSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardSearchException(uri, null);
        }

        if (!ResponseValidator.isValidResponseSingle(gson, response, "card")) {
            throw new CardSearchException(uri, response);
        }

        try {
            return gson.fromJson(response, Card.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardSearchException(uri, response);
        }
    }

    public static CardNameList executeCardNameListSearch(ScryfallClient client, Gson gson, URI uri) {
        String response;

        try {
            response = client.httpClient.send(
                    client.requestBuilder.GET().uri(uri).build(),
                    HttpResponse.BodyHandlers.ofString()
            ).body();
        } catch (IOException | InterruptedException e) {
            throw new CardNameException(uri, null);
        }

        if (!ResponseValidator.isValidResponseSingle(gson, response, "catalog")) {
            throw new CardNameException(uri, response);
        }

        try {
            return gson.fromJson(response, CardNameList.class);
        } catch (Exception e) {
            System.out.println(e.getCause() + " | " + e.getMessage());
            throw new CardNameException(uri, response);
        }
    }

//    @Deprecated
//    public static CardCollection executeCardCollectionSearch(ScryfallClient client, Gson gson, URI uri) {
//        String body = "";
//
//        String response;
//
//        try {
//            response = client.httpClient.send(
//                    client.requestBuilder.header("Content-Type", "application/json")
//                            .POST(HttpRequest.BodyPublishers.ofString(body))
//                            .uri(uri).build(),
//                    HttpResponse.BodyHandlers.ofString()
//            ).body();
//        } catch (IOException | InterruptedException e) {
//            throw new CardCollectionException(uri, null);
//        }
//
//        if (!ResponseValidator.isValidResponseDouble(gson, response, "list", "card")) {
//            throw new CardCollectionException(uri, response);
//        }
//
//        try {
//            return gson.fromJson(response, CardCollection.class);
//        } catch (Exception e) {
//            System.out.println(e.getCause() + " | " + e.getMessage());
//            throw new CardCollectionException(uri, response);
//        }
//    }
}
