package gunging.ootilities.goob.ootilities.friendly;

import gunging.ootilities.goob.ootilities.OotilityFriend;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class that can reproduce Friendly Feedback messages.
 * Basically, that class that can finally print them out somewhere!
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface FriendlyReproducer {

    /**
     * @param ffp Feedback Provider with the messages to display.
     * @param categories The categories of the messages to display.
     *
     * @return ZERO if reproduction was unsuccessful, any other code is
     *         implementation-dependant. POSITIVES are assumed to be a
     *         success, while NEGATIVES may be treated as more-specific
     *         failure codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    int reproduce(@Nullable FriendlyFeedbackProvider ffp, @NotNull FriendlyFeedbackCategory... categories);

    /**
     * @param ffp Feedback Provider with the messages to display.
     *
     * @return ZERO if reproduction was unsuccessful, any other code is
     *         implementation-dependant. POSITIVES are assumed to be a
     *         success, while NEGATIVES may be treated as more-specific
     *         failure codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    default int reproduce(@Nullable FriendlyFeedbackProvider ffp)  { return reproduce(ffp, OotilityFriend.AllCategories()); }
}
