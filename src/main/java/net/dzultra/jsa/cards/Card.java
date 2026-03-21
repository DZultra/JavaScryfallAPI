package net.dzultra.jsa.cards;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record Card(
        // -------- Core Card Fields --------
        @Nullable Integer arena_id,// This card’s Arena ID, if any. A large percentage of cards are not available on Arena and do not have this ID.
        @SerializedName("id") UUID scryfall_id, // A unique ID for this card in Scryfall’s database.
        String lang, // A language code for this printing.
        @Nullable Integer mtgo_id,// This card’s Magic Online ID (also known as the Catalog ID), if any. A large percentage of cards are not available on Magic Online and do not have this ID.
        @Nullable Integer mtgo_foil_id,// This card’s foil Magic Online ID (also known as the Catalog ID), if any. A large percentage of cards are not available on Magic Online and do not have this ID.
        @Nullable List<Integer> multiverse_ids,// This card’s multiverse IDs on Gatherer, if any, as an array of integers. Note that Scryfall includes many promo cards, tokens, and other esoteric objects that do not have these identifiers.
        @Nullable String resource_id, // This card’s Resource ID on Gatherer, if any.
        @Nullable Integer tcgplayer_id, // This card’s ID on TCGplayer’s API, also known as the productId.
        @Nullable Integer tcgplayer_etched_id,// This card’s ID on TCGplayer’s API, for its etched version if that version is a separate product.
        @Nullable Integer cardmarket_id, // This card’s ID on Cardmarket’s API, also known as the idProduct.
        @SerializedName("object") String type, // A content type for this object, should always be card.
        String layout, // A code for this card’s layout.
        @Nullable UUID oracle_id,// A unique ID for this card’s oracle identity. This value is consistent across reprinted card editions, and unique among different cards with the same name (tokens, Unstable variants, etc). Always present except for the reversible_card layout where it will be absent; oracle_id will be found on each face instead.
        URI prints_search_uri,// A link to where you can begin paginating all re/prints for this card on Scryfall’s API.
        URI rulings_uri, // A link to this card’s rulings list on Scryfall’s API.
        URI scryfall_uri, // A link to this card’s permapage on Scryfall’s website.
        URI uri, // A link to this card object on Scryfall’s API.

        // -------- Gameplay Fields --------
        @SerializedName("all_parts") @Nullable List<RelatedCard> related_cards,// If this card is closely related to other cards, this property will be an array with Related Card Objects.
        Float cmc, // The card’s mana value. Note that some funny cards have fractional mana costs.
        List<String> color_identity, // This card’s color identity.
        @Nullable List<String> color_indicator,// The colors in this card’s color indicator, if any. A null value for this field indicates the card does not have one.
        @Nullable List<String> colors,// This card’s colors, if the overall card has colors defined by the rules. Otherwise the colors will be on the card_faces objects, see below.
        @Nullable String defense, // This face’s defense, if any.
        @Nullable Integer edhrec_rank, // This card’s overall rank/popularity on EDHREC. Not all cards are ranked.
        @Nullable Boolean game_changer, // True if this card is on the Commander Game Changer list.
        @Nullable String hand_modifier,// This card’s hand modifier, if it is Vanguard card. This value will contain a delta, such as -1.
        List<String> keywords, // An array of keywords that this card uses, such as 'Flying' and 'Cumulative upkeep'.
        Legalities legalities,// An object describing the legality of this card across play formats. Possible legalities are legal, not_legal, restricted, and banned.
        @Nullable String life_modifier,// This card’s life modifier, if it is Vanguard card. This value will contain a delta, such as +2.
        @Nullable String loyalty,// This loyalty if any. Note that some cards have loyalties that are not numeric, such as X
        @Nullable String mana_cost,// The mana cost for this card. This value will be any empty string "" if the cost is absent. Remember that per the game rules, a missing mana cost and a mana cost of {0} are different values. Multi-faced cards will report this value in card faces.
        String name,// The name of this card. If this card has multiple faces, this field will contain both names separated by ␣//␣.
        @Nullable String oracle_text, // The Oracle text for this card, if any.
        @Nullable Integer penny_rank, // This card’s rank/popularity on Penny Dreadful. Not all cards are ranked.
        @Nullable String power,// This card’s power, if any. Note that some cards have powers that are not numeric, such as *.
        @Nullable List<String> produced_mana, // Colors of mana that this card could produce.
        Boolean reserved, // True if this card is on the Reserved List.
        @Nullable String toughness,// This card’s toughness, if any. Note that some cards have toughnesses that are not numeric, such as *.
        String type_line, // The type line of this card.

        // -------- Print Fields --------
        @Nullable String artist,// The name of the illustrator of this card. Newly spoiled cards may not have this field yet.
        List<UUID> artist_ids,// The IDs of the artists that illustrated this card. Newly spoiled cards may not have this field yet.
        @Nullable List<Integer> attraction_lights, // The lit Unfinity attractions lights on this card, if any.
        Boolean booster, // Whether this card is found in boosters.
        String border_color, // This card’s border color: black, white, borderless, yellow, silver, or gold.
        UUID card_back_id, // The Scryfall ID for the card back design present on this card.
        String collector_number,// This card’s collector number. Note that collector numbers can contain non-numeric characters, such as letters or ★.
        @Nullable Boolean content_warning, // True if you should consider avoiding use of this print downstream.
        Boolean digital, // True if this card was only released in a video game.
        List<String> finishes,// An array of computer-readable flags that indicate if this card can come in foil, nonfoil, or etched finishes.
        @Nullable String flavor_name, // The just-for-fun name printed on the card (such as for Godzilla series cards).
        @Nullable String flavor_text, // The flavor text, if any.
        @Nullable List<String> frame_effects, // This card’s frame effects, if any.
        String frame, // This card’s frame layout.
        Boolean full_art, // True if this card’s artwork is larger than normal.
        List<String> games,// A list of games that this card print is available in, paper, arena, mtgo, astral, and/or sega.
        Boolean highres_image, // True if this card’s imagery is high resolution.
        @Nullable UUID illustration_id,// A unique identifier for the card artwork that remains consistent across reprints. Newly spoiled cards may not have this field yet.
        String image_status,// A computer-readable indicator for the state of this card’s image, one of missing, placeholder, lowres, or highres_scan.
        @Nullable ImageURIs image_uris,// An object listing available imagery for this card. See the Card Imagery article for more information.
        Boolean oversized, // True if this card is oversized.
        Prices prices,// An object containing daily price information for this card, including usd, usd_foil, usd_etched, eur, eur_foil, eur_etched, and tix prices, as strings.
        @Nullable String printed_name, // The localized name printed on this card, if any.
        @Nullable String printed_text, // The localized text printed on this card, if any.
        @Nullable String printed_type_line, // The localized type line printed on this card, if
        Boolean promo, // True if this card is a promotional print.
        @Nullable List<String> promo_types,// An array of strings describing what categories of promo cards this card falls into.
        @Nullable PurchaseURIs purchaseURIs,// An object providing URIs to this card’s listing on major marketplaces. Omitted if the card is unpurchaseable.
        String rarity, // This card’s rarity. One of common, uncommon, rare, special, mythic, or bonus.
        RelatedURIs relatedURIs,// An object providing URIs to this card’s listing on other Magic: The Gathering online resources.
        Date released_at, // The date this card was first released.
        Boolean reprint, // True if this card is a reprint.
        URI scryfall_set_uri, // A link to this card’s set on Scryfall’s website.
        String set_name, // This card’s full set name.
        URI set_search_uri, // A link to where you can begin paginating this card’s set on the Scryfall API.
        String set_type, // The type of set this printing is in.
        URI set_uri, // A link to this card’s set object on Scryfall’s API.
        String set, // This card’s set code.
        UUID set_id, // This card’s Set object UUID.
        Boolean story_spotlight, // True if this card is a Story Spotlight.
        Boolean textless, // True if the card is printed without text.
        Boolean variation, // True if this card is a variation of another print.
        @Nullable UUID variation_of, // The printing ID of the printing this card is a variation of.
        @Nullable String security_stamp, // The security stamp on this card, if any. One of oval, triangle, acorn, circle, arena, or heart.
        @Nullable String watermark, //  This card’s watermark, if any.
        @Nullable PreviewSource preview, // An object describing this card’s preview source, if any.

        // -------- Card Face Field --------
        List<CardFace> card_faces
) {
    public record RelatedCard(
            @SerializedName("id") UUID scryfall_id, // An unique ID for this card in Scryfall’s database.
            @SerializedName("object") String type, // A content type for this object, should always be related_card.
            String component, // A field explaining what role this card plays in this relationship, one of token, meld_part, meld_result, or combo_piece.
            String name, // The name of this particular related card.
            String type_line, // The type line of this card.
            URI uri // 	A URI where you can retrieve a full object describing this card on Scryfall’s API.
    ) {}

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

    public record ImageURIs(
            URI small,
            URI normal,
            URI large,
            URI png,
            URI art_crop,
            URI border_crop
    ) {}

    public record Prices(
            @Nullable String usd,
            @Nullable String usd_foil,
            @Nullable String usd_etched,
            @Nullable String eur,
            @Nullable String eur_foil,
            @Nullable String eur_etched,
            @Nullable String tix
    ) {}

    public record PurchaseURIs(
            @Nullable URI tcgplayer,
            @Nullable URI cardmarket,
            @Nullable URI cardhoarder
    ) {}

    public record RelatedURIs(
            @Nullable URI gatherer,
            @Nullable URI tcgplayer_infinite_articles,
            @Nullable URI tcgplayer_infinite_decks,
            @Nullable URI edhrec
    ) {}

    public record PreviewSource(
            @Nullable URI source_uri,
            @Nullable String source,
            @Nullable String previewed_at
    ) {}

    public record CardFace(
            @Nullable String artist, // The name of the illustrator of this card face. Newly spoiled cards may not have this field yet.
            @Nullable UUID artist_id, // The ID of the illustrator of this card face. Newly spoiled cards may not have this field yet.
            @Nullable Float cmc, // The mana value of this particular face, if the card is reversible.
            @Nullable List<String> color_indicator,// The colors in this card’s color indicator, if any. A null value for this field indicates the card does not have one.
            @Nullable List<String> colors,// This card’s colors, if the overall card has colors defined by the rules. Otherwise the colors will be on the card_faces objects, see below.
            @Nullable String defense, // This face’s defense, if any.
            @Nullable String flavor_text, // The flavor text printed on this face, if any.
            @Nullable String illustration_id, // A unique identifier for the card artwork that remains consistent across reprints. Newly spoiled cards may not have this field yet.
            @Nullable ImageURIs image_uris, // An object providing URIs to imagery for this face, if this is a double-sided card. If this card is not double-sided, then the image_uris property will be part of the parent object instead.
            @Nullable String layout, // The layout of this card face, if the card is reversible.
            @Nullable String loyalty, // This face’s loyalty, if any.
            String mana_cost, // The mana cost for this face. This value will be any empty string "" if the cost is absent. Remember that per the game rules, a missing mana cost and a mana cost of {0} are different values.
            String name, // The name of this particular face.
            @SerializedName("object") String type, // A content type for this object, should always be card_face.
            @Nullable UUID oracle_id, // The Oracle ID of this particular face, if the card is reversible.
            @Nullable String oracle_text, // The Oracle text for this face, if any.
            @Nullable String power, // This face’s power, if any. Note that some cards have powers that are not numeric, such as *.
            @Nullable String printed_name, // The localized name printed on this card face, if any.
            @Nullable String printed_text, // The localized text printed on this card face, if any.
            @Nullable String printed_type_line, // The localized type line printed on this card face, if any.
            @Nullable String toughness, // This face’s toughness, if any.
            @Nullable String type_line, // The type line of this particular face, if the card is reversible.
            @Nullable String watermark //  This card face’s watermark, if any.
    ) implements CardRecord {}
}
