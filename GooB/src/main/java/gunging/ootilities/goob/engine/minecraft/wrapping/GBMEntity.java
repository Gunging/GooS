package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * The fundamental functionality of Entity, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Entity class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMEntity<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMEntity(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @return The angle, in degrees, the entity's line of sight makes with the horizontal plane.
     *         A negative number means the entity is looking up, and a positive value means they
     *         are looking down.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract double getPitch();

    /**
     * @return The angle, in degrees, the entity's line of sight makes with the YZ plane. Essentially,
     *         ZERO means they are looking toward positive Z with no X component, and when this is +90°
     *         we are looking towards negative X with no Z component.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract double getYaw();

    /**
     * @return The UUID of this entity
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract UUID getUUID();

    /**
     * @return A human-readable name to show this entity
     *         in chat or the console, nothing too serious.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract String getDebugName();
}
