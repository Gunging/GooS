package gunging.ootilities.goob.ootilities.friendly.palette;

import org.jetbrains.annotations.Nullable;

/**
 * Palette for the Friendly Feedback system, it has
 * the colors necessary for parsing of messages.
 * <br><br>
 * Friendly Feedback Palette Codes:
 * <p><b>$b</b> Normal Body Text</p>
 * <p><b>$e</b> Example Value</p>
 * <p><b>$d</b> Recommended/Default Value</p>
 * <p><b>$u</b> Unprocessed User Input</p>
 * <p><b>$p</b> Processed User Input</p>
 * <p><b>$r</b> Operation Result</p>
 * <p><b>$s</b> Success</p>
 * <p><b>$f</b> Failure</p>
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface FriendlyFeedbackPalette {

    /**
     * @return The color that substitutes the <b>$b</b> color code.
     *
     * @param index The Friendly Feedback Palette code for this color.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable FriendlyPaletteColor getPaletteColor(char index);
}
