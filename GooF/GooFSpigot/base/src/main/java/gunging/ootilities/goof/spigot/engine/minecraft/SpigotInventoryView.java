package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMInventoryView;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper for the Spigot Inventory View
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotInventoryView extends GBMInventoryView<InventoryView> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotInventoryView(@NotNull SpigotInventoryViewWrapper wrapper, @NotNull InventoryView wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> getCraftingItem(int slot) {
        if (unwrap().getType() != InventoryType.CRAFTING) { return null; }
        if (slot < 0 || slot > 4) { getEngine().scrutinize(GOOBScrutiny.DEVELOPMENT_HIGH, "Invalid index for crafting slot grid. "); return null; }
        ItemStack result = unwrap().getItem(slot);
        if (result == null) { return null; }
        return getMinecraft().getItemStacks().wrap(result);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setCraftingItem(int slot, @Nullable GBMItemStack<?> item) {
        if (unwrap().getType() != InventoryType.CRAFTING) { return; }
        if (slot < 0 || slot > 4) { getEngine().scrutinize(GOOBScrutiny.DEVELOPMENT_HIGH, "Invalid index for crafting slot grid. "); return; }
        if (item == null) { item = getMinecraft().getItemStacks().empty(); }
        if (!(item.unwrap() instanceof ItemStack)) {
            getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Tried to use a different platform's wrapper. ");
            return; }

        // Set item, yay
        unwrap().setItem(slot, (ItemStack) item.unwrap());
    }
}