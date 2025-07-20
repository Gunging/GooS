package gunging.ootilities.goof.spigot.exploring.players.specalization;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.players.ISPPlayerExplorer;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotEquipmentStatement;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatements;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotEquipmentSlot;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * The slot currently held by the player.
 *
 * @since 1.0.0
 * @author Gunging
 */
public class ISPSMainhand extends ISPSpigotEquipmentStatement {

    /**
     * @param statements    A reference to the engines' spigot statements class
     * @param wrapper       The platform-dependent wrapper for this statement
     * @param statementName The name identifier for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPSMainhand(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(statements, wrapper, statementName);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ISPSMainhand withOptions(@NotNull String options) { return getStatements().MAINHAND; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull SpigotEquipmentSlot getEquipmentSlot() { return getStatements().getMinecraft().getEquipmentSlots().wrap(EquipmentSlot.HAND); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMPlayer<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMPlayer<?>>> ret = new ArrayList<>();

        ret.add(new ISPPlayerExplorer(getStatements().MAINHAND));

        return ret;
    }
}
