package gunging.ootilities.goof.spigot.exploring.entities.specialization;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.*;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import gunging.ootilities.goob.exploring.entities.ISEEntityExplorer;
import gunging.ootilities.goof.spigot.exploring.entities.ISESpigotStatement;
import gunging.ootilities.goof.spigot.exploring.entities.ISESpigotStatements;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Both the armor slots and the hand slots
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISESAllEquipment extends ISESpigotStatement {

    /**
     * @param statements    A reference to the engines' spigot statements class
     * @param wrapper       The platform-dependent wrapper for this statement
     * @param statementName The name identifier for this statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISESAllEquipment(@NotNull ISESpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMEntity<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
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
    @Override public @NotNull ISESAllEquipment withOptions(@NotNull String options) { return getStatements().ALL_EQUIPMENT; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean isFundamental() { return false; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ArrayList<ItemStackExplorer<GBMEntity<?>>> whenElaborated(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        ArrayList<ItemStackExplorer<GBMEntity<?>>> ret = new ArrayList<>();

        ret.addAll(new ISEEntityExplorer(getStatements().ARMOR).elaborate(elaborator));
        ret.addAll(new ISEEntityExplorer(getStatements().HANDS).elaborate(elaborator));

        return ret;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable ItemStackLocation<GBMEntity<?>> whenRealized(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be realized. Please elaborate it first. ");
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GBMItemStack<?> readItemStack(@NotNull GBMEntity<?> target) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be read. Please elaborate it first. ");
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void writeItemStack(@NotNull GBMEntity<?> target, @Nullable GBMItemStack<?> item) {
        throw new UnsupportedOperationException("A Slot Specialization that targets multiple slots cannot be written. Please elaborate it first. ");
    }
}
