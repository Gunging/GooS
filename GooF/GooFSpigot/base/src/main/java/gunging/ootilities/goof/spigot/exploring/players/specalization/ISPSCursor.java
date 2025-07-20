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
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatement;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatements;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * The cursor slot, only really exists while the inventory GUI is open
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISPSCursor extends ISPSpigotStatement {

    /**
     * @param statements    A reference to the engines' spigot statements class
     * @param wrapper       The platform-dependent wrapper for this statement
     * @param statementName The name identifier for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPSCursor(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(statements, wrapper, statementName);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getOptions() { return ""; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ISPSCursor withOptions(@NotNull String options) { return getStatements().CURSOR; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMPlayer<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMPlayer<?>>> ret = new ArrayList<>();

        ret.add(new ISPPlayerExplorer(getStatements().CURSOR));

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
    @Override public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMPlayer<?> target) { return target.getCursor(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void writeItemStack(@NotNull GBMPlayer<?> target, @Nullable GBMItemStack<?> item) { target.setCursor(item); }
}
