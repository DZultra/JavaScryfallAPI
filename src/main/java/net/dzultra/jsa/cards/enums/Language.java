package net.dzultra.jsa.cards.enums;

/**
 * Represents supported card language codes used by the Scryfall API.
 * <p>
 * Each enum value maps to a language identifier used in card queries,
 * along with a human-readable display name.
 */
public enum Language {

    /** English language cards. */
    EN("en", "English"),

    /** Spanish language cards. */
    ES("es", "Spanish"),

    /** French language cards. */
    FR("fr", "French"),

    /** German language cards. */
    DE("de", "German"),

    /** Italian language cards. */
    IT("it", "Italian"),

    /** Portuguese language cards. */
    PT("pt", "Portuguese"),

    /** Japanese language cards. */
    JA("ja", "Japanese"),

    /** Korean language cards. */
    KO("ko", "Korean"),

    /** Russian language cards. */
    RU("ru", "Russian"),

    /** Simplified Chinese language cards. */
    ZHS("zhs", "Chinese Simplified"),

    /** Traditional Chinese language cards. */
    ZHT("zht", "Chinese Traditional");

    /**
     * ISO-style language code used in API requests.
     */
    private final String code;

    /**
     * Human-readable language name.
     */
    private final String name;

    Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Returns the API language code used by Scryfall.
     *
     * @return language code string (e.g., "en", "de", "ja")
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns a human-readable name of the language.
     *
     * @return language display name
     */
    public String getName() {
        return name;
    }
}