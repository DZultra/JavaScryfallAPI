package net.dzultra.jsa.sets;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a paginated list of Magic: The Gathering sets returned by the Scryfall API.
 * <p>
 * This response is typically used when retrieving multiple sets at once. It includes
 * pagination metadata and an array of {@link MTGSet} objects.
 *
 * @param type    the type of the returned object (typically {@code "list"})
 * @param has_more indicates whether additional pages of results are available
 * @param sets    an array of {@link MTGSet} objects contained in this response
 */
public record MTGSetList(
        @SerializedName("object") String type,
        boolean has_more,
        @SerializedName("data") MTGSet[] sets
) implements SetRecord {}