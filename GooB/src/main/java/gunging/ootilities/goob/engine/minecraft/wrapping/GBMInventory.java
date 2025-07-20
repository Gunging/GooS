package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The fundamental functionality of Inventory Container, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Inventory class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMInventory<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMInventory(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @return The size of this inventory container
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract int getSize();

    /**
     * @param slot The slot to search for
     *
     * @return The item this entity has in this slot
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract GBMItemStack<?> getItem(int slot);

    /**
     * @param slot The slot to put the item in
     * @param item The item to put in this slot
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setItem(int slot, @Nullable GBMItemStack<?> item);
}
