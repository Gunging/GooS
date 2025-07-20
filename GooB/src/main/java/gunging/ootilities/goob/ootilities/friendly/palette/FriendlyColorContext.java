package gunging.ootilities.goob.ootilities.friendly.palette;

/**
 * Options for the context by which a Friendly Color is requested.
 * For example, if "darkmode" is enabled which will presumably give you a color
 * with better contrast against a dark background.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface FriendlyColorContext {

    /**
     * Resets this context to its starting values
     *
     * @author Gunging
     * @since 1.0.0
     */
    void resetContext();
}
