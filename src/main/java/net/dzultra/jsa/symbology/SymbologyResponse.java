package net.dzultra.jsa.symbology;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public record SymbologyResponse (
    @SerializedName("object") String type,
    boolean has_more,
    SymbologyObjectRecord[] data
) {
    public record SymbologyObjectRecord(
            @SerializedName("object") String object,
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
