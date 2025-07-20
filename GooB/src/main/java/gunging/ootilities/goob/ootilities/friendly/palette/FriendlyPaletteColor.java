package gunging.ootilities.goob.ootilities.friendly.palette;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A "single" color. However, some contexts require small variations of
 * this "single" color like enabling some sort of darkmode which will
 * need a light shade rather than a dark shade.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface FriendlyPaletteColor {

    /**
     * @param text The text to give color format
     *
     * @return The colorized text
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull default String colorize(@NotNull String text) { return colorize(null, text); }

    /**
     * @param text The text to give color format
     * @param context Context or options by which to display this color
     *
     * @return The colorized text
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull String colorize(@Nullable FriendlyColorContext context, @NotNull String text);
}
