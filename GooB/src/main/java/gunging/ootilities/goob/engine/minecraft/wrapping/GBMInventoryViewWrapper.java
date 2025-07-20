package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Inventory Views.
 *
 * @param <GooF> The wrapper class for Inventory Views
 * @param <E> The Inventory Views class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMInventoryViewWrapper<GooF extends GBMInventoryView<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMInventoryViewWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }
}
