package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMInventoryViewWrapper;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Inventory View into SpigotInventoryViews
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotInventoryViewWrapper extends GBMInventoryViewWrapper<SpigotInventoryView, InventoryView> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotInventoryViewWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotInventoryView wrap(@NotNull InventoryView view) { return new SpigotInventoryView(this, view); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<InventoryView> getWrappedClass() { return InventoryView.class; }
}
