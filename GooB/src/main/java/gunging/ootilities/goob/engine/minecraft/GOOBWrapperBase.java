package gunging.ootilities.goob.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import org.jetbrains.annotations.NotNull;

/**
 * The base class of a wrapper class, setting up a structure for wrappers.
 *
 * @param <GooF> The wrap class for this wrapper
 * @param <E> The class being wrapped in the wrap
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GOOBWrapperBase<GooF extends GOOBWrap<E>, E> implements GOOBWrapper<GooF, E>, GOOBFuel, GOOBMinecraftFuel {

    //region Wrapper
    /**
     * @param minecraft The minecraft engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOBWrapperBase(@NotNull GOOBMinecraft minecraft) { this.minecraft = minecraft; }

    /**
     * The engine this exception is running on
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
    //endregion
}
