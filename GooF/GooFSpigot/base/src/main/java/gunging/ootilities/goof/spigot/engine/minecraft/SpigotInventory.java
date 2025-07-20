package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMInventory;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper for the Spigot Inventory
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotInventory extends GBMInventory<Inventory> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotInventory(@NotNull SpigotInventoryWrapper wrapper, @NotNull Inventory wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }

    //region Inventory
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int getSize() { return unwrap().getSize(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> getItem(int slot) {
        ItemStack found = unwrap().getItem(slot);
        if (found == null) { return null; }
        return getMinecraft().getItemStacks().wrap(found);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setItem(int slot, @Nullable GBMItemStack<?> item) {
        if (item == null) { item = getMinecraft().getItemStacks().empty(); }
        if (!(item.unwrap() instanceof ItemStack)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return; }
        unwrap().setItem(slot, (ItemStack) item.unwrap());
    }
    //endregion
}
