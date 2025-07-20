package gunging.ootilities.goob.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import org.jetbrains.annotations.NotNull;

/**
 * The base class of a wrap class, takes care of structural jargon such as GOOBFuel.
 * Also delegates equality members to the object being wrapped rather than the wrap.
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GOOBWrapBase<E> implements GOOBWrap<E>, GOOBFuel, GOOBMinecraftFuel {

    //region Wrapper
    /**
     * @param wrapper The wrapper that uses this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOBWrapBase(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        this(wrapper.getMinecraft(), wrapper, wrap);
    }
    /**
     * @param minecraft The engine this is running on
     * @param wrapper The wrapper that uses this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOBWrapBase(@NotNull GOOBMinecraft minecraft, @NotNull GOOBWrapper<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        this.wrapper = wrapper;
        this.minecraft = minecraft;
        this.wrapped = wrap;
    }

    /**
     * The wrapper that uses this wrap
     *
     * @since 1.0.0
     */
    @NotNull final GOOBWrapper<? extends GOOBWrap<E>, E> wrapper;

    /**
     * The engine this wrapper is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBMinecraft minecraft;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBMinecraft getMinecraft() { return minecraft; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }

    /**
     * The wrapped object this wrapper is wrapping
     *
     * @since 1.0.0
     */
    @NotNull final E wrapped;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull GOOBWrapper<? extends GOOBWrap<E>, E> getWrapper() { return wrapper; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull E unwrap() { return wrapped; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<E> getWrappedClass() { return wrapper.getWrappedClass(); }
    //endregion

    //region Equality
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int hashCode() { return unwrap().hashCode(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public boolean equals(Object obj) {

        // Make sure the wrapped objects check for each other if wrapped
        if (obj instanceof GOOBWrap) { return unwrap().equals(((GOOBWrap<?>) obj).unwrap()); }

        // Equals is resolved by the object I am wrapping
        return unwrap().equals(obj);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public String toString() { return unwrap().toString(); }
    //endregion
}
