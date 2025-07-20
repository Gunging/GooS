package gunging.ootilities.goob.engine.hooking;

import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * It is common for things that are hooked with {@link GOOBDualInitHook} to
 * also need a closure or shutdown method, in addition to their initialization
 * methods.
 *
 * @param <Env> The environment this is running on
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBDisableShutdown<Env> {

    /**
     * This is called when a GOOBEngine is destroyed in
     * a controlled manner, it should handle logic related
     * to closing and saving or whatever.
     *
     * @param server The server, or environment, object
     *
     * @author Gunging
     * @since 1.0.0
     */
    default void shutdownOnDisable(@NotNull Env server) { shutdownOnDisable(server, null); }

    /**
     * This is called when a GOOBEngine is destroyed in
     * a controlled manner, it should handle logic related
     * to closing and saving or whatever.
     *
     * @param server The server, or environment, object
     * @param ffp Messages regarding the execution of this operation
     *
     * @author Gunging
     * @since 1.0.0
     */
    default void shutdownOnDisable(@NotNull Env server, @Nullable FriendlyFeedbackProvider ffp) { }
}
