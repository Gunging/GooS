package gunging.ootilities.goof.spigot.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.exploring.entities.ISEEquipmentStatement;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotEquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * The default implementation of Spigot Equipment Statement
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISESpigotEquipmentStatement extends ISEEquipmentStatement<SpigotEquipmentSlot> implements EntitySpigotStatement {

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISESpigotEquipmentStatement(@NotNull ISESpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMEntity<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(wrapper, statementName);
        this.statements = statements;
    }

    /**
     * A reference to the engines' spigot statements class
     *
     * @since 1.0.0
     */
    @NotNull final ISESpigotStatements statements;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISESpigotStatements getStatements() { return statements; }
}