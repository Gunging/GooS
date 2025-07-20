package gunging.ootilities.goob.ootilities.friendly.provided;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A GooDefault implementation of the Friendly Feedback Provider.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooDFriendlyProvider implements FriendlyFeedbackProvider, GOOBFuel {

    /**
     * All the feedback contained in this Friendly Feedback Provider
     *
     * @since 1.0.0
     */
    @NotNull HashMap<FriendlyFeedbackCategory, ArrayList<FriendlyFeedbackMessage>> feedback = new HashMap<>();

    /**
     * A reproduction means that is triggered immediately upon calling Log.
     *
     * @since 1.0.0
     */
    @Nullable FriendlySimpleReproducer autoSender;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean enableAutoSender(@Nullable FriendlySimpleReproducer reproducer) {
        autoSender = reproducer;
        return true;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<FriendlyFeedbackMessage> getFeedback(@NotNull FriendlyFeedbackCategory... categories) {
        ArrayList<FriendlyFeedbackMessage> ret;

        // One category only returns the actual array.
        if (categories.length == 1) {
            FriendlyFeedbackCategory cat = categories[0];
            ArrayList<FriendlyFeedbackMessage> found = feedback.get(cat);
            if (found == null) { ret = new ArrayList<>(); feedback.put(cat, ret); } else { ret = found; }

        // Multiple categories combines them all
        } else {
            ret = new ArrayList<>();
            for (FriendlyFeedbackCategory cat : categories) {
                ArrayList<FriendlyFeedbackMessage> found = feedback.get(cat);
                if (found != null) { ret.addAll(found); }
            }
        }

        // That's the result
        return ret;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<FriendlyFeedbackMessage> getFeedback() {
        return getFeedback(OotilityFriend.AllCategories());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void log(@NotNull FriendlyFeedbackCategory category, @NotNull FriendlyFeedbackMessage message) {

        // Obtain the ArrayLit for this logging category
        ArrayList<FriendlyFeedbackMessage> found = feedback.computeIfAbsent(category, k -> new ArrayList<>());

        // Add the message
        found.add(message);

        // Auto reproduce
        if (autoSender != null) { autoSender.reproduce(message); }
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void clearFeedback(@NotNull FriendlyFeedbackCategory... categories) {
        for (FriendlyFeedbackCategory cat : categories) { feedback.remove(cat); }
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void clearFeedback() { feedback.clear(); }

    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooDFriendlyProvider(@NotNull GOOBEngine engine) { this.engine = engine; }

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
