package gunging.ootilities.goob.exploring.players;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.ootilities.OotilityNumbers;
import gunging.ootilities.goob.ootilities.utility.IntegerNumberRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Deals with numbered inventory slots in a player's inventory
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public abstract class ISPIndexedStatement extends ISPPlayerStatement {

    /**
     * @return The minimum slot index covered by this Indexed Statement (usually zero)
     *
     * @since 1.0.0
     */
    public abstract int getMinimumRange(@NotNull GBMPlayer<?> target);

    /**
     * @return The maximum slot index covered by this Indexed Statement
     *
     * @since 1.0.0
     */
    public abstract int getMaximumRange(@NotNull GBMPlayer<?> target);

    /**
     * The numeric slot range or index for the inventory GUI
     *
     * @since 1.0.0
     */
    @NotNull final IntegerNumberRange numericSlot;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public IntegerNumberRange getNumericSlot() { return numericSlot; }


    /**
     * @param elaborator The player that is requesting these slots, thus
     *                   elaborating something like "HOTBAR" will return
     *                   their hotbar slots... even if they were modified
     *                   by another mod.
     *
     * @return A distinct ISEPlayerInventory that targets a single slot for
     *         every slot that is encoded for in this range or special slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMPlayer<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMPlayer<?>>> ret = new ArrayList<>();

        // If simple... we are kind of done but whatever
        if (getNumericSlot().isSimple()) {
            //noinspection DataFlowIssue
            ret.add(new ISPPlayerExplorer(of(OotilityNumbers.Round(getNumericSlot().getMinimumInclusive()))));

            // If there is range, elaborate
        } else {

            // Darn modded minecraft! I cant even assume the inventory size (plugin developer moment)
            int minInventorySize = getMinimumRange(elaborator.getElaboratee());
            int maxInventorySize = getMaximumRange(elaborator.getElaboratee());

            int min = getNumericSlot().getMinimumInclusive() == null ? minInventorySize : OotilityNumbers.Round(getNumericSlot().getMinimumInclusive());
            int max = getNumericSlot().getMaximumInclusive() == null ? maxInventorySize : OotilityNumbers.Round(getNumericSlot().getMaximumInclusive());

            // I mean, just add every one of those slots inclusive of the upper and lower bound
            for (int i = min; i <= max; i++) {

                // Cancel if the max inventory size was reached
                if (i >= maxInventorySize) {break;}

                // Yes include this slot it is a nice beautiful CLEAN slot
                ret.add(new ISPPlayerExplorer(of(i)));
            }
        }

        return ret;
    }

    /**
     * Creates an Indexed Statement "ANY" that represents
     * all the slots of this indexed inventory.
     * 
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement(@NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name) {
        super(wrapper, name);
        numericSlot = new IntegerNumberRange(null, null);
    }

    /**
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param slot The slot of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement(@NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int slot) {
        super(wrapper, name);
        numericSlot = new IntegerNumberRange(slot, slot);
    }

    /**
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param qnr The slot range of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement(@NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, @NotNull IntegerNumberRange qnr) {
        super(wrapper, name);
        numericSlot = qnr;
    }

    /**
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param minSlot The first inventory slot of the range [inclusive]
     * @param maxSlot The last inventory slot of the range [inclusive]
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement(@NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int minSlot, int maxSlot) {
        super(wrapper, name);
        numericSlot = new IntegerNumberRange(minSlot, maxSlot);
    }

    /**
     * @return A clone of this indexed statement
     */
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override public ISPIndexedStatement clone() { return of(numericSlot); }

    /**
     * Creates an Indexed Statement "ANY" that represents
     * all the slots of this indexed inventory.
     *
     * @return Essentially a clone, but with different index
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement any() { return of(-1, -1); }

    /**
     * @param slot The slot of this inventory in question
     *
     * @return Essentially a clone, but with different index
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement of(int slot) { return of(slot, -1); }

    /**
     * @param minSlot The first inventory slot of the range [inclusive]
     * @param maxSlot The last inventory slot of the range [inclusive]
     *
     * @return Essentially a clone, but with different minimum and maximum indices
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPIndexedStatement of(int minSlot, int maxSlot) { return of(new IntegerNumberRange(minSlot, maxSlot)); }

    /**
     * @param qnr The slot range of this inventory in question
     *
     * @return Essentially a clone, but with different minimum and maximum indices
     *
     * @since 1.0.0
     * @author Gunging
     */
    public abstract ISPIndexedStatement of(@NotNull IntegerNumberRange qnr);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getOptions() { return numericSlot.toString(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ISPIndexedStatement withOptions(@NotNull String options) {
        IntegerNumberRange rebuilt = IntegerNumberRange.GetFromString(options);
        if (rebuilt == null) { return null; }
        return this.of(rebuilt);
    }
}
