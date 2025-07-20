package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStackWrapper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps ItemStacks into SpigotItemStacks
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotItemStackWrapper extends GBMItemStackWrapper<SpigotItemStack, ItemStack> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotItemStackWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull SpigotItemStack wrap(@NotNull ItemStack itemStack) { return new SpigotItemStack(this, itemStack); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<ItemStack> getWrappedClass() { return ItemStack.class; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull GBMItemStack<?> empty() { return wrap(new ItemStack(Material.AIR)); }
}
