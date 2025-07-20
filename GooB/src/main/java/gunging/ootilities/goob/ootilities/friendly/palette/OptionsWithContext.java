package gunging.ootilities.goob.ootilities.friendly.palette;

import org.jetbrains.annotations.NotNull;

/**
 * An extension to {@link gunging.ootilities.goob.ootilities.friendly.FriendlyMessageOptions}
 * that indicates they come with a context within them
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface OptionsWithContext {

    /**
     * @param context The context of these options
     *
     * @author Gunging
     * @since 1.0.0
     */
    void setContext(@NotNull FriendlyColorContext context);

    /**
     * @return The context of these options
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull FriendlyColorContext getContext();
}
