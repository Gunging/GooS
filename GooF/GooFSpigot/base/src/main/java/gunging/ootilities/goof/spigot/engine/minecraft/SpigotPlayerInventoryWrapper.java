package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayerInventoryWrapper;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Player Inventory into SpigotPlayerInventory
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotPlayerInventoryWrapper extends GBMPlayerInventoryWrapper<SpigotPlayerInventory, PlayerInventory> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotPlayerInventoryWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotPlayerInventory wrap(@NotNull PlayerInventory player) { return new SpigotPlayerInventory(this, player); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<PlayerInventory> getWrappedClass() { return PlayerInventory.class; }
}
