package gunging.ootilities.goob.ootilities.friendly.provided;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.ootilities.friendly.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Reproduces the messages of this Friendly Feedback Provider into the java console.
 * Consider changing this for better, specific platform-dependent logging ones.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooDConsoleReproducer implements FriendlyReproducer, GOOBFuel, FriendlySimpleReproducer {

    /**
     * Code returned when a null Friendly Feedback Provider was passed, which means
     * nothing is printed since we are skipping the Friendly Feedback system for now.
     *
     * @since 1.0.0
     */
    public static final int CANCELLED_NULL_FFP = 1;

    /**
     * The FFP Contents were printed successfully yay
     *
     * @since 1.0.0
     */
    public static final int SUCCESS = 2;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int reproduce(@Nullable FriendlyFeedbackProvider ffp, @NotNull FriendlyFeedbackCategory... categories) {

        // Nothing happens when null
        if (ffp == null) { return CANCELLED_NULL_FFP; }

        // For every category
        for (FriendlyFeedbackCategory cat : categories) {

            // For every message
            for (FriendlyFeedbackMessage message : ffp.getFeedback(cat)) {

                // Simply print to console without options
                reproduce(message);
            }
        }

        // Successfully printed out all contents of this Friendly Feedback Provider
        return SUCCESS;
    }

    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooDConsoleReproducer(@NotNull GOOBEngine engine) { this.engine = engine; }

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

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public int reproduce(@Nullable FriendlyFeedbackMessage message) {

        // Nothing happens when null
        if (message == null) { return CANCELLED_NULL_FFP; }

        // Simply print to console without options
        System.out.printf(message.getMessage(null));

        // Success
        return SUCCESS;
    }
    //endregion
}
