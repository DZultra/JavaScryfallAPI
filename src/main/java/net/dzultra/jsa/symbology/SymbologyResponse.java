package net.dzultra.jsa.symbology;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a symbology response returned by the Scryfall API.
 * <p>
 * This response typically contains a list of mana or card symbol definitions,
 * along with metadata about pagination.
 *
 * @param type     the type of the returned object (usually {@code "list"})
 * @param has_more indicates whether additional pages of results are available
 * @param data     an array of {@link SymbologyObjectRecord} entries describing individual symbols
 */
public record SymbologyResponse(
        @SerializedName("object") String type,
        boolean has_more,
        SymbologyObjectRecord[] data
) {

    /**
     * Represents a single symbology object (e.g., a mana symbol or special card symbol)
     * returned by the Scryfall API.
     * <p>
     * Each object describes visual representation, meaning, and gameplay-related
     * properties of a symbol.
     *
     * @param type                the type of the object (typically {@code "card_symbol"})
     * @param symbol              the symbolic representation (e.g., "{G}", "{2/W}")
     * @param svgUri              the URI to an SVG image of the symbol; may be {@code null}
     * @param looseVariant        an alternate textual representation of the symbol; may be {@code null}
     * @param english             the English description of the symbol
     * @param transposable        whether the symbol can be reordered in a mana cost
     * @param representsMana      whether the symbol represents a mana cost component
     * @param appearsInManaCosts  whether the symbol can appear in mana costs
     * @param manaValue           the numeric mana value contribution of the symbol; may be {@code null}
     * @param hybrid              whether the symbol is a hybrid mana symbol
     * @param phyrexian           whether the symbol is a Phyrexian mana symbol
     * @param cmc                 the converted mana cost contribution; may be {@code null}
     * @param funny               whether the symbol is considered “funny” (non-standard usage)
     * @param colors              the colors associated with the symbol
     * @param gathererAlternates  alternate representations used in Gatherer; may be {@code null}
     */
    public record SymbologyObjectRecord(
            @SerializedName("object") String type,
            String symbol,
            @Nullable @SerializedName("svg_uri") String svgUri,
            @Nullable @SerializedName("loose_variant") String looseVariant,
            String english,
            boolean transposable,
            @SerializedName("represents_mana") boolean representsMana,
            @SerializedName("appears_in_mana_costs") boolean appearsInManaCosts,
            @Nullable @SerializedName("mana_value") Double manaValue,
            boolean hybrid,
            boolean phyrexian,
            @Nullable Double cmc,
            boolean funny,
            String[] colors,
            @Nullable @SerializedName("gatherer_alternates") String[] gathererAlternates
    ) {}
}
