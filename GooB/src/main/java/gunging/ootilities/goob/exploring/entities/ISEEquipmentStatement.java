package gunging.ootilities.goob.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMEquipmentSlotted;
import gunging.ootilities.goob.engine.minecraft.wrapping.*;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Deals with vanilla EquipmentSlots in Living Entities
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public abstract class ISEEquipmentStatement<E extends GBMEquipmentSlot<?>> extends ISEEntityStatement implements GBMEquipmentSlotted<E> {

    /**
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISEEquipmentStatement(@NotNull GOOBWrapperBase<? extends GBMEntity<?>, ?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(wrapper, statementName);
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMEntity<?> target) {
        GBMLivingEntity<?> asLiving = target instanceof GBMLivingEntity<?> ? (GBMLivingEntity<?>) target : null;
        if (asLiving == null) { return null; }
        return asLiving.getItemBySlot(getEquipmentSlot());
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public void writeItemStack(@NotNull GBMEntity<?> target, @Nullable GBMItemStack<?> item) {
        GBMLivingEntity<?> asLiving = target instanceof GBMLivingEntity<?> ? (GBMLivingEntity<?>) target : null;
        if (asLiving == null) { return; }

        // When setting a null item, we set the EMPTY item instead
        if (item == null) {
            GBMItemStackWrapper<?,?> wrapper = getMinecraft().getItemStacks();
            if (wrapper == null) { return; }
            asLiving.setItemInSlot(getEquipmentSlot(), wrapper.empty());

        // Not a null item? Just put it in the slot
        } else {
            asLiving.setItemInSlot(getEquipmentSlot(), item);
        }
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public boolean equals(Object obj) {

        // Equipment Slot compatibility
        if (obj instanceof GBMEquipmentSlotted<?>) {
            return getEquipmentSlot().equals(((GBMEquipmentSlotted<?>) obj).getEquipmentSlot());
        }

        // Use superclass
        return super.equals(obj);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getOptions() { return ""; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ItemStackLocation<GBMEntity<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        return new ISEEntityLocation(elaborator.getElaboratee(), this);
    }
}
