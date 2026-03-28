package net.dzultra.jsa;

import com.google.gson.Gson;

public class ResponseValidator {
    public static boolean isValidResponseSingle(Gson gson, String response, String type) {
        TypeRecord record = gson.fromJson(response, TypeRecord.class);
        return record.type().equals(type);
    }

    public static boolean isValidResponseDouble(Gson gson, String response, String type1, String type2) {
        DataTypeRecord record = gson.fromJson(response, DataTypeRecord.class);
        return record.type().equals(type1) && record.data()[0].type().equals(type2);
    }

    public static boolean isValidSymbologyResponse(Gson gson, String response) {
        return (gson.fromJson(response, TypeRecord.class).type().equals("list")
                && gson.fromJson(response, DataTypeRecord.class).data()[0].type().equals("card_symbol"))
                || gson.fromJson(response, TypeRecord.class).type().equals("mana_cost");
    }
}
