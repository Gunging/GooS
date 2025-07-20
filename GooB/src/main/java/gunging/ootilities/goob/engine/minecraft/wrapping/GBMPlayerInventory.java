package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMCraftingSlots;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The fundamental functionality of Player Inventory, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Player Inventory class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMPlayerInventory<E> extends GBMInventory<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMPlayerInventory(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) { super(wrapper, wrap); }
}

