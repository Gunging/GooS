package gunging.ootilities.goob.ootilities;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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
public class OotilityNumbers implements GOOBFuel {

    //region The Purest Math Parsing
    /*
     *   Try-Parsers
     */
    /**
     * Is this either <code>true</code> or <code>false</code>? (Ignores caps)
     *
     * @param b Any text piece that may not even exist.
     * @return <b><code>true</code></b> if and only if the string parses into a boolean value
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean BooleanTryParse(@Nullable String b) {
        // Nothing straight up does not parse
        if (b == null) { return false; }

        // Attempt
        return b.equalsIgnoreCase("true") || b.equalsIgnoreCase("false");
    }
    /**
     * Can you parse a double value from this?
     *
     * @param d Any text piece that may not even exist.
     * @return <b><code>true</code></b> if and only if the string parses into a number
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean DoubleTryParse(@Nullable String d) { return (DoubleParse(d) != null); }
    /**
     * Can you parse an int value from this?
     * <br>This one is not as sensitive as {@link Integer#parseInt(String)},
     * <b>this will actually read an integer</b> value from a number with trailing
     * decimal zeroes like <code>3.0000</code><br>
     *
     * @param i Any text piece that may not even exist.
     * @return <b><code>true</code></b> if and only if the string parses into a number.
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean IntTryParse(@Nullable String i) { return (IntegerParse(i) != null);}

    /*
     *   Straight up parsing
     */
    /**
     * Reads a string saying <code>true</code> or <code>false</code> (ignoring caps).
     * @return <code>null</code> in case of any error, or your boolean value.
     * @param b Any text piece that may not even exist.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static Boolean BooleanParse(@Nullable String b) {

        // Straight up no
        if (b == null) { return null;}

        // Well does it work?
        if (b.equalsIgnoreCase("true")) { return true;
        } else if (b.equalsIgnoreCase("false")) { return false; }

        // Nope
        return null;
    }
    /**
     * Straight up {@link Double#parseDouble(String)}.
     * <br><br>
     * However, instead of throwing an exception, this method will return <code>null</code>.
     * @return <code>null</code> in case of any error, or your double value.
     * @param d Any text piece that may not even exist.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static Double DoubleParse(@Nullable String d) {

        // Straight up no
        if (d == null) { return null; }

        // Attempt
        try { return Double.parseDouble(d);

            // Nope
        } catch (NumberFormatException e) { return null; }
    }
    /**
     * Parses an int from a string even if it contains a decimal point (But it must be followed only by zeros)
     * @return <code>null</code> in case of any error, or your integer value.
     * @param i Any text piece that may not even exist.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static Integer IntegerParse(@Nullable String i) {
        // No more null
        if (i == null) { return null; }

        // Well attempt to parse that...
        try { return Integer.parseInt(OotilityStrings.RemoveDecimalZeros(i));

            // That's an L
        } catch (NumberFormatException ignored) { return null; }
    }

    /**
     * Can this string represent a UUID? If so, return it as a UUID!
     *
     * @param anything Any string that may not even exist.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static UUID UUIDParse(@Nullable String anything) {

        // Straight up no
        if (anything == null) { return null; }

        // Correct Format?
        if (anything.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {

            // Return thay
            return UUID.fromString(anything);
        }

        // No
        return null;
    }
    //endregion

    //region Math Utilities
    /**
     * Rounds a double to any amount of decimal places.
     * 
     * @param number Number to round
     * @param decimals Decimals to round it to
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static double Round(double number, int decimals) {
        long rounded = Math.round(number * Math.pow(10, decimals));
        return rounded / Math.pow(10, decimals);
    }
    /**
     * Rounds a double into an integer
     * 
     * @param number Number to round to an integer
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static int Round(double number) { return (int) Math.round(number); }
    /**
     * Floors a double to any amount of decimal places.
     * 
     * @param number Number to round toward negative infinity
     * @param decimals Decimals to round to
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static double Floor(double number, int decimals) {
        double rounded = Math.floor(number * Math.pow(10, decimals));
        return rounded / Math.pow(10, decimals);
    }
    /**
     * Floors a double into an integer
     * 
     * @param number Number to floor toward negative infinity
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static int Floor(double number) { return (int) Math.floor(number); }
    /**
     * Ceils a double to any amount of decimal places.
     *
     * @param number Number to round toward positive infinity
     * @param decimals Decimals to round to
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static double Ceil(double number, int decimals) {
        double rounded = Math.ceil(number * Math.pow(10, decimals));
        return rounded / Math.pow(10, decimals);
    }
    /**
     * Ceils a double into an integer
     *
     * @param number Number to round toward positive infinity
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static int Ceil(double number) { return (int) Math.ceil(number); }

    /**
     * Returns:
     * <br><b><code>1</code></b> if true
     * <br><b><code>0</code></b> if false
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static int IntegerParse(boolean value) { if (value) { return 1; } else { return  0; } }
    /**
     * Is this integer equal to <code>0</code>?
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean BooleanParse(int value) { return value != 0; }
    /**
     * Is this integer equal to <code>0.0</code>?
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean BooleanParse(double value) { return value != 0D; }

    /**
     * RNG Roll
     * @param decimal Chance where 0 = 0% and 1 = 100%
     * @return <code>true</code> if roll succeeds
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean RollSuccess(double decimal) {

        // If a number between 0 and 1 is less than the provided
        return Math.random() <= decimal;
    }
    /**
     * RNG Roll
     * @param percent Chance where 0 = 0% and 100 = 100%
     * @return <code>true</code> if roll succeeds
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean RollSuccessPercent(double percent) {

        // If a number between 0 and 1 is less than the provided
        return Math.random() <= (percent / 100D);
    }
    /**
     * Get a double number between these two
     * @param minInclusive Minimum range
     * @param maxExclusive Maximum range
     * @return A number between the two specified
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static double RandomRange(double minInclusive, double maxExclusive) {

        // Scale to the range of them and add the min range.
        return (Math.random() * (maxExclusive - minInclusive)) + minInclusive;
    }
    //endregion

    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public OotilityNumbers(@NotNull GOOBEngine engine) { this.engine = engine; }

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
