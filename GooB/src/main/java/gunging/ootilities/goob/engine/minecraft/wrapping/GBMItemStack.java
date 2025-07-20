package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The fundamental functionality of ItemStacks, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The ItemStack class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMItemStack<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMItemStack(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @return The display name of this ItemStack
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract String getDisplayName();

    /**
     * If this ItemStack has no display name, this
     * will be the name it is assumed to have.
     *
     * @return Ideally, the name of the material of this item,
     *         perhaps something that can be serialized into a
     *         file and then deserialized later.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract String getMaterialName();

    /**
     * @param rename The name to set in the contained ItemStack
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setItemName(@Nullable String rename);

    /**
     * @return The count of items in this ItemStack
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract int getCount();

    /**
     * @return Another wrapper, holding a clone of the wrapped ItemStack.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMItemStack<E> clone();
}
