package net.dzultra.jsa.symbology;

public record ManaCostResponse(
        String object,
        String cost,
        String[] colors,
        double cmc,
        boolean colorless,
        boolean monocolored,
        boolean multicolored
) {}
