package net.dzultra.jsa.symbology;

/**
 * Represents the parsed result of a mana cost returned by the Scryfall API.
 * <p>
 * This response provides a structured interpretation of a mana cost string,
 * including its color composition and various derived properties such as
 * converted mana cost and color classification.
 *
 * @param object        the type of the returned object (typically {@code "mana_cost"})
 * @param cost          the normalized mana cost string (e.g., {@code "{2}{G}{G}"})
 * @param colors        an array of color identifiers present in the cost (e.g., {@code ["G"]})
 * @param cmc           the converted mana cost (total numeric value of the cost)
 * @param colorless     whether the mana cost contains no colored mana symbols
 * @param monocolored   whether the mana cost contains exactly one color
 * @param multicolored  whether the mana cost contains more than one color
 */
public record ManaCostResponse(
        String object,
        String cost,
        String[] colors,
        double cmc,
        boolean colorless,
        boolean monocolored,
        boolean multicolored
) {}