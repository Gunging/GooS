package gunging.ootilities.goop.engine;

import gunging.ootilities.goob.ootilities.friendly.palette.*;
import gunging.ootilities.goob.ootilities.friendly.provided.GooBSectionCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * A GooB-Brand Friendly Feedback Palette, yes.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GooPPalette  implements FriendlyFeedbackPalette, FriendlyPrefixPalette {

    /**
     * The colors of this GooB Palette
     * @since 1.0.0
     */
    @NotNull HashMap<Character, FriendlyPaletteColor> palette;

    /**
     * A basic palette that turns Friendly Feedback color codes into Section Sign codes
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GooPPalette() {
        palette = new HashMap<>();

        // Fill palette codes
        palette.put('b', new GooBSectionCode("§7"));
        palette.put('e', new GooBSectionCode("§b"));
        palette.put('d', new GooBSectionCode("§3"));
        palette.put('u', new GooBSectionCode("§f"));
        palette.put('p', new GooBSectionCode("§e"));
        palette.put('r', new GooBSectionCode("§6"));
        palette.put('s', new GooBSectionCode("§a"));
        palette.put('f', new GooBSectionCode("§c"));

        // Prefixes
        SIMPLE_PREFIX = "§3[§eGooP§3]§7 ";
        MODULE_PREFIX = "§3[§eGooP §b§o{module}§r§3]§7 ";
    }

    /**
     * Prefix used when no module exists in the context
     *
     * @since 1.0.0
     */
    @NotNull final String SIMPLE_PREFIX;

    /**
     * Prefix used when a module is indeed specified in the context
     *
     * @since 1.0.0
     */
    @NotNull final String MODULE_PREFIX;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable FriendlyPaletteColor getPaletteColor(char index) { return palette.get(index); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getPrefix(@Nullable FriendlyColorContext options) {

        // Check if these options have a module
        if (options instanceof ModuleContext) {
            String module = ((ModuleContext) options).getModule();

            // Return module prefix
            if (module != null && module.length() > 0) {
                return MODULE_PREFIX.replace("{module}", module);
            }
        }

        // Just the simple prefix
        return SIMPLE_PREFIX;
    }
}
