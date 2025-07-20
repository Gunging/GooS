package gunging.ootilities.goob.exploring.players;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMEquipmentSlotted;
import gunging.ootilities.goob.engine.minecraft.wrapping.*;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Deals with vanilla EquipmentSlots in Players
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISPEquipmentStatement<E extends GBMEquipmentSlot<?>> extends ISPPlayerStatement implements GBMEquipmentSlotted<E> {

    /**
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPEquipmentStatement(@NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(wrapper, statementName);
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMPlayer<?> target) { return target.getItemBySlot(getEquipmentSlot()); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public void writeItemStack(@NotNull GBMPlayer<?> target, @Nullable GBMItemStack<?> item) {

        // When setting a null item, we set the EMPTY item instead
        if (item == null) {
            GBMItemStackWrapper<?,?> wrapper = getMinecraft().getItemStacks();
            if (wrapper == null) { return; }
            target.setItemInSlot(getEquipmentSlot(), wrapper.empty());

        // Not a null item? Just put it in the slot
        } else {
            target.setItemInSlot(getEquipmentSlot(), item);
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
    @Override public @Nullable ItemStackLocation<GBMPlayer<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        return new ISPPlayerLocation(elaborator.getElaboratee(), this);
    }
}
