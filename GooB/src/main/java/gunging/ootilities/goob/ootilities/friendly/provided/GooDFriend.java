package gunging.ootilities.goob.ootilities.friendly.provided;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import gunging.ootilities.goob.ootilities.friendly.FriendlyReproducer;
import org.jetbrains.annotations.NotNull;

/**
 * A default implementation of the Ootility Friend class meant
 * ready to work with the other GooDefault implementations.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooDFriend extends OotilityFriend {

    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooDFriend(@NotNull GOOBEngine engine) { super(engine); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GooDFriendlyMessage buildMessage(@NotNull String message) { return new GooDFriendlyMessage(message); }

    /**
     * @since 1.0.0
     */
    @NotNull FriendlyReproducer defaultReproducer = new GooDConsoleReproducer(getEngine());

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setDefaultReproducer(@NotNull FriendlyReproducer reproducer) { defaultReproducer = reproducer; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull FriendlyReproducer getDefaultReproducer() { return defaultReproducer; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull FriendlyFeedbackProvider newFFP() { return new GooDFriendlyProvider(getEngine()); }

}
