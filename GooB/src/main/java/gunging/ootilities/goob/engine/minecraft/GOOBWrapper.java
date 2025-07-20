package gunging.ootilities.goob.engine.minecraft;

import org.jetbrains.annotations.NotNull;

/**
 * A class that wraps platform-dependent object in GooB Wraps.
 *
 * @param <GooF> The wrap class for this wrapper
 * @param <E> The class being wrapped in the wrap
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBWrapper<GooF extends GOOBWrap<E>, E> {

    /**
     * @param wrap The Object being wrapped
     *
     * @return This Object but wrapped as its platform-dependent version
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull GooF wrap(@NotNull E wrap);

    /**
     * @return The class of the object being wrapped
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull Class<E> getWrappedClass();
}
