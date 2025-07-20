package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Living Entities.
 *
 * @param <GooF> The wrapper class for Living Entities
 * @param <E> The Living Entity class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMLivingEntityWrapper<GooF extends GBMLivingEntity<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The minecraft engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMLivingEntityWrapper(@NotNull GOOBMinecraft minecraft) {
        super(minecraft);
    }
}
