package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEquipmentSlot;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMLivingEntity;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Wrapper for the Spigot Living Entity
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotLivingEntity extends GBMLivingEntity<LivingEntity> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap    The platform object to wrap
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotLivingEntity(@NotNull SpigotLivingEntityWrapper wrapper, @NotNull LivingEntity wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }

    //region Entity
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public double getPitch() { return unwrap().getLocation().getPitch(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public double getYaw() { return unwrap().getLocation().getYaw(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull UUID getUUID() { return unwrap().getUniqueId(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getDebugName() { return unwrap().getName(); }
    //endregion

    //region Living Entity
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> getItemBySlot(@NotNull GBMEquipmentSlot<?> slot) {

        // I guess a lot of unwrapping
        EntityEquipment equipment = unwrap().getEquipment();
        if (equipment == null) { return null; }
        if (!(slot.unwrap() instanceof EquipmentSlot)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return null; }
        EquipmentSlot eq = (EquipmentSlot) slot.unwrap();

        // Finally, wrap the item in the equipment and return it
        return getMinecraft().getItemStacks().wrap(equipment.getItem(eq));
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public void setItemInSlot(@NotNull GBMEquipmentSlot<?> slot, @NotNull GBMItemStack<?> item) {

        // I guess a lot of unwrapping
        EntityEquipment equipment = unwrap().getEquipment();
        if (equipment == null) { return; }
        if (!(slot.unwrap() instanceof EquipmentSlot)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return; }
        EquipmentSlot eq = (EquipmentSlot) slot.unwrap();
        if (!(item.unwrap() instanceof ItemStack)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return; }

        // Finally, wrap the item in the equipment and return it
        equipment.setItem(eq, (ItemStack) item.unwrap());
    }
    //endregion
}
