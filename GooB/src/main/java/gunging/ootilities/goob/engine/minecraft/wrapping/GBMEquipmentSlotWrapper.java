package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Equipment Slots.
 *
 * @param <GooF> The wrapper class for Equipment Slots
 * @param <E> The Equipment Slots class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMEquipmentSlotWrapper<GooF extends GBMEquipmentSlot<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMEquipmentSlotWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }
}
