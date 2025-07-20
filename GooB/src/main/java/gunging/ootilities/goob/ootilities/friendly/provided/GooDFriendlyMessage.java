package gunging.ootilities.goob.ootilities.friendly.provided;

import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackBit;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A default implementation of the Friendly Feedback Message
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooDFriendlyMessage implements FriendlyFeedbackMessage {

    /**
     * @param message The message contained in this Friendly Feedback Message
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooDFriendlyMessage(@NotNull String message) {
        rawMessage = message;
        asBits = OotilityFriend.SplitByPaletteCodes(message);
    }

    /**
     * The message enclosed, as it was originally written.
     *
     * @since 1.0.0
     */
    @NotNull String rawMessage;

    /**
     * The message enclosed, but stripped of format coeds
     *
     * @since 1.0.0
     */
    @Nullable String strippedMessage;

    @Override
    public @NotNull String getStrippedMessage() {
        if (strippedMessage != null) { return strippedMessage; }

        // Strip message
        StringBuilder colorless = new StringBuilder();
        for (FriendlyFeedbackBit code : getAsBits()) {
            colorless.append(code.getFeedbackMessage());
        }

        // Done
        strippedMessage = colorless.toString();
        return strippedMessage;
    }

    /**
     * The message enclosed, but split into bits.
     *
     * @since 1.0.0
     */
    @NotNull ArrayList<FriendlyFeedbackBit> asBits;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getRawMessage() { return rawMessage; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<FriendlyFeedbackBit> getAsBits() { return asBits; }
}
