package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper for the Spigot ItemStack
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotItemStack extends GBMItemStack<ItemStack> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotItemStack(@NotNull SpigotItemStackWrapper wrapper, @NotNull ItemStack wrap) {
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
    @Override
    public @Nullable String getDisplayName() {

        // Obtain from Item Meta
        ItemStack stack = unwrap();
        if (!stack.hasItemMeta()) { return null; }
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) { return null; }
        if (!meta.hasDisplayName()) { return null; }
        return meta.getDisplayName();
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getMaterialName() { return unwrap().getType().toString(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void setItemName(@Nullable String rename) {

        // Modify into Item Meta
        ItemStack stack = unwrap();
        if (!stack.hasItemMeta()) { return; }
        ItemMeta meta = stack.getItemMeta();
        if (meta == null) { return; }
        meta.setDisplayName(rename);
        stack.setItemMeta(meta);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int getCount() { return unwrap().getAmount(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GBMItemStack<ItemStack> clone() {
        return (GBMItemStack<ItemStack>) getWrapper().wrap(unwrap().clone());
    }
}
