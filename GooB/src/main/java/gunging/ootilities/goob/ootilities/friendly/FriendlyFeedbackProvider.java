package gunging.ootilities.goob.ootilities.friendly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * For developers, exceptions are thrown when mistakes are made.
 * However, users make mistakes too, and they need not be blasted
 * with such long and tedious technical messages.
 * <br><br>
 * The Friendly Feedback system is intended to tell the common
 * user what they are doing wrong, while maintaining flexibility
 * and maintenance for developers to use.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface FriendlyFeedbackProvider {

    /**
     * @param reproducer Friendly Reproducer to automatically publish
     *                   to as messages are added to this Feedback
     *                   Provider. Null to disable.
     *
     * @return True if enabling or disabling succeeded.
     *
     * @author Gunging
     * @since 1.0.0
     */
    default boolean enableAutoSender(@Nullable FriendlySimpleReproducer reproducer) { return false; }

    /**
     * @param categories Feedback categories of interest.
     * @return The feedback messages for the requested categories in this FFP.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull ArrayList<FriendlyFeedbackMessage> getFeedback(@NotNull FriendlyFeedbackCategory... categories);

    /**
     * @return The feedback messages for all categories in this FFP.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull ArrayList<FriendlyFeedbackMessage> getFeedback();

    /**
     * @param category The category of logging for this message
     * @param message Message of this Friendly Feedback system
     *
     * @author Gunging
     * @since 1.0.0
     */
    void log(@NotNull FriendlyFeedbackCategory category, @NotNull FriendlyFeedbackMessage message);

    /**
     * Clears the feedback associated to specified category.
     *
     * @param categories Feedback category of interest.
     *
     * @author Gunging
     * @since 1.0.0
     */
    void clearFeedback(@NotNull FriendlyFeedbackCategory... categories);

    /**
     * Clears all feedback in this FFP.
     *
     * @author Gunging
     * @since 1.0.0
     */
    void clearFeedback();
}
