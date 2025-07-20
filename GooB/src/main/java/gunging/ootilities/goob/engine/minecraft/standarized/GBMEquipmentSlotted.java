package gunging.ootilities.goob.engine.minecraft.standarized;

import org.jetbrains.annotations.NotNull;

/**
 * Denotes a class that involves itself with Equipment Slots
 *
 * @param <E> The equipment slot class for this platform
 *
 * @since 1.0.0
 * @author Gunging
 */
public interface GBMEquipmentSlotted<E> {

    /**
     * @return The vanilla equipment slot that this Entity slot targets
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull E getEquipmentSlot();
}
