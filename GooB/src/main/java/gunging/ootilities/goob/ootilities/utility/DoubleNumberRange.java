package gunging.ootilities.goob.ootilities.utility;

import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.OotilityNumbers;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackCategory;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link QuickNumberRange} that deals with Double-type numbers, essentially the decimal version.
 * <p>
 * Use {@link #GetFromString(String)} to generate one of these bad boys from user input!
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class DoubleNumberRange extends QuickNumberRange<Double> {

    /**
     * Supported Formats:
     * <p><code>[m]..[M]</code>  (Vanilla range, the way used in command selectors)
     * </p><code>[n]</code> (Just a number; basically <code>=</code> operator)
     * <p><code>..[M]</code> (anything up to this; basically less than or equals operator)
     * </p><code>[m]..</code> (this and on; basically greater than or equals operator)
     * <p></p>
     * Obviously, number <code>[m]</code> is the minimum, and <code>[M]</code> the maximum;
     * They may not even be specified.
     *
     * @return <code>null</code>> if incorrect format.
     *
     * @param qnr The string representation of this number range
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Contract("null -> null") @Nullable public static DoubleNumberRange GetFromString(@Nullable String qnr) { return GetFromString(qnr, null); }
    /**
     * Supported Formats:
     * <p><code>[m]..[M]</code>  (Vanilla range, the way used in command selectors)
     * </p><code>[n]</code> (Just a number; basically <code>=</code> operator)
     * <p><code>..[M]</code> (anything up to this; basically less than or equals operator)
     * </p><code>[m]..</code> (this and on; basically greater than or equals operator)
     * <p></p>
     * Obviously, number <code>[m]</code> is the minimum, and <code>[M]</code> the maximum;
     * They may not even be specified.
     *
     * @return <code>null</code>> if incorrect format.
     *
     * @param ffp Feedback on the completion of this operation.
     * @param qnr The string representation of this number range
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Contract("null,_ -> null") @Nullable public static DoubleNumberRange GetFromString(@Nullable String qnr, @Nullable FriendlyFeedbackProvider ffp) {

        // Parsing errors before any parsing errors could have existed...
        if (qnr == null) {
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.ERROR,"Could not build integer number range from '$unull$b'. ");
            return null; }

        // The special case, when neither bound is specified (bruh but ok)
        if (qnr.equals("..")) {
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.SUCCESS,"Accepted unbounded range from '$u{0}$b'. ", qnr);
            return new DoubleNumberRange(null, null); }
        if (qnr.equals("*")) {
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.SUCCESS,"Accepted unbounded range from '$u{0}$b'. ", qnr);
            return new DoubleNumberRange(null, null); }

        // Number itself? So it's basically an EXACTLY this value, I'll allow it.
        Double asItself = OotilityNumbers.DoubleParse(qnr);
        if (asItself != null) {
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.SUCCESS,"Accepted exact value from '$u{0}$b'. ", qnr);
            return new DoubleNumberRange(asItself, asItself); }

        // Split
        if (qnr.contains("..")) {

            // Split
            String[] blit = qnr.split("\\.\\.");

            // Must be length two
            if (blit.length == 2 || blit.length == 1) {

                // Failure?
                boolean failure = false;

                /*
                 *   Identify minor and major bounds
                 */
                String m = "", M = "";

                // Are both specified?
                if (blit.length == 2) {

                    // In order
                    m = blit[0];
                    M = blit[1];

                    // Only one is specified
                } else {

                    // Unrestricted Bounds
                    if(qnr.startsWith("..")) {

                        // Its second
                        M = blit[0];

                    } else {

                        // Its first
                        m = blit[0];
                    }
                }

                /*
                 *  Parse both of them
                 */
                Double min = OotilityNumbers.DoubleParse(m);
                Double max = OotilityNumbers.DoubleParse(M);

                // Try to parse both
                if (!m.isEmpty() && min == null) {

                    // Number Format Error
                    failure = true; }

                // Try to parse both
                if (!M.isEmpty() && max == null) {

                    // Number Format Error
                    failure = true; }

                // Success?
                if (!failure) {

                    // Return
                    OotilityFriend.Log(ffp, FriendlyFeedbackCategory.SUCCESS,"Accepted number range from '$u{0}$b'. ", qnr);
                    return new DoubleNumberRange(min, max);
                }
            }
        }

        // Something went wrong
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.ERROR,"Could not parse integer number range from '$u{0}$b'. ", qnr);
        return null;
    }

    /**
     * An easy way of testing if the number falls between two numbers.
     *
     * @param min The (optional) lower bound
     * @param max The (optional) upper bound
     *
     * @author Gunging
     * @since 1.0.0
     */
    public DoubleNumberRange(@Nullable Double min, @Nullable Double max) { super(min, max); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public boolean isGreaterThan(@NotNull Double basis, @NotNull Double target) {
        return basis > target;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public boolean isLessThan(@NotNull Double basis, @NotNull Double target) {
        return basis < target;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public DoubleNumberRange clone() { return new DoubleNumberRange(getMinimumInclusive(), getMaximumInclusive()); }
}
