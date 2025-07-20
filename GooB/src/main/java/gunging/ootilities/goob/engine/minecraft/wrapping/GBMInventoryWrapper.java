package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Inventories.
 *
 * @param <GooF> The wrapper class for Inventories
 * @param <E> The Inventory class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMInventoryWrapper<GooF extends GBMInventory<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMInventoryWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }
}
