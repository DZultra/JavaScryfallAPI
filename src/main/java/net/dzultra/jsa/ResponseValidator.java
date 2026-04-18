package net.dzultra.jsa;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Utility class for validating JSON responses from the Scryfall API.
 * <p>
 * This class provides helper methods to verify whether a given JSON response
 * matches expected structural and type constraints by deserializing it into
 * predefined record types.
 */
public class ResponseValidator {

    /**
     * Validates whether the given JSON response contains a top-level object
     * with the specified {@code type}.
     * <p>
     * The response is deserialized into a {@link TypeRecord}, and the
     * {@code type} field is compared against the expected value.
     *
     * @param gson     the {@link Gson} instance used for deserialization
     * @param response the raw JSON response as a string
     * @param type     the expected value of the {@code type} field
     * @return {@code true} if the response is valid and matches the expected type;
     *         {@code false} if parsing fails or the type does not match
     */
    public static boolean isValidResponseSingle(Gson gson, String response, String type) {
        try {
            TypeRecord record = gson.fromJson(response, TypeRecord.class);
            return record.type().equals(type);
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    /**
     * Validates whether the given JSON response contains a top-level object
     * with a specific {@code type}, and a nested {@code data} array whose
     * first element has a specific secondary type.
     * <p>
     * The response is deserialized into a {@link DataTypeRecord}, and both
     * the top-level {@code type} and the {@code type} of the first element
     * in the {@code data} array are validated.
     *
     * @param gson     the {@link Gson} instance used for deserialization
     * @param response the raw JSON response as a string
     * @param type1    the expected top-level {@code type}
     * @param type2    the expected {@code type} of the first element in {@code data}
     * @return {@code true} if both type checks pass;
     *         {@code false} if parsing fails or any type does not match
     */
    public static boolean isValidResponseDouble(Gson gson, String response, String type1, String type2) {
        DataTypeRecord record;
        try {
            record = gson.fromJson(response, DataTypeRecord.class);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return record.type().equals(type1) && record.data()[0].type().equals(type2);
    }

    /**
     * Validates whether the given JSON response represents a valid symbology response.
     * <p>
     * A response is considered valid if:
     * <ul>
     *     <li>The top-level {@code type} is {@code "mana_cost"}, or</li>
     *     <li>The top-level {@code type} is {@code "list"} and the first element
     *         in the {@code data} array has type {@code "card_symbol"}.</li>
     * </ul>
     *
     * @param gson     the {@link Gson} instance used for deserialization
     * @param response the raw JSON response as a string
     * @return {@code true} if the response matches the expected symbology formats;
     *         {@code false} if parsing fails or the structure is invalid
     */
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
