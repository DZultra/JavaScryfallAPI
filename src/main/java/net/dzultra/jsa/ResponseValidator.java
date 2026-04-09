package net.dzultra.jsa;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ResponseValidator {
    public static boolean isValidResponseSingle(Gson gson, String response, String type) {
        try {
            TypeRecord record = gson.fromJson(response, TypeRecord.class);
            return record.type().equals(type);
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    public static boolean isValidResponseDouble(Gson gson, String response, String type1, String type2) {
        DataTypeRecord record;
        try {
            record = gson.fromJson(response, DataTypeRecord.class);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return record.type().equals(type1) && record.data()[0].type().equals(type2);
    }

    public static boolean isValidSymbologyResponse(Gson gson, String response) {
        try {
            TypeRecord typeRecord = gson.fromJson(response, TypeRecord.class);
            if (typeRecord.type().equals("mana_cost")) {
                return true;
            } else if (typeRecord.type().equals("list")) {
                DataTypeRecord dataRecord = gson.fromJson(response, DataTypeRecord.class);
                return dataRecord.data()[0].type().equals("card_symbol");
            }
            return false;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
}
