package gunging.ootilities.goob.engine.hooking.overriding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The objective of this class is to override a method that runs from
 * one of GooS' static utility classes when certain compatibility
 * is included. Select methods support this overrideable format, and
 * third parties may register their overrideable onto them, the events
 * will be fired in the same way as an event bus.
 *
 * @param <Return> The return type of this operation
 *
 * @since 1.0.0
 * @author Gunging
 */
@FunctionalInterface
public interface GOOBStaticOverrideable<Return> {

    /**
     * @param target The object being processed, most likely wrapped by GOOB.
     * @param arguments The arguments from the original method that was called.
     *
     * @return The result of this operation, see {@link GOOBStaticOverrideableResult}.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull GOOBStaticOverrideableResult<Return> process(@NotNull GOOBStaticOverrideableResult<Return> target, @Nullable Object[] arguments);
}
