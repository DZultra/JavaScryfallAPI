package net.dzultra.jsa.exceptions;

import net.dzultra.jsa.symbology.Symbology;

public class SymbologyResponseException extends RuntimeException {
    public SymbologyResponseException(Symbology symbology) {
        super("Failed to get a valid response from the Catalog endpoint: " + symbology.endpoint +"\n" + symbology.response);
    }
}
