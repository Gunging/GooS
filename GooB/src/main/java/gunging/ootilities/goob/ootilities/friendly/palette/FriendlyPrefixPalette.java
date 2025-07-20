package gunging.ootilities.goob.ootilities.friendly.palette;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Extension to the {@link FriendlyFeedbackPalette} interface
 * that supports a context-dependent prefix, very professional.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface FriendlyPrefixPalette {

    /**
     * @return The final message, as processed by these options.
     * @param options The options to apply when parsing this message.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull String getPrefix(@Nullable FriendlyColorContext options);
}
