package gunging.ootilities.goob.engine.minecraft;

import org.jetbrains.annotations.NotNull;

/**
 * A platform or version dependent anything that is
 * being wrapped to avoid referencing it directly.
 *
 * @param <E> The class of the wrapped something of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBWrap<E> {

    /**
     * @return The object being wrapped, guaranteed to exist.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull E unwrap();

    /**
     * @return The class of the object being wrapped
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull Class<E> getWrappedClass();
}
