package gunging.ootilities.goob.engine.hooking;

import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@link GOOBDualInitHook} is for hooking onto something
 * either on load, on enable, or both. But then, once you hooked
 * onto it, you might want to initialize it. Then this is called
 * after successfully hooking, with methods to initialize during
 * load phase or during enable phase.
 *
 * @param <Env> The environment this is running on, to check if this can be hooked
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBDuallyInitializable<Env> {

    /**
     * @param server The server, or environment, object
     *
     * @return If loading succeeded, returning FALSE will cancel the operation and unhook.
     *
     * @author Gunging
     * @since 1.0.0
     */
    default boolean initializeOnLoad(@NotNull Env server) { return initializeOnLoad(server, null); }

    /**
     * @param server The server, or environment, object
     * @param ffp Messages in regard to the progress of this operation
     *
     * @return If loading succeeded, returning FALSE will cancel the operation and unhook.
     *
     * @author Gunging
     * @since 1.0.0
     */
    default boolean initializeOnLoad(@NotNull Env server, @Nullable FriendlyFeedbackProvider ffp) { return true; }

    /**
     * @param server The server, or environment, object
     *
     * @return If enabling succeeded, returning FALSE will cancel the operation and unhook.
     *
     * @author Gunging
     * @since 1.0.0
     */
     default boolean initializeOnEnable(@NotNull Env server) { return initializeOnEnable(server,null); }

    /**
     * @param server The server, or environment, object
     * @param ffp Messages in regard to the progress of this operation
     *
     * @return If enabling succeeded, returning FALSE will cancel the operation and unhook.
     *
     * @author Gunging
     * @since 1.0.0
     */
     default boolean initializeOnEnable(@NotNull Env server, @Nullable FriendlyFeedbackProvider ffp) { return true; }
}
