package net.dzultra.jsa.sets;

/**
 * Represents the different types of Magic: The Gathering sets as defined by the Scryfall API.
 * <p>
 * Each enum constant maps to a raw string value used in API responses and can be used
 * to categorize {@link MTGSet} instances by their release or functional type.
 */
public enum SetType {

    /** Core sets in the Magic: The Gathering product line. */
    CORE("core"),

    /** Expansion sets that extend existing gameplay mechanics and card pools. */
    EXPANSION("expansion"),

    /** Masters sets featuring reprints of powerful cards. */
    MASTERS("masters"),

    /** Eternal format-focused sets, legal in non-rotating formats. */
    ETERNAL("eternal"),

    /** Arena-exclusive Alchemy digital sets. */
    ALCHEMY("alchemy"),

    /** Special showcase sets featuring premium or collectible cards. */
    MASTERPIECE("masterpiece"),

    /** Arsenal-style supplementary product sets. */
    ARSENAL("arsenal"),

    /** Sets from the Vault series of curated reprints. */
    FROM_THE_VAULT("from_the_vault"),

    /** Spellbook promotional or themed reprint sets. */
    SPELLBOOK("spellbook"),

    /** Premium deck products such as Commander preconstructed decks. */
    PREMIUM_DECK("premium_deck"),

    /** Duel Deck products featuring prebuilt opposing decks. */
    DUEL_DECK("duel_deck"),

    /** Draft Innovation sets designed for limited play formats. */
    DRAFT_INNOVATION("draft_innovation"),

    /** Treasure Chest digital-only reward sets. */
    TREASURE_CHEST("treasure_chest"),

    /** Commander-focused supplemental sets. */
    COMMANDER("commander"),

    /** Planechase multiplayer variant sets. */
    PLANECHASE("planechase"),

    /** Archenemy multiplayer villain deck sets. */
    ARCHENEMY("archenemy"),

    /** Vanguard variant format sets. */
    VANGUARD("vanguard"),

    /** Un-set or joke/fun-themed sets. */
    FUNNY("funny"),

    /** Starter-level sets designed for new players. */
    STARTER("starter"),

    /** Box promotional sets or sealed product tie-ins. */
    BOX("box"),

    /** Promotional distribution sets (e.g., convention promos). */
    PROMO("promo"),

    /** Token-only sets used for gameplay representation. */
    TOKEN("token"),

    /** Memorabilia or non-gameplay collectible sets. */
    MEMORABILIA("memorabilia"),

    /** Mini-game or supplemental game mode sets. */
    MINIGAME("minigame");

    private final String name;

    /**
     * Creates a {@code SetType} with its associated API string value.
     *
     * @param name the raw string representation used by the Scryfall API
     */
    SetType(String name) {
        this.name = name;
    }

    /**
     * Returns the raw string value used in API responses.
     *
     * @return the API string representation of this set type
     */
    public String getValue() {
        return name;
    }

    /**
     * Converts a raw API string into a corresponding {@code SetType}.
     *
     * @param name the raw set type string
     * @return the matching {@code SetType}, or {@code null} if no match is found
     * @throws IllegalArgumentException when the input string does not correspond to any known set type
     */
    public static SetType fromString(String name) {
        for (SetType type : values()) {
            if (type.getValue().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
}