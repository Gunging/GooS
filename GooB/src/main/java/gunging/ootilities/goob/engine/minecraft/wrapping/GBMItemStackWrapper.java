package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Item Stacks.
 *
 * @param <GooF> The wrapper class for ItemStacks
 * @param <E> The ItemStack class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMItemStackWrapper<GooF extends GBMItemStack<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMItemStackWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @return The empty item stack for this platform
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMItemStack<?> empty();
}
