package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMCraftingSlots;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The fundamental functionality of Inventory View, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Inventory View class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMInventoryView<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMInventoryView(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @param slot The slot of the crafting slots to fetch
     *
     * @return The item in this slot, if it exists
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable
    public GBMItemStack<?> getCraftingItem(@NotNull GBMCraftingSlots slot) { return getCraftingItem(slot.ordinal()); }

    /**
     * @param slot The slot of the crafting slots
     * @param item The item to put in this slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public void setCraftingItem(@NotNull GBMCraftingSlots slot, @Nullable GBMItemStack<?> item) { setCraftingItem(slot.ordinal(), item); }

    /**
     * @param slot The slot of the crafting slots to fetch
     *
     * @return The item in this slot, if it exists
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract GBMItemStack<?> getCraftingItem(int slot);

    /**
     * @param slot The slot of the crafting slots
     * @param item The item to put in this slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setCraftingItem(int slot, @Nullable GBMItemStack<?> item);
}
