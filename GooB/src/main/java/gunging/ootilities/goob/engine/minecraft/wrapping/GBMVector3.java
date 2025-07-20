package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The fundamental functionality of Vector3, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Vector3 class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMVector3<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMVector3(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @return The X coordinate of this vector
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract double getX();

    /**
     * @return The Y coordinate of this vector
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract double getY();

    /**
     * @return The Z coordinate of this vector
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract double getZ();

    /**
     * @return This vector but normalized
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract GBMVector3<E> normalize();
}
