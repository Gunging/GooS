package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Player Inventories.
 *
 * @param <GooF> The wrapper class for Player Inventories
 * @param <E> The Player Inventory class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMPlayerInventoryWrapper<GooF extends GBMPlayerInventory<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The minecraft engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMPlayerInventoryWrapper(@NotNull GOOBMinecraft minecraft) {
        super(minecraft);
    }
}
