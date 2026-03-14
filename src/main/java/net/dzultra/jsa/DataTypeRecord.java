package net.dzultra.jsa;

import com.google.gson.annotations.SerializedName;

public record DataTypeRecord(
        @SerializedName("object") String type,
        TypeRecord[] data
) {}
