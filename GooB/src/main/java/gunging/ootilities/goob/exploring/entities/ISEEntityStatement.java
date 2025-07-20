package gunging.ootilities.goob.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.exploring.EXPStatementBase;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Deals with the slots of an Entity's inventory
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISEEntityStatement extends EXPStatementBase<GBMEntity<?>> {

    /**
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISEEntityStatement(@NotNull GOOBWrapperBase<? extends GBMEntity<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(wrapper, statementName);
    }

    /**
     * @param elaborator The entity that is requesting these slots, thus
     *                   elaborating something like "HOTBAR" will return
     *                   their hotbar slots... even if they were modified
     *                   by another mod.
     *
     * @return A distinct ISEEntityInventory that targets a single slot for
     *         every slot that is encoded for in this range or special slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract @NotNull ArrayList<ItemStackExplorer<GBMEntity<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator);

    /**
     * @param elaborator The entity requesting these slots, then if they ask for
     *                   the "MAINHAND" it is a valid existing slot that can be
     *                   retrieved.
     *
     * @return A real ItemStack slot/location ready to be interacted with, or <code>null</code> if it failed.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract ItemStackLocation<GBMEntity<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator);

    /**
     * @param target The player requesting these slots, then if they ask for
     *               the "MAINHAND" it is a valid existing slot that can be
     *               retrieved.
     *
     * @return The item currently present in this location
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract GBMItemStack<?> readItemStack(@NotNull GBMEntity<?> target);

    /**
     * @param target The player requesting these slots, then if they ask for
     *               the "MAINHAND" it is a valid existing slot that can be
     *               written onto.
     *
     * @param item The item to put into that slot
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void writeItemStack(@NotNull GBMEntity<?> target, @Nullable GBMItemStack<?> item);

    /**
     * Some slot specializations encode for multiple slots, in which case some
     * methods cannot be used. Indeed, it is complicated working with things
     * that mean multiple things at the same time.
     * <p>
     * Anyway, if this is true, then this is a "most-basic" building block
     * that encodes for one thing only and needs no more elaboration.
     *
     * @return If this Slot Specialization may not be elaborated further
     *
     * @author Gunging
     * @since 1.0.0
     */
     public boolean isFundamental() { return true; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ItemStackExplorer<GBMEntity<?>> prepareExplorer() { return new ISEEntityExplorer(this); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ISEEntityElaborator prepareElaborator(@NotNull GBMEntity<?> target) { return new ISEEntityElaborator(target); }
}
