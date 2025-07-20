package gunging.ootilities.goob.ootilities.friendly.provided;

import gunging.ootilities.goob.ootilities.friendly.palette.FriendlyColorContext;
import gunging.ootilities.goob.ootilities.friendly.palette.FriendlyPaletteColor;
import gunging.ootilities.goob.ootilities.friendly.palette.HexSupportContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A color coded by section signs, very much like vanilla minecraft.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooBSectionCode implements FriendlyPaletteColor {

    /**
     * The section sign prefix by this color
     *
     * @since 1.0.0
     */
    @NotNull final String sectionHex;

    /**
     * The section sign prefix by this color
     *
     * @since 1.0.0
     */
    @NotNull final String sectionLegacy;

    /**
     * @param section The section sign prefix by this color.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooBSectionCode(@NotNull String section) { this(section, section); }

    /**
     * @param sectionHex The section sign prefix by this color, when HEX colors are supported
     * @param sectionLegacy The section sign prefix by this color, when HEX colors are not supported
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooBSectionCode(@NotNull String sectionHex, @NotNull String sectionLegacy) {
        this.sectionHex = sectionHex;
        this.sectionLegacy = sectionLegacy;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String colorize(@Nullable FriendlyColorContext context, @NotNull String text) {

        // Choose between hex and legacy depending on context
        if (context instanceof HexSupportContext) {
            if (((HexSupportContext) context).isHexSupported()) {
                return sectionHex + text;
            } else {
                return sectionLegacy + text;
            }
        }

        // No context means using the legacy sections (considered most universal)
        return sectionLegacy + text;
    }
}
