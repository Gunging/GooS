package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMInventoryWrapper;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Inventories into SpigotInventories
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotInventoryWrapper extends GBMInventoryWrapper<SpigotInventory, Inventory> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotInventoryWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotInventory wrap(@NotNull Inventory entity) { return new SpigotInventory(this, entity); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<Inventory> getWrappedClass() { return Inventory.class; }
}
