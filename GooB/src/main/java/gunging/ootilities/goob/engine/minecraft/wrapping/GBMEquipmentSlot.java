package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMEquipmentSlotted;
import org.jetbrains.annotations.NotNull;

/**
 * The fundamental functionality of Equipment Slots, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The EquipmentSlot class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMEquipmentSlot<E> extends GOOBWrapBase<E> implements GBMEquipmentSlotted<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMEquipmentSlot(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull E getEquipmentSlot() { return unwrap(); }
}
