package gunging.ootilities.goof.spigot.exploring.players;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.players.ISPPlayerStatement;
import org.jetbrains.annotations.NotNull;

/**
 * The default implementation of Spigot Statement
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISPSpigotStatement extends ISPPlayerStatement implements PlayerSpigotStatement {

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param statementName The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSpigotStatement(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> statementName) {
        super(wrapper, statementName);
        this.statements = statements;
    }

    /**
     * A reference to the engines' spigot statements class
     *
     * @since 1.0.0
     */
    @NotNull final ISPSpigotStatements statements;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISPSpigotStatements getStatements() { return statements; }
}
