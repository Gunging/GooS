package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Vectors
 *
 * @param <GooF> The wrapper class for Vectors
 * @param <E> The Vector3 class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMVector3Wrapper<GooF extends GBMVector3<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMVector3Wrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param z The Z coordinate
     *
     * @return A wrapped namespaced key
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMVector3<E> wrap(double x, double y, double z);

    /**
     * @return A wrapper for the Vector at the origin
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMVector3<E> zero();
}
