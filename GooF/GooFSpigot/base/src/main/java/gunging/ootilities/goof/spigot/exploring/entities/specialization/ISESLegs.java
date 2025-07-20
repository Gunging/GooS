package gunging.ootilities.goof.spigot.exploring.entities.specialization;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.entities.ISEEntityExplorer;
import gunging.ootilities.goof.spigot.exploring.entities.ISESpigotEquipmentStatement;
import gunging.ootilities.goof.spigot.exploring.entities.ISESpigotStatements;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotEquipmentSlot;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * The slot for armor legs
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISESLegs extends ISESpigotEquipmentStatement {

    /**
     * @param statements    A reference to the engines' spigot statements class
     * @param wrapper       The platform-dependent wrapper for this statement
     * @param statementName The name identifier for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISESLegs(@NotNull ISESpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMEntity<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(statements, wrapper, statementName);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ISESLegs withOptions(@NotNull String options) { return getStatements().LEGS; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull SpigotEquipmentSlot getEquipmentSlot() { return getStatements().getMinecraft().getEquipmentSlots().wrap(EquipmentSlot.LEGS); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMEntity<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMEntity<?>>> ret = new ArrayList<>();

        ret.add(new ISEEntityExplorer(getStatements().LEGS));

        return ret;
    }
}
