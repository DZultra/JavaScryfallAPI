package net.dzultra.jsa.sets;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * Represents a Magic: The Gathering set as returned by the Scryfall API.
 * <p>
 * A set contains metadata about cards release, including their identifiers,
 * release information, and various properties describing their composition
 * and availability across platforms.
 *
 * @param type              the type of the returned object (typically {@code "set"})
 * @param id                the unique identifier of the set
 * @param code              the three-to-five letter code identifying the set
 * @param mtgo_code         the set code used on Magic: The Gathering Online; may be {@code null}
 * @param arena_code        the set code used on MTG Arena; may be {@code null}
 * @param tcgplayer_id      the numeric identifier used by TCGPlayer; may be {@code null}
 * @param name              the full name of the set
 * @param set_type          the category of the set (e.g., {@code "core"}, {@code "expansion"})
 * @param released_at       the release date of the set; may be {@code null}
 * @param block_code        the code of the block this set belongs to; may be {@code null}
 * @param block             the name of the block this set belongs to; may be {@code null}
 * @param parent_set_code   the code of the parent set, if applicable; may be {@code null}
 * @param card_count        the number of cards in the set
 * @param printed_size      the total number of printed cards, if different from {@code card_count}; may be {@code null}
 * @param digital           whether the set is only available in digital form
 * @param foil_only         whether the set contains only foil cards
 * @param nonfoil_only      whether the set contains only non-foil cards
 * @param scryfall_uri      the URI to the set’s page on Scryfall
 * @param uri               the API URI for retrieving this set
 * @param icon_svg_uri      the URI to an SVG icon representing the set
 * @param search_uri        the API URI for searching cards within this set
 */
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