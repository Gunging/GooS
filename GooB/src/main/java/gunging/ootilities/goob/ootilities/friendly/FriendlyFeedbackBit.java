package gunging.ootilities.goob.ootilities.friendly;

import org.jetbrains.annotations.NotNull;

/**
 * Messages sent to the Friendly Feedback Provider will have
 * Friendly Feedback Palette color codes, so this class simply
 * wraps a piece of raw string with its associated palette code.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class FriendlyFeedbackBit {

    /**
     * @param code The Friendly Feedback Palette code associated with this message.
     * @param feedback Feedback message guaranteed to have no more palette codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public FriendlyFeedbackBit(char code, @NotNull String feedback) {
        paletteCode = code;
        feedbackMessage = feedback;
    }

    /**
     * The Friendly Feedback Palette code associated with this message.
     *
     * @since 1.0.0
     */
    char paletteCode;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public char getPaletteCode() { return paletteCode; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public void setPaletteCode(char code) { paletteCode = code; }

    /**
     * Feedback message guaranteed to have no more palette codes.
     *
     * @since 1.0.0
     */
    @NotNull String feedbackMessage;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public @NotNull String getFeedbackMessage() { return feedbackMessage; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public void setFeedbackMessage(@NotNull String msg) { feedbackMessage = msg; }
}
