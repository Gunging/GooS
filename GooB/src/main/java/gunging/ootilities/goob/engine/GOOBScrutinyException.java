package gunging.ootilities.goob.engine;

import org.jetbrains.annotations.NotNull;

/**
 * An exception thrown when GooB scrutinizes your code.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GOOBScrutinyException extends RuntimeException implements GOOBFuel {

    /**
     * The scrutiny required for this exception to be thrown.
     *
     * @since 1.0.0
     */
    @NotNull final GOOBScrutiny requiredScrutiny;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public GOOBScrutiny getRequiredScrutiny() { return requiredScrutiny; }

    /**
     * @param level The level of scrutiny that this exception begins firing at
     * @param message The message sent to the console
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOBScrutinyException(@NotNull GOOBEngine engine, @NotNull GOOBScrutiny level, @NotNull String message) {
        super(message);
        requiredScrutiny = level;
        this.engine = engine;
    }

    /**
     * The engine this exception is running on
     *
     * @since 1.0.0
     */
    @NotNull final GOOBEngine engine;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return engine; }
}
