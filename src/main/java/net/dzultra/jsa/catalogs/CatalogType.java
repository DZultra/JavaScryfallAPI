package net.dzultra.jsa.catalogs;

public enum CatalogType {
    CARD_NAMES("/catalog/card-names"),
    ARTIST_NAMES("/catalog/artist-names"),
    WORD_BANK("/catalog/word-bank"),
    SUPERTYPES("/catalog/supertypes"),
    CARD_TYPES("/catalog/card-types"),
    ARTIFACT_TYPES("/catalog/artifact-types"),
    BATTLE_TYPES("/catalog/battle-types"),
    CREATURE_TYPES("/catalog/creature-types"),
    ENCHANTMENT_TYPES("/catalog/enchantment-types"),
    LAND_TYPES("/catalog/land-types"),
    PLANESWALKER_TYPES("/catalog/planeswalker-types"),
    SPELL_TYPES("/catalog/spell-types"),
    POWERS("/catalog/powers"),
    TOUGHNESSES("/catalog/thoughnesses"),
    LOYALTIES("/catalog/loyalties"),
    KEYWORD_ABILITIES("/catalog/keyword-abilities"),
    KEYWORD_ACTIONS("/catalog/keyword-actions"),
    ABILITY_WORDS("/catalog/ability-words"),
    FLAVOR_WORDS("/catalog/flavor-words"),
    WATERMARKS("/catalog/watermarks");

    private final String endpoint;

    CatalogType(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
