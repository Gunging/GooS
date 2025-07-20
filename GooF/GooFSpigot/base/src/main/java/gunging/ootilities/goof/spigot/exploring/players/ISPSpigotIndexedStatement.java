package gunging.ootilities.goof.spigot.exploring.players;

import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.players.ISPIndexedStatement;
import gunging.ootilities.goob.ootilities.utility.IntegerNumberRange;
import org.jetbrains.annotations.NotNull;

/**
 * The default implementation of Spigot Indexed Statement
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class ISPSpigotIndexedStatement extends ISPIndexedStatement implements PlayerSpigotStatement {

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSpigotIndexedStatement(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name) {
        super(wrapper, name);
        this.statements = statements;
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param slot The slot of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSpigotIndexedStatement(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int slot) {
        super(wrapper, name, slot);
        this.statements = statements;
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param qnr The slot range of this inventory in question
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSpigotIndexedStatement(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, @NotNull IntegerNumberRange qnr) {
        super(wrapper, name, qnr);
        this.statements = statements;
    }

    /**
     * @param statements A reference to the engines' spigot statements class
     * @param name The name identifier for this statement
     * @param wrapper The platform-dependent wrapper for the elaborator this statement
     * @param minSlot The first inventory slot of the range [inclusive]
     * @param maxSlot The last inventory slot of the range [inclusive]
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPSpigotIndexedStatement(@NotNull ISPSpigotStatements statements, @NotNull GOOBWrapperBase<? extends GBMPlayer<?>,?> wrapper, @NotNull GBMNamespacedKey<?> name, int minSlot, int maxSlot) {
        super(wrapper, name, minSlot, maxSlot);
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
