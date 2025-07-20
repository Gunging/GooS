package gunging.ootilities.goof.spigot.exploring.players.specalization;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import gunging.ootilities.goob.exploring.players.ISPPlayerExplorer;
import gunging.ootilities.goob.exploring.players.ISPPlayerLocation;
import gunging.ootilities.goob.ootilities.utility.IntegerNumberRange;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotIndexedStatement;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatements;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A specific slot of the enderchest
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISPSEnderchest extends ISPSpigotIndexedStatement {

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSEnderchest(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name) {
        super(statements, wrapper, name);
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     * @param slot The slot of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSEnderchest(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int slot) {
        super(statements, wrapper, name, slot);
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     * @param qnr The slot range of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSEnderchest(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, @NotNull IntegerNumberRange qnr) {
        super(statements, wrapper, name, qnr);
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     * @param minSlot The first inventory slot of the range [inclusive]
     * @param maxSlot The last inventory slot of the range [inclusive]
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSEnderchest(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int minSlot, int maxSlot) {
        super(statements, wrapper, name, minSlot, maxSlot);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int getMinimumRange(@NotNull GBMPlayer<?> target) { return 0; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int getMaximumRange(@NotNull GBMPlayer<?> target) { return target.getEnderchest().getSize(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public ISPSEnderchest of(@NotNull IntegerNumberRange qnr) {
        ISPSEnderchest crafting = new ISPSEnderchest(getStatements(), (GOOBWrapperBase<? extends GBMPlayer<?>,?>) getElaboratorWrapper(), getStatementName(), qnr);
        crafting.setNetworkIndex(getNetworkIndex());
        return crafting;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMPlayer<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMPlayer<?>>> ret = new ArrayList<>();

        ret.add(new ISPPlayerExplorer(clone()));

        return ret;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ItemStackLocation<GBMPlayer<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        return new ISPPlayerLocation(elaborator.getElaboratee(), this);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMPlayer<?> target) {
        if (!getNumericSlot().isSimple()) { return null; }
        @SuppressWarnings("DataFlowIssue") int min = getNumericSlot().getMinimumInclusive();
        if (min < getMinimumRange(target) || min >= getMaximumRange(target)) { return null; }

        // Return that slot
        return target.getEnderchest().getItem(min);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void writeItemStack(@NotNull GBMPlayer<?> target, @Nullable GBMItemStack<?> item) {
        if (!getNumericSlot().isSimple()) { return; }
        @SuppressWarnings("DataFlowIssue") int min = getNumericSlot().getMinimumInclusive();
        if (min < getMinimumRange(target) || min >= getMaximumRange(target)) { return; }

        // It is best to set AIR item stacks than null ones
        if (item == null) {
            target.getEnderchest().setItem(min, getStatements().getMinecraft().getItemStacks().empty());
        } else {
            target.getEnderchest().setItem(min, item);
        }
    }
}
