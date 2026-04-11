package net.dzultra.jsa.exceptions;

import org.jetbrains.annotations.Nullable;

import java.net.URI;

public class CardNameException extends RuntimeException {
    public CardNameException(URI uri, @Nullable String response) {
        super("Issue while sending CardName request to " + uri + ". Response: \n" + response);
    }
}
