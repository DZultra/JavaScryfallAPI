package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Magic: The Gathering card returned by the Scryfall API.
 * <p>
 * This record contains all metadata, gameplay information, printing details,
 * and optional card face data for both single-faced and multi-faced cards.
 * It is a direct mapping of the Scryfall card object.
 */
public record Card(
        // -------- Core Card Fields --------
        @Nullable Integer arena_id,
        @SerializedName("id") UUID scryfall_id,
        String lang,
        @Nullable Integer mtgo_id,
        @Nullable Integer mtgo_foil_id,
        @Nullable List<Integer> multiverse_ids,
        @Nullable String resource_id,
        @Nullable Integer tcgplayer_id,
        @Nullable Integer tcgplayer_etched_id,
        @Nullable Integer cardmarket_id,
        @SerializedName("object") String type,
        String layout,
        @Nullable UUID oracle_id,
        URI prints_search_uri,
        URI rulings_uri,
        URI scryfall_uri,
        URI uri,

        // -------- Gameplay Fields --------
        @SerializedName("all_parts") @Nullable List<RelatedCard> related_cards,
        Float cmc,
        List<String> color_identity,
        @Nullable List<String> color_indicator,
        @Nullable List<String> colors,
        @Nullable String defense,
        @Nullable Integer edhrec_rank,
        @Nullable Boolean game_changer,
        @Nullable String hand_modifier,
        List<String> keywords,
        Legalities legalities,
        @Nullable String life_modifier,
        @Nullable String loyalty,
        @Nullable String mana_cost,
        String name,
        @Nullable String oracle_text,
        @Nullable Integer penny_rank,
        @Nullable String power,
        @Nullable List<String> produced_mana,
        Boolean reserved,
        @Nullable String toughness,
        String type_line,

        // -------- Print Fields --------
        @Nullable String artist,
        List<UUID> artist_ids,
        @Nullable List<Integer> attraction_lights,
        Boolean booster,
        String border_color,
        UUID card_back_id,
        String collector_number,
        @Nullable Boolean content_warning,
        Boolean digital,
        List<String> finishes,
        @Nullable String flavor_name,
        @Nullable String flavor_text,
        @Nullable List<String> frame_effects,
        String frame,
        Boolean full_art,
        List<String> games,
        Boolean highres_image,
        @Nullable UUID illustration_id,
        String image_status,
        @Nullable ImageURIs image_uris,
        Boolean oversized,
        Prices prices,
        @Nullable String printed_name,
        @Nullable String printed_text,
        @Nullable String printed_type_line,
        Boolean promo,
        @Nullable List<String> promo_types,
        @Nullable PurchaseURIs purchaseURIs,
        String rarity,
        RelatedURIs relatedURIs,
        Date released_at,
        Boolean reprint,
        URI scryfall_set_uri,
        String set_name,
        URI set_search_uri,
        String set_type,
        URI set_uri,
        String set,
        UUID set_id,
        Boolean story_spotlight,
        Boolean textless,
        Boolean variation,
        @Nullable UUID variation_of,
        @Nullable String security_stamp,
        @Nullable String watermark,
        @Nullable PreviewSource preview,

        // -------- Card Face Field --------
        List<CardFace> card_faces
) {

    /**
     * Represents a card related to another card (tokens, melds, combos, etc.).
     */
    public record RelatedCard(
            @SerializedName("id") UUID scryfall_id,
            @SerializedName("object") String type,
            String component,
            String name,
            String type_line,
            URI uri
    ) {}

    /**
     * Contains legality information for a card across different game formats.
     */
    public record Legalities(
            String standard,
            String future,
            String historic,
            String timeless,
            String gladiator,
            String pioneer,
            String modern,
            String legacy,
            String pauper,
            String vintage,
            String penny,
            String commander,
            String oathbreaker,
            String standardbrawl,
            String brawl,
            String alchemy,
            String paupercommander,
            String duel,
            String oldschool,
            String premodern,
            String predh
    ) {}

    /**
     * Contains image URIs for different resolutions of a card.
     */
    public record ImageURIs(
            URI small,
            URI normal,
            URI large,
            URI png,
            URI art_crop,
            URI border_crop
    ) {}

    /**
     * Represents pricing information for a card across different marketplaces.
     */
    public record Prices(
            @Nullable String usd,
            @Nullable String usd_foil,
            @Nullable String usd_etched,
            @Nullable String eur,
            @Nullable String eur_foil,
            @Nullable String eur_etched,
            @Nullable String tix
    ) {}

    /**
     * External marketplace purchase links for a card.
     */
    public record PurchaseURIs(
            @Nullable URI tcgplayer,
            @Nullable URI cardmarket,
            @Nullable URI cardhoarder
    ) {}

    /**
     * External references to related card resources.
     */
    public record RelatedURIs(
            @Nullable URI gatherer,
            @Nullable URI tcgplayer_infinite_articles,
            @Nullable URI tcgplayer_infinite_decks,
            @Nullable URI edhrec
    ) {}

    /**
     * Preview metadata for spoiler cards.
     */
    public record PreviewSource(
            @Nullable URI source_uri,
            @Nullable String source,
            @Nullable String previewed_at
    ) {}

    /**
     * Represents a single face of a multi-faced card.
     */
    public record CardFace(
            @Nullable String artist,
            @Nullable UUID artist_id,
            @Nullable Float cmc,
            @Nullable List<String> color_indicator,
            @Nullable List<String> colors,
            @Nullable String defense,
            @Nullable String flavor_text,
            @Nullable String illustration_id,
            @Nullable ImageURIs image_uris,
            @Nullable String layout,
            @Nullable String loyalty,
            String mana_cost,
            String name,
            @SerializedName("object") String type,
            @Nullable UUID oracle_id,
            @Nullable String oracle_text,
            @Nullable String power,
            @Nullable String printed_name,
            @Nullable String printed_text,
            @Nullable String printed_type_line,
            @Nullable String toughness,
            @Nullable String type_line,
            @Nullable String watermark
    ) implements CardRecord {}
}