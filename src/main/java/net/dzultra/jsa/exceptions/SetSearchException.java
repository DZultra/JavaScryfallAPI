package net.dzultra.jsa.exceptions;

import org.jetbrains.annotations.Nullable;

import java.net.URI;

public class SetSearchException extends RuntimeException {
    public SetSearchException(URI uri, @Nullable String response) {
        super("Issue while sending SetSearch request to " + uri + ". Response: \n" + response);
    }
}
