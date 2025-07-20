package gunging.ootilities.goob.ootilities.friendly.palette;

import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackBit;
import gunging.ootilities.goob.ootilities.friendly.FriendlyMessageOptions;
import org.jetbrains.annotations.NotNull;

/**
 * A default implementation of options that colorize messages
 * based on color and format code prefixes. Very human and easy
 * to use on-the-go.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class FriendlyPaletteOptions implements FriendlyMessageOptions, OptionsWithContext {

    /**
     * @param palette Palette used to colorize text
     * @param context The context of these options
     *
     * @author Gunging
     * @since 1.0.0
     */
    public FriendlyPaletteOptions(@NotNull FriendlyFeedbackPalette palette, @NotNull FriendlyColorContext context) {
        this.palette = palette;
        this.context = context;
    }

    /**
     * Palette used to colorize text
     *
     * @since 1.0.0
     */
    @NotNull FriendlyColorContext context;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setContext(@NotNull FriendlyColorContext context) { this.context = context; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override @NotNull public FriendlyColorContext getContext() { return context; }

    /**
     * The context of these options
     *
     * @since 1.0.0
     */
    @NotNull FriendlyFeedbackPalette palette;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public void setPalette(@NotNull FriendlyFeedbackPalette palette) { this.palette = palette; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public FriendlyFeedbackPalette getPalette() { return palette; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String friendlyFormat(@NotNull FriendlyFeedbackBit bit) {
        String colorized;

        // Cannot colorize if no color in palette
        FriendlyPaletteColor color = getPalette().getPaletteColor(bit.getPaletteCode());
        if (color == null) {
            colorized = bit.getFeedbackMessage();
        } else {
            colorized = color.colorize(getContext(), bit.getFeedbackMessage());
        }

        // Append prefix
        if (getPalette() instanceof FriendlyPrefixPalette) {
            if (getContext() instanceof MessageLengthContext) {

                // Only on the first Friendly bit
                if (((MessageLengthContext) getContext()).getCurrentLength() == 0) {

                    // Append prefix
                    colorized = ((FriendlyPrefixPalette) getPalette()).getPrefix(getContext()) + colorized; }

            // Always append prefix T.T if we have no length information
            } else {

                // Append prefix
                colorized = ((FriendlyPrefixPalette) getPalette()).getPrefix(getContext()) + colorized;
            }
        }

        // Colorize
        return colorized;
    }
}
