package net.dzultra.jsa.exceptions;

import org.jetbrains.annotations.Nullable;

import java.net.URI;

public class CardSearchException extends RuntimeException {
    public CardSearchException(URI uri, @Nullable String response) {
        super("Issue while sending CardSearch request to " + uri + ". Response: \n" + response);
    }
}
