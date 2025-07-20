package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.wrapping.*;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.entity.Player;
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
public class SpigotPlayer extends GBMPlayer<Player> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap    The platform object to wrap
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotPlayer(@NotNull SpigotPlayerWrapper wrapper, @NotNull Player wrap) {
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

    //region Player
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GBMPlayerInventory<?> getInventory() {
        return getMinecraft().getPlayerInventories().wrap(unwrap().getInventory());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GBMInventory<?> getEnderchest() {
        return getMinecraft().getInventories().wrap(unwrap().getEnderChest());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setCursor(@Nullable GBMItemStack<?> item) {

        // Check the item
        if (item == null) { item = getMinecraft().getItemStacks().empty(); }
        if (!(item.unwrap() instanceof ItemStack)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return; }

        // Put
        unwrap().setItemOnCursor((ItemStack) item.unwrap());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> getCursor() {
        return getMinecraft().getItemStacks().wrap(unwrap().getItemOnCursor());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> getCraftingItem(int slot) { return getOpenedInventory().getCraftingItem(slot); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setCraftingItem(int slot, @Nullable GBMItemStack<?> item) { getOpenedInventory().setCraftingItem(slot, item); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GBMInventoryView<?> getOpenedInventory() {
        return getMinecraft().getInventoryViews().wrap(unwrap().getOpenInventory());
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void sendChatMessage(@NotNull String message) { unwrap().sendMessage(message); }
    //endregion
}
