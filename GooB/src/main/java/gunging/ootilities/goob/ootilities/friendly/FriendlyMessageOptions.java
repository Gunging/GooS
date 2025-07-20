package gunging.ootilities.goob.ootilities.friendly;

import org.jetbrains.annotations.NotNull;

/**
 * Options for Friendly Feedback Messages, like whether
 * they should print with a handy prefix identifier of
 * the sender of the message chain or not.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface FriendlyMessageOptions {

    /**
     * @param bit The message to apply these options to.
     * @return This bit but formatted.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull String friendlyFormat(@NotNull FriendlyFeedbackBit bit);
}
