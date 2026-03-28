package net.dzultra.jsa.cards.enums;

public enum Language {
    EN("en", "English"),
    ES("es", "Spanish"),
    FR("fr", "French"),
    DE("de", "German"),
    IT("it", "Italian"),
    PT("pt", "Portuguese"),
    JA("ja", "Japanese"),
    KO("ko", "Korean"),
    RU("ru", "Russian"),
    ZHS("zhs", "Chinese Simplified"),
    ZHT("zht", "Chinese Traditional");

    private final String code;
    private final String name;


    Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
