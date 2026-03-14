package net.dzultra.jsa.exceptions;

import net.dzultra.jsa.catalogs.Catalog;

public class CatalogResponseException extends RuntimeException {
    public CatalogResponseException(Catalog catalog) {
        super("Failed to get a valid response from the Catalog endpoint: " + catalog.uri.toString() + "\n" + catalog.getResponse());
    }
}
