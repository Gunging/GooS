package gunging.ootilities.goob.ootilities.friendly.palette;

/**
 * A context that cares about HEX colors being supported or not
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface HexSupportContext {

    /**
     * @return If hex colors are supported in this context
     *
     * @author Gunging
     * @since 1.0.0
     */
    boolean isHexSupported();
}
