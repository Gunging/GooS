package gunging.ootilities.goob.engine;

import org.jetbrains.annotations.NotNull;

/**
 * The default (and empty) implementation of the GOOB Engine
 * object that supports only environment-independent operations,
 * namely pure-java kind of things such as Vector Math and String
 * Manipulation.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GOOBStandalone extends GOOBEngine {

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getDefaultNamespace() { return "gungingoob"; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBScrutiny getScrutiny() { return GOOBScrutiny.DEVELOPMENT_HIGH; }
}
