package gunging.ootilities.goob.ootilities.friendly;

import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.palette.FriendlyColorContext;
import gunging.ootilities.goob.ootilities.friendly.palette.MessageLengthContext;
import gunging.ootilities.goob.ootilities.friendly.palette.OptionsWithContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A message line to be used by the Friendly Feedback system.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface FriendlyFeedbackMessage {

    /**
     * @return The raw message enclosed, before any color code parsing or anything.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull String getRawMessage();

    /**
     * @return The raw message, but split in Friendly Feedback Bits
     *         to be each parsed individually.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull ArrayList<FriendlyFeedbackBit> getAsBits();

    /**
     * @return The final message, as processed by these options.
     * @param options The options to apply when parsing this message.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull default String getMessage(@Nullable FriendlyMessageOptions options) {

        // No option? Just strip codes.
        if (options == null) { return getStrippedMessage(); }

        // Reset the context of the options
        boolean hasContext = options instanceof OptionsWithContext;
        FriendlyColorContext context = null;
        if (hasContext) {
            context = ((OptionsWithContext) options).getContext();
            context.resetContext(); }
        boolean lenContext = context instanceof MessageLengthContext;

        // Parse
        StringBuilder parsed = new StringBuilder();
        for (FriendlyFeedbackBit bit : getAsBits()) {

            // Update supported dynamic context
            if (hasContext) {
                if (lenContext) { ((MessageLengthContext) context).setCurrentLength(parsed.length()); }
            }

            // Pares under this context
            parsed.append(options.friendlyFormat(bit));
        }

        // Complete
        return parsed.toString();
    }

    /**
     *
     * @return The {@link #getRawMessage()} but with no friendly format codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull default String getStrippedMessage() { return OotilityFriend.StripFormatCodes(getRawMessage()); }
}
