package net.dzultra.jsa.catalogs;

/**
 * Represents the available Scryfall catalog endpoints.
 * <p>
 * Each enum constant maps to a specific catalog resource path used to retrieve
 * predefined lists of data such as card names, types, keywords, and other
 * structured reference values from the Scryfall API.
 */
public enum CatalogType {

    /** Catalog of all card names. */
    CARD_NAMES("/catalog/card-names"),

    /** Catalog of all artist names. */
    ARTIST_NAMES("/catalog/artist-names"),

    /** Catalog of words used in card text (word bank). */
    WORD_BANK("/catalog/word-bank"),

    /** Catalog of Magic supertype values. */
    SUPERTYPES("/catalog/supertypes"),

    /** Catalog of card types. */
    CARD_TYPES("/catalog/card-types"),

    /** Catalog of artifact subtypes. */
    ARTIFACT_TYPES("/catalog/artifact-types"),

    /** Catalog of battle subtypes. */
    BATTLE_TYPES("/catalog/battle-types"),

    /** Catalog of creature subtypes. */
    CREATURE_TYPES("/catalog/creature-types"),

    /** Catalog of enchantment subtypes. */
    ENCHANTMENT_TYPES("/catalog/enchantment-types"),

    /** Catalog of land subtypes. */
    LAND_TYPES("/catalog/land-types"),

    /** Catalog of planeswalker subtypes. */
    PLANESWALKER_TYPES("/catalog/planeswalker-types"),

    /** Catalog of spell-related types. */
    SPELL_TYPES("/catalog/spell-types"),

    /** Catalog of power values used on cards. */
    POWERS("/catalog/powers"),

    /** Catalog of toughness values used on cards. */
    TOUGHNESSES("/catalog/toughnesses"),

    /** Catalog of loyalty values used on planeswalkers. */
    LOYALTIES("/catalog/loyalties"),

    /** Catalog of keyword abilities. */
    KEYWORD_ABILITIES("/catalog/keyword-abilities"),

    /** Catalog of keyword actions. */
    KEYWORD_ACTIONS("/catalog/keyword-actions"),

    /** Catalog of ability words used in rules text. */
    ABILITY_WORDS("/catalog/ability-words"),

    /** Catalog of flavor words used in card text. */
    FLAVOR_WORDS("/catalog/flavor-words"),

    /** Catalog of watermark identifiers. */
    WATERMARKS("/catalog/watermarks");

    /**
     * The API endpoint path associated with this catalog type.
     */
    private final String endpoint;

    /**
     * Constructs a {@code CatalogType} with its associated API endpoint.
     *
     * @param endpoint the relative API path for this catalog
     */
    CatalogType(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Returns the API endpoint path for this catalog type.
     *
     * @return the catalog endpoint string
     */
    public String getEndpoint() {
        return endpoint;
    }
}