package net.dzultra.jsa.exceptions;

import org.jetbrains.annotations.Nullable;

import java.net.URI;

public class CardCollectionException extends RuntimeException {
    public CardCollectionException(URI uri, @Nullable String response) {
        super("Issue while sending CardCollection request to " + uri + ". Response: \n" + response);
    }
}
