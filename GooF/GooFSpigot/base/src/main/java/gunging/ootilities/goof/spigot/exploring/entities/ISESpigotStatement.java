package gunging.ootilities.goof.spigot.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.exploring.entities.ISEEntityStatement;
import org.jetbrains.annotations.NotNull;

/**
 * The default implementation of Spigot Statement
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISESpigotStatement extends ISEEntityStatement implements EntitySpigotStatement {

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISESpigotStatement(@NotNull ISESpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMEntity<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
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
