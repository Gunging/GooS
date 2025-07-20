package gunging.ootilities.goob.ootilities;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.ootilities.friendly.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Static methods to use the Friendly Feedback system effectively
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class OotilityFriend implements GOOBFuel {

    /**
     * @param ffp Where to write this message to
     * @param category The category this message belongs to
     * @param message The message being sent
     *
     * @author Gunging
     * @since 1.0.0
     */
    public static void Log(@Nullable FriendlyFeedbackProvider ffp, @NotNull FriendlyFeedbackCategory category, @Nullable FriendlyFeedbackMessage message) {
        if (ffp == null || message == null) { return; }
        ffp.log(category, message);
    }
    /**
     * @param ffp Where to write this message to.
     * @param category The category this message belongs to.
     * @param message The message being sent.
     * @param replaces Objects to replace {n} signs in the message.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public static void Log(@Nullable FriendlyFeedbackProvider ffp, @NotNull FriendlyFeedbackCategory category, @Nullable String message, @Nullable Object... replaces) {
        if (ffp == null || message == null) { return; }

        // Build message according to the platform
        FriendlyFeedbackMessage built;
        if (ffp instanceof GOOBFuel) {
            built = ((GOOBFuel) ffp).getEngine().getOotilityFriend().buildMessage(message, replaces);

            // Or default
        } else { built = BuildMessage(message, replaces); }

        // Log
        ffp.log(category, built);
    }

    //region GooS Default Static
    /**
     * @return The default instance of this class being called.
     *
     * @since 1.0.0
     * @author Gunging
     */
    static @NotNull OotilityFriend defaultInstance() {
        return GungingOotilitiesBase.getEngine().getOotilityFriend();
    }
    //endregion

    //region Implementation Wrapping
    /**
     * @param message The raw message to wrap as this implementation's Friendly Feedback Message
     *
     * @return This message but as a Friendly Feedback object.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract FriendlyFeedbackMessage buildMessage(@NotNull String message);

    /**
     * @return The default capability of this engine to print out messages.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract FriendlyReproducer getDefaultReproducer();

    /**
     * @param reproducer The new Friendly Reproducer to print messages to by default.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setDefaultReproducer(@NotNull FriendlyReproducer reproducer);

    /**
     * @return A freshly-created provider to work with.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract FriendlyFeedbackProvider newFFP();

    /**
     * @return A freshly-created provider to work with, with Auto-Reproduction enabled using
     *         the {@link #getDefaultReproducer()} if it supports auto reproducing logs.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public FriendlyFeedbackProvider newFFPAuto() {
        FriendlyFeedbackProvider ret = newFFP();

        // Try to use the default reproducer
        if (getDefaultReproducer() instanceof FriendlySimpleReproducer) {
            ret.enableAutoSender((FriendlySimpleReproducer) getDefaultReproducer());
        }

        // Done
        return ret;
    }

    /**
     * @return A freshly-created provider to work with, with Auto-Reproduction enabled.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public FriendlyFeedbackProvider newFFPAuto(@Nullable FriendlySimpleReproducer reproducer) {
        FriendlyFeedbackProvider ret = newFFP();
        ret.enableAutoSender(reproducer);
        return ret;
    }
    //endregion

    //region Static Constants
    /**
     * The enum of existing friendly feedback categories.
     *
     * @since 1.0.0
     */
    @Nullable static FriendlyFeedbackCategory[] categories;

    /**
     * @return An array with all the Friendly Feedback categories.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static FriendlyFeedbackCategory[] AllCategories() {

        // Fill categories array
        if (categories == null) {
            ArrayList<FriendlyFeedbackCategory> cats = new ArrayList<>();
            for (FriendlyFeedbackCategory cat : FriendlyFeedbackCategory.values()) { if (cat != null) { cats.add(cat); } }
            categories = new FriendlyFeedbackCategory[cats.size()];
            categories = cats.toArray(categories); }

        // That's what we needed
        //noinspection NullableProblems
        return categories;
    }

    /**
     * The characters that follow a '$' that are considered a Friendly Feedback palette code
     *
     * @since 1.0.0
     */
    private static final char[] paletteCodes = { 'b', 'e', 'd', 'u', 'p', 'r', 's', 'f' };

    /**
     * @return The characters that follow a '$' that are considered a Friendly Feedback palette code
     *
     * @author Gunging
     * @since 1.0.0
     */
    public static char[] GetPaletteCodes() { return paletteCodes; }
    //endregion

    //region Message Parsing
    /**
     * For example, the message <b>"Expected a number instead of {0}"</b> will
     * replace that <b>"{0}"</b> with the zeroth (first) element in the replaces array.
     *
     * @param message The message to be displayed.
     * @param replaces In the message, replaces <b>"{n}"</b> with the nth element of this array
     *
     * @return This message wrapped as a Friendly Feedback Message object.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static FriendlyFeedbackMessage BuildMessage(@NotNull String message, @Nullable Object... replaces) {

        // Delegate to default instance
        return defaultInstance().buildMessage(message, replaces);
    }

    /**
     * @param message The raw message to wrap as this implementation's Friendly Feedback Message
     *
     * @return This message but as a Friendly Feedback object.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public FriendlyFeedbackMessage buildMessage(@NotNull String message, @Nullable Object... replaces) {

        // First, cook the message
        String parsed = message;

        // Replace {N} for the Nth item in the replaces array
        for (int i = 0; i < replaces.length; i++) {

            // Fetch the replacement to replace
            String rep = "nulL";
            Object lace = replaces[i];
            if (lace != null) { rep = lace.toString(); }

            // Replace in the message
            parsed = parsed.replace("{" + i + "}", rep);
        }

        // Simply wrap it as this implementation's message
        return buildMessage(parsed);
    }

    /**
     * @return A freshly-created provider, the default implementation loaded in the static singleton.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static FriendlyFeedbackProvider NewFFP() { return defaultInstance().newFFP(); }

    /**
     * @return A freshly-created provider, the default implementation loaded in the static singleton.
     *         Attempts to enable auto-reproducing messages if the default reproducer can handle it.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static FriendlyFeedbackProvider NewFFPAuto() {
        FriendlyFeedbackProvider ret = defaultInstance().newFFP();

        // Try to use the default instance
        if (defaultInstance().getDefaultReproducer() instanceof FriendlySimpleReproducer) {
            ret.enableAutoSender((FriendlySimpleReproducer) defaultInstance().getDefaultReproducer());
        }

        return ret;
    }
    /**
     * @return A freshly-created provider, the default implementation loaded in the static singleton.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static FriendlyFeedbackProvider NewFFPAuto(@Nullable FriendlySimpleReproducer reproducer) {
        FriendlyFeedbackProvider ret = defaultInstance().newFFP();
        ret.enableAutoSender(reproducer);
        return ret;
    }

    /**
     * @param message Message that may contain Friendly Feedback codes
     *
     * @return Message with no Friendly Feedback codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Contract("null -> null;!null -> !null")
    @Nullable public static String StripFormatCodes(@Nullable String message) {
        if (message == null) { return null; }

        // No palette codes? We are done
        if (!message.contains("$")) { return message; }

        // Split by $ signs and build back up (basically)
        StringBuilder colorless = new StringBuilder();
        for (FriendlyFeedbackBit code : SplitByPaletteCodes(message)) {
            colorless.append(code.getFeedbackMessage());
        }

        // Done
        return colorless.toString();
    }

    /**
     * @param message Message that may contain Friendly Feedback codes.
     * @return Individual bits referenced by their friendly feedback character.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static ArrayList<FriendlyFeedbackBit> SplitByPaletteCodes(@Nullable String message) {
        if (message == null) { return new ArrayList<>(); }

        // Create collection
        ArrayList<FriendlyFeedbackBit> bits = new ArrayList<>();

        // No palette codes? Only one message with the body bit
        if (!message.contains("$")) {
            bits.add(new FriendlyFeedbackBit('b', message));
            return bits;
        }

        // Prepare next bit
        char palette = 'b';
        StringBuilder colorless = new StringBuilder();
        boolean startException = !message.startsWith("$");

        // Split by $ signs
        String[] codeSpit = message.split("\\$");
        for (String code : codeSpit) {

            // No need to bother with empty strings
            if (code.length() < 1) { continue; }

            // Does this string begin with a Palette Code character?
            boolean paletteCodeDetected = false;
            for (char c : GetPaletteCodes()) {
                if (code.charAt(0) == c) {
                    paletteCodeDetected = true;
                    break;
                }
            }

            // If a palette
            if (paletteCodeDetected) {

                // If it does start with a palette code, ignore this
                if (code.length() == 1) { continue; }

                // Consolidate the previous message that was built
                if (colorless.length() > 0) {
                    bits.add(new FriendlyFeedbackBit(palette, colorless.toString()));
                }

                // Reset the palette code and colorless
                colorless.setLength(0);
                palette = code.charAt(0);
                colorless.append(code.substring(1));

                // This was a normal $ followed by more text, then append it
            } else {

                // Append the removed dollar sign
                if (startException) { startException = false; } else { colorless.append('$'); }

                // Append this text split
                colorless.append(code);
            }
        }

        // Add the remainder message node
        if (colorless.length() > 0) { bits.add(new FriendlyFeedbackBit(palette, colorless.toString())); }

        // That's the result
        return bits;
    }
    //endregion

    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public OotilityFriend(@NotNull GOOBEngine engine) { this.engine = engine; }

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
