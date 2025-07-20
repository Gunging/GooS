package gunging.ootilities.goof.spigot.exploring.players.specalization;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import gunging.ootilities.goob.exploring.players.ISPPlayerExplorer;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatement;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatements;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Targets all slots of the crafting grid.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISPSAllCrafting extends ISPSpigotStatement {

    /**
     * @param statements    A reference to the engines' spigot statements class
     * @param wrapper       The platform-dependent wrapper for this statement
     * @param statementName The name identifier for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPSAllCrafting(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(statements, wrapper, statementName);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override  public @NotNull String getOptions() { return ""; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ISPSAllCrafting withOptions(@NotNull String options) { return getStatements().ALL_CRAFTING; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean isFundamental() { return false; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMPlayer<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMPlayer<?>>> ret = new ArrayList<>();

        ret.add(new ISPPlayerExplorer(getStatements().CRAFTING_RESULT));
        ret.add(new ISPPlayerExplorer(getStatements().CRAFTING.of(1)));
        ret.add(new ISPPlayerExplorer(getStatements().CRAFTING.of(2)));
        ret.add(new ISPPlayerExplorer(getStatements().CRAFTING.of(3)));
        ret.add(new ISPPlayerExplorer(getStatements().CRAFTING.of(4)));

        return ret;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ItemStackLocation<GBMPlayer<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be realized. Please elaborate it first. ");
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMPlayer<?> target) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be read. Please elaborate it first. ");
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void writeItemStack(@NotNull GBMPlayer<?> target, @Nullable GBMItemStack<?> item) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be written. Please elaborate it first. ");
    }
}
