package gunging.ootilities.goob.exploring;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import org.jetbrains.annotations.NotNull;

/**
 * A class for Item Explorer components that depend on statements.
 *
 * @param <Elaboratee> The class that this expects for statements
 *                     to be resolved and elaborated.
 *
 * @since 1.0.0
 * @author Gunging
 */
public abstract class EXPStatementBased<Elaboratee> implements GOOBFuel, GOOBMinecraftFuel {

    /**
     * @param statement The Item Explorer Statement associated with this.
     *
     * @since 1.0.0
     * @author Gunging
     */
    public EXPStatementBased(@NotNull EXPStatementBase<Elaboratee> statement) { this.statement = statement; }

    /**
     * The Item Explorer Statement associated with this.
     *
     * @since 1.0.0
     */
    @NotNull EXPStatementBase<Elaboratee> statement;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public EXPStatementBase<Elaboratee> getStatement() { return statement; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBMinecraft getMinecraft() { return statement.getMinecraft(); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }
}
