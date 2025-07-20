package gunging.ootilities.goob.ootilities.friendly.palette;

/**
 * A context that carries information on the length of this
 * Friendly Feedback Message, best used with {@link FriendlyPrefixPalette}
 * to put prefixes only to the first Friendly Feedback Bit
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface MessageLengthContext {

    /**
     * @return The length of the message up to this point, before
     *         actually appending the new Friendly Feedback Bit being
     *         colorized.
     *
     * @author Gunging
     * @since 1.0.0
     */
    int getCurrentLength();

    /**
     * @param l The length of the Friendly Feedback Message so far
     *
     * @author Gunging
     * @since 1.0.0
     */
    void setCurrentLength(int l);
}
