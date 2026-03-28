package net.dzultra.jsa.sets;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public record MTGSet(
        @SerializedName("object") String type,
        UUID id,
        String code,
        @Nullable String mtgo_code,
        @Nullable String arena_code,
        @Nullable Integer tcgplayer_id,
        String name,
        String set_type,
        @Nullable Date released_at,
        @Nullable String block_code,
        @Nullable String block,
        @Nullable String parent_set_code,
        Integer card_count,
        @Nullable Integer printed_size,
        Boolean digital,
        Boolean foil_only,
        Boolean nonfoil_only,
        URI scryfall_uri,
        URI uri,
        URI icon_svg_uri,
        URI search_uri
) implements SetRecord {}
