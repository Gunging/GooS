package gunging.ootilities.goob.engine.hooking.overriding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The capability of cancelling events is essential
 * to an event bus, which is why a Static Overrideable
 * returns a result rather than the thing they are supposed
 * to return directly.
 *
 * @param <Return> The return type of this operation
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class GOOBStaticOverrideableResult<Return> {

    /**
     * The original item, as sent to the GooB utility class
     *
     * @since 1.0.0
     */
    @NotNull final Return original;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public Return getOriginal() { return original; }

    /**
     * The item, as processed by all previous overrideable results.
     *
     * @since 1.0.0
     */
    @Nullable Return result;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public Return getResult() { return result; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public void setResult(@Nullable Return result) { this.result = result; }

    /**
     * If this has been cancelled such that the Ootility class should return null.
     *
     * @since 1.0.0
     */
    boolean cancelled;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public boolean isCancelled() { return cancelled; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    /**
     * This signals the Ootility class that this overrideable result is final
     * and should be returned, instead of attempting to continue processing
     * it through other compatibilities.
     *
     * @since 1.0.0
     */
    boolean finished;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public boolean isFinished() { return finished; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public void setFinished() { this.finished = true; }

    /**
     * @param original The original item, as sent to the GooB utility class
     *
     * @since 1.0.0
     * @author Gunging
     */
    public GOOBStaticOverrideableResult(@NotNull Return original) { this.original = original; }
}
