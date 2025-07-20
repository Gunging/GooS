package gunging.ootilities.goob.exploring;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a specific inventory slot somewhere, ready to
 * be interacted with by reading or modifying its contents.
 * It is the final result of evaluating an ItemStack Explorer
 *
 * @see ItemStackExplorer
 *
 * @param <Elaboratee> The class that this expects for statements
 *                     to be resolved and elaborated.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface ItemStackLocation<Elaboratee> {

    /**
     * @return The class of the target of the elaborator.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull Class<?> getHolderClass();

    /**
     * @param holder The object that may be accepted by this Item Stack Location
     *
     * @return If this Item Stack Location could accept this holder type of objects
     *
     * @author Gunging
     * @since 1.0.0
     */
    default boolean acceptsHolder(@Nullable Object holder) { return getHolderClass().isInstance(holder); }

    /**
     * @return The explorer statement that was used to obtain this Item Stack Location
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull ItemExplorerStatement<Elaboratee> getStatement();

    /**
     * @return The container of this ItemStack Location,
     *         whatever that means in this context
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull Elaboratee getHolder();

    /**
     * @param stack The ItemStack to place in this slot
     *
     * @author Gunging
     * @since 1.0.0
     */
    void setItemStack(@Nullable GBMItemStack<?> stack);

    /**
     * @return The item stack currently in this slot
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable GBMItemStack<?> getItemStack();

    /**
     * @return If this object can be used as an elaboration target by this Item Stack Location
     *
     * @since 1.0.0
     * @author Gunging
     */
    default boolean acceptsTarget(@Nullable Object target) { return getHolderClass().isInstance(target); }
}
