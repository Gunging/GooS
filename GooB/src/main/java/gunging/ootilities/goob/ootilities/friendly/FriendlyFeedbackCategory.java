package gunging.ootilities.goob.ootilities.friendly;

/**
 * When you must tell the user something, what kind of topic does it relate to?
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public enum FriendlyFeedbackCategory {

    /**
     * Messages sent when an operation completes successfully
     *
     * @since 1.0.0
     */
    SUCCESS,

    /**
     * Messages sent when an operation cannot be completed
     * <br><br>
     * These <i>failures</i> are not really errors. In fact, despite the operation
     * not reaching completion, it is working as it is supposed to. Perhaps it was
     * stopped due to certain unmet conditions? The point is that this same arguments
     * can produce a success in the right context.
     * <br><br>
     * For example, a failed RNG roll. It could have been a success without changing
     * any parameter!
     *
     * @since 1.0.0
     */
    FAILURE,

    /**
     * Miscellaneous information that the user might be concerned about.
     *
     * @since 1.0.0
     */
    INFORMATION,

    /**
     * Messages concerning syntax errors in the arguments mostly, things that prevent
     * the operation from parsing or running. Under no circumstances can this input
     * allow the operation to reach success.
     *
     * @since 1.0.0
     */
    ERROR,

    /**
     * "Avoid using as much as possible" - me in like 2021, lol what did I mean? Who knows
     *
     * @since 1.0.0
     */
    OTHER
}
