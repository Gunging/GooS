package gunging.ootilities.goob.ootilities;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.ootilities.utility.DoubleNumberRange;
import gunging.ootilities.goob.ootilities.utility.ToStringLambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A few methods to attempt to parse Strings into Numbers. Also, a few methods to parse Math into Strings.
 * <br><br>
 * These methods are designed to fail <b>silently</b>, use that to your advantage.
 * Basically, they will return <code>null</code> instead of throwing exceptions,
 * admitting even input that makes no sense without generating errors.
 * <br><br>
 * Considering it is pure java math, this always runs on the default GOOBEngine
 * and does not even query it. This class is completely standalone and consists
 * of only static methods.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class OotilityStrings implements GOOBFuel {
    
    //region Number Management
    /**
     * If given some value that ends in <code>.000</code> (any number of zeros),
     * it will remove the decimal point and the zeros.
     * If there is any decimal number, it will remove all zeros after it:
     * <br><br>
     * Examples:
     * <br><b><code>"1.0000"</code></b> will return <b><code>"1"</code></b>
     * <br><b><code>"1.000100"</code></b> will return <b><code>"1.0001"</code></b>
     * <br><br>
     * Intended to parse '8.0' as an integer value "8"; BRUH
     * <br><br>
     * If the value is not a number that ends in <code>.0000...</code>, it will return it unchanged.
     *
     * @param source A string that may represent a number that ends with a bunch of zeroes
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull
    public static String RemoveDecimalZeros(@NotNull String source) {

        // Does it have a decimal to begin with?
        if (source.contains(".")) {

            // Get
            String decimals = source.substring(source.lastIndexOf("."));

            // Find last nonzero char
            int lC = -1;

            // Evaluate all zeroes
            for (int c = 1; c < decimals.length(); c++) {

                // Get Char
                char ch = decimals.charAt(c);

                // Is it not a zero
                if (ch != '0') {

                    // AH cancel
                    lC = c;
                }
            }

            // Return thay
            return source.substring(0, source.lastIndexOf(".") + lC + 1);
        }

        // AH cancel
        return source;
    }

    /**
     * When you round a <code>double</code>, and try to use {@link String#valueOf(double)}, it
     * will show up as "<b><code>2.0</code></b>. I think that <code>.0</code> is ugly asf.
     * <br><br>
     * This method will remove such .0
     * <br><br>
     * Also, if the input is something like <code>2.03003</code>, and it rounds to 4 decimals as
     * <code>"2.0300"</code>, this will remove those pesky <code>00</code>s and return just <code>"2.03"</code>
     *
     * @param something The number you are rounding
     * @param decimals The maximum decimals to round to
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String ReadableRounding(double something, int decimals) {

        // Round to decimals ig
        return RemoveDecimalZeros(String.valueOf(OotilityNumbers.Round(something, decimals)));
    }

    /**
     * Say you have a big number of seconds you want to display to the user.
     * Well, this compresses it into minutes or hours depending on how big
     * this number is, attempting not to exceed 3 characters length.
     * <br><br>
     * So basically, <code>72</code> will be returned as <code>"72s"</code>,
     * but once you go into like <code>1800</code>, it will become <code>"30m"</code>
     *
     * @param seconds The number of seconds to transcribe.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String NicestTimeValueFrom(double seconds) {

        // If more than 1 minute
        if (seconds > 60) {

            // Is it greater than 1800?
            if (seconds > 1800) {

                // Dive by 1800
                double div1800 = seconds / 1800.0D;

                // Get the difference from an integer rounding [0-0.99]
                double difference = Math.round(div1800) - div1800;

                // If it was nice (within 9 minutes of half an hour, continue as hours)
                if (difference < 0.34) {

                    // Return as minutes alv
                    return ReadableRounding(OotilityNumbers.Round(seconds / 3600.0D, 1),1) + "h";
                }

                // Difference was kinda sensitive. Will evaluate as minutes I guess

                // BUT first, if it would hit 1000 minutes, force-convert to hours ~ with two decimal places :)
                if (seconds > 60000) {

                    // Return as minutes alv
                    return ReadableRounding(OotilityNumbers.Round(seconds / 3600.0D, 2), 1) + "h";
                }
            }

            // Dive by 30
            double div30 = seconds / 30.0D;

            // Get the difference from an integer rounding [0-0.99]
            double difference = Math.round(div30) - div30;

            // If it was nice (within 9 seconds of half a minute, continue as minutes)
            if (difference < 0.34) {

                // Return as minutes alv
                return ReadableRounding(OotilityNumbers.Round(seconds / 60.0D, 1), 1) + "m";
            }

            // Difference was kinda sensitive. Will use seconds

            // BUT first, if it would hit 1000 seconds, force-convert to seconds ~ with two decimal places :)
            if (seconds > 1000) {

                // Return as minutes alv
                return ReadableRounding(OotilityNumbers.Round(seconds / 60.0D, 2), 1) + "m";
            }
        }

        // Return as seconds alv
        return ReadableRounding(OotilityNumbers.Round(seconds, 1), 1) + "s";
    }

    /**
     * Only works well in the range -3999 to +3999
     *
     * @param value Number to turn to a roman numeral
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String ToRomanNumerals(int value) {

        // Zero did not exist back then .-.
        if (value == 0) { return "0"; }

        // Just go through this method and negate
        if (value < 0) { return "-" + ToRomanNumerals(-value); }

        // Get the greatest number applicable
        int gr8st = romanNumeralValues.floorKey(value);

        // Exact? That's it
        if (value == gr8st) { return romanNumeralValues.get(gr8st); }

        // Compute remainder
        return romanNumeralValues.get(gr8st) + ToRomanNumerals(value - gr8st);
    }

    /**
     * The roman numeral letters
     *
     * @since 1.0.0
     */
    private final static TreeMap<Integer, String> romanNumeralValues = new TreeMap<Integer, String>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};
    //endregion

    //region List Operations
    /**
     * Chops a long description into several parts (use for nice lore IDK)
     * @param colorPrefix Will be placed at the beginning of each line
     * @param longString Long string to be chopped
     * @param paragraphWide Number of characters maximum per line, will naturally
     *                      chop before the last space if possible.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static ArrayList<String> Chop(@NotNull String longString, int paragraphWide, @NotNull String colorPrefix) {

        // Ret
        ArrayList<String> ret = new ArrayList<>();
        boolean skip = false;

        // While longer
        while (longString.length() > paragraphWide) {

            // Skip
            skip = true;

            // Get the wide
            int idx = longString.lastIndexOf(" ", paragraphWide + 1);

            // Chop
            ret.add(colorPrefix + longString.substring(0, idx));

            // Update
            longString = longString.substring(idx + 1);

            // Add final.
            if (longString.length() <= paragraphWide) { ret.add(colorPrefix + longString); }
        }

        // Wasn't long at all
        if (!skip) { ret.add(colorPrefix + longString); }

        // That's it
        return ret;
    }

    /**
     * You see how the tab completer filters out commands as you write?
     * This method does exactly that.
     * @param source List of all the entries
     * @param filter Characters that the entries must match
     * @param ignoreCaps Should ignore caps?
     * @return The list, but filtered. Will be empty if nothing matched.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static ArrayList<String> SmartFilter(@NotNull ArrayList<String> source, @NotNull String filter, boolean ignoreCaps) {

        // Actual list
        @NotNull ArrayList<String> starts = new ArrayList<>();
        @NotNull ArrayList<String> contains = new ArrayList<>();

        // Lowercase
        if (ignoreCaps) { filter = filter.toLowerCase(); }

        // Filter the original list and build into the returned one
        for (String str : source) {

            // Lowercase
            if (ignoreCaps) { str = str.toLowerCase(); }

            // Does it start with? (More important, will display first)
            if (str.startsWith(filter)) {

                // Add
                starts.add(str);

                // Doesn't start with it, but does it contain them?
            } else if (str.contains(filter)) {

                // Add
                contains.add(str);
            }
        }

        // Combine arrays
        starts.addAll(contains);

        // That's it
        return starts;
    }

    /**
     * This thing just makes this component list into an array list.
     *
     * @param components All the things you want to add. <b><code>null</code> entries will be skipped</b>
     * @param <S> The type of components, obviously.
     *
     * @return An array list with all the things you specified (that may be empty
     *         if you didn't specify anything or all is <code>null</code>)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @SafeVarargs @NotNull public static <S> ArrayList<S> ToArrayList(S... components) {

        // Create
        ArrayList<S> ret = new ArrayList<>();

        // Add if non-null
        for (S comp : components) { if (comp != null) { ret.add(comp); } }

        // That's it
        return ret;
    }

    /**
     * This thing just makes this component list into an array list.
     *
     * @param append All the things you want to add. <b><code>null</code> entries will be skipped</b>
     * @param <S> The type of components, obviously.
     *
     * @return An array list plus all the things you specified.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @SafeVarargs @NotNull public static <S> ArrayList<S> AddAll(@Nullable ArrayList<S> list, @Nullable S... append) {

        // Null?
        if (list == null) { list = new ArrayList<>(); }

        // Add
        ArrayList<S> finalList = list;
        if (append == null) { return finalList; }
        Arrays.asList(append).forEach(s -> {if (s != null) { finalList.add(s); }});

        // That's it
        return finalList;
    }

    /**
     * Appends each string of a list of strings one after another.
     *
     * @param list A list containing several strings
     *
     * @param separator A separator to append in between members.
     *
     * @return A single string containing all the members of the list.
     *         <br><br>
     *         <code>null</code> strings are passed as <code>"null"</code>
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String CollapseList(@NotNull ArrayList<String> list, @NotNull String separator) {

        //Yes
        StringBuilder sb = new StringBuilder();
        boolean af = false;

        // Add every list
        for (String str : list) {

            if (str == null) { str = "null"; }

            // Append separator except before the very first one
            if (af) { sb.append(separator); }
            af = true;

            // Append string element
            sb.append(str);
        }

        // Bake
        return sb.toString();
    }

    /**
     * Ever wished to write your own toString method on the spot?
     * <br>
     * Well now you can with this handy transcriber.
     * <br><br>
     * Check this example out:
     * <br><br>
     * <code>
     * ArrayList itemNames = OotilityNumbers.transcribeList(itemsList, (o)->OotilityNumbers.getItemName( ((ItemStack)o) ));
     * </code>
     *
     * @param list List of objects
     * @param conversion Method to apply to each entry of the list
     *
     * @return A string list containing strings processed by the method you provided.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static ArrayList<String> TranscribeList(@NotNull List<?> list, @NotNull ToStringLambda conversion) {

        // Yes
        ArrayList<String> ret = new ArrayList<>();

        // Transcribe
        for (Object str : list) {

            // Yes
            ret.add(conversion.rewrite(str));
        }

        // That's it
        return ret;
    }

    /**
     * Basically {@link String#split(String)} method but:
     * <br> #1 Returns an ArrayList instead
     * <br> #2 If the string does not contain the sequence, the returned array will contain only the string itself.
     *
     * @param str String to split
     * @param seq Sequence to match
     * @return The string split by the sequence
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static ArrayList<String> Split(@NotNull String str, @NotNull String seq) {

        // If contained
        if (!str.contains(seq)) {

            // New with only one entry
            ArrayList<String> ret = new ArrayList<>();
            ret.add(str);
            return ret;

        } else {

            // Split that shit
            return ToArrayList(str.split(Pattern.quote(seq)));
        }

    }

    /**
     * Returns true if list 1 has every entry of list 2.
     * @param list1 The list that may have more entries than needed.
     * @param list2 The list that has all the must-have entries.
     *
     * @return If list 1 has at least all the entries of list 2
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static <S> boolean HasAll(@NotNull List<S> list1, @NotNull List<S> list2) {

        // A single missing and this is over!
        for (S str : list2) { if (!list1.contains(str)) { return false; } }

        // Success
        return true;
    }
    //endregion

    //region String Manipulation
    /**
     * Explained by itself
     *
     * @param inputString SOMETHING LIKE THIS
     * @return Something Like This
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String TitleCaseConversion(@NotNull String inputString) {

        /*
         * Clearly not my code, but not searching for this
         * online would be wasting my time reinventing the
         * wheel. Just look at that lol
         */

        if (inputString.length() == 0) { return ""; }

        if (inputString.length() == 1) { return inputString.toUpperCase(); }

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

        Stream.of(inputString.split(" ")).forEach(stringPart -> {
            if (stringPart.length() > 1)
                resultPlaceHolder.append(stringPart.substring(0, 1)
                                .toUpperCase())
                        .append(stringPart.substring(1)
                                .toLowerCase());
            else
                resultPlaceHolder.append(stringPart.toUpperCase());

            resultPlaceHolder.append(" ");
        });

        return resultPlaceHolder.toString().trim();
    }

    /**
     * Need not necessarily within {}s, will crop them out if they are there.
     *
     * @param source String that may be wrapped in {}s
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String UnwrapFromCurlyBrackets(@NotNull String source) {

        // Unwrap if Existing
        if (source.endsWith("}")) { source = source.substring(0, source.length() -1); }
        if (source.startsWith("{")) { source = source.substring(1); }

        return source;
    }
    //endregion

    //region Brackets Tab
    /**
     * A brackets tab is the name I came up with at 3am for a list between brackets.
     * <br>
     * Example (vanilla selectors): <code>@a[distance=..10,tag=Whatever,limit=4]</code>
     *
     * @param source The whole thing; Ex <code>@a[distance=..10,tag=Whatever,limit=4]</code>
     *
     * @param tag The tag you're interested in; Ex <code>limit</code>
     *
     * @return if there was no tag included, or incorrect format, null. Otherwise, the parsed value; Ex <code>4</code>
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static Integer IntegerFromBracketsTab(@NotNull String source, String tag) {

        // Parses
        return OotilityNumbers.IntegerParse(ValueFromBracketsTab(source, tag));
    }

    /**
     * A brackets tab is the name I came up with at 3am for a list between brackets.
     * <br>
     * Example (vanilla selectors): <code>@a[distance=..10,tag=Whatever]</code>
     *
     * @param source The whole thing; Ex <code>@a[distance=..10,tag=Whatever]</code>
     *
     * @param tag The tag you're interested in; Ex <code>distance</code>
     *
     * @return if there was no tag included, or incorrect format, null. Otherwise, a QuickNumberRange class parsing the value; Ex <code>..10</code>
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static DoubleNumberRange RangeFromBracketsTab(@NotNull String source, @NotNull String tag) {

        // Parse
        return DoubleNumberRange.GetFromString(ValueFromBracketsTab(source, tag));
    }

    /**
     * A brackets tab is the name I came up with at 3am for a list between brackets.
     * <br>
     * Example (vanilla selectors): <code>@a[distance=..10,tag=Whatever]</code>
     *
     * @param source The whole thing; Ex <code>@a[distance=..10,tag=Whatever]</code>
     *
     * @param tag The tag you're interested in; Ex <code>distance</code>
     *
     * @return if there was no tag included, or incorrect format, null. Otherwise, the string value; Ex <code>..10</code>
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static String ValueFromBracketsTab(@NotNull String source, @NotNull String tag) {

        int limitSt = source.indexOf(tag + "=");
        if (limitSt >= 0) //noinspection GrazieInspection
        {

            // Crop the hell of it
            String limitCropB4 = source.substring(limitSt + tag.length() + 1);

            // Find the end, may it be a , or a ]; Whichever comes first
            int limitCropEnd = -1;

            // Find closing curly bracket I guess; Must begin with a curly bracket for that.
            int curlyIndex = -1;
            if (limitCropB4.startsWith("{")) { curlyIndex = limitCropB4.indexOf("}");}
            if (curlyIndex == -1) { curlyIndex = 0; }

            // Get the index of a comma, starting after the closing bracket.
            int limitCropComma = limitCropB4.indexOf(",", curlyIndex);

            int limitCropClose = limitCropB4.indexOf("]");
            if (limitCropComma > 0) { limitCropEnd = limitCropComma; }
            if (limitCropClose > 0) { if (limitCropEnd > 0) { if (limitCropClose < limitCropEnd) { limitCropEnd = limitCropClose; } } else { limitCropEnd = limitCropClose; } }

            // I suppose that, if no comma nor ] was encountered, we may as well just not crop it
            if (limitCropEnd == -1) { return limitCropB4; }

            // Found an end? Crop
            return limitCropB4.substring(0, limitCropEnd);
        }

        return null;
    }
    //endregion
    
    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public OotilityStrings(@NotNull GOOBEngine engine) { this.engine = engine; }

    /**
     * The engine this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBEngine engine;


    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBEngine getEngine() { return engine; }
    //endregion
}
