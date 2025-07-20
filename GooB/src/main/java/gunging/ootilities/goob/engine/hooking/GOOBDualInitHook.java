package gunging.ootilities.goob.engine.hooking;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The design of two-step initialization is, in retrospect, quite ubiquitous. I never thought about it, but anyway,
 * first things Load then they Enable or whatever. Then a hook that works this way will have those two stages to
 * build its contribution to the {@link gunging.ootilities.goob.engine.GOOBEngine} being built.
 *
 * @param <Env> The environment this is running on, to check if this can be hooked
 * @param <GooF> The foundation (or compatibility) that will be hooked
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBDualInitHook<GooF, Env> {

    /**
     * @param server The server, or environment, object
     * @param engine The engine this GooS is running
     * @return The built compatibility hook, if it could be built
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable default GooF hookOnLoad(@NotNull GOOBEngine engine, @NotNull Env server) { return hookOnLoad(engine, server, null); }

    /**
     * @param server The server, or environment, object
     * @param engine The engine this GooS is running
     * @param ffp Messages regarding the completion of this operation
     * @return The built compatibility hook, if it could be built
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable GooF hookOnLoad(@NotNull GOOBEngine engine, @NotNull Env server, @Nullable FriendlyFeedbackProvider ffp);

    /**
     * By default, redirects to {@link #hookOnLoad(GOOBEngine, Env)}
     *
     * @param server The server, or environment, object
     * @param engine The engine this GooS is running
     * @return The built compatibility hook, if it could be built
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable default GooF hookOnEnable(@NotNull GOOBEngine engine, @NotNull Env server) { return hookOnEnable(engine, server, null); }
    /**
     * By default, redirects to {@link #hookOnLoad(GOOBEngine, Env)}
     *
     * @param server The server, or environment, object
     * @param engine The engine this GooS is running
     * @param ffp Messages regarding the completion of this operation
     * @return The built compatibility hook, if it could be built
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable default GooF hookOnEnable(@NotNull GOOBEngine engine, @NotNull Env server, @Nullable FriendlyFeedbackProvider ffp) { return hookOnLoad(engine, server, ffp); }
}
