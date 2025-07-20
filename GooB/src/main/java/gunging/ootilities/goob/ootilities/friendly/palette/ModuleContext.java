package gunging.ootilities.goob.ootilities.friendly.palette;

import org.jetbrains.annotations.Nullable;

/**
 * A context that carries information on what module or
 * area of the larger project it is happening. Great for
 * prefixes or something.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface ModuleContext {

    /**
     * @return The module context
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable String getModule();

    /**
     * @param mod The module context
     *
     * @author Gunging
     * @since 1.0.0
     */
    void setModule(@Nullable String mod);
}
