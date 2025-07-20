package gunging.ootilities.goob;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBStandalone;
import org.jetbrains.annotations.NotNull;

/**
 * The root and main class of the GooBase module of the GooSuite,
 * only exists to hold a static reference to the default engine
 * used by all GooB methods, considering they are available from
 * a static context since the spirit of GooS is to be called like
 * any other static utility library.
 * <br>
 * Naturally, you probably want to replace this static reference
 * by whichever environment-dependent one you can build to really
 * provide the environment-specific functions of GooS.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GungingOotilitiesBase {

    /**
     * The default singleton of GOOBEngine
     *
     * @since 1.0.0
     */
    @NotNull static GOOBEngine engine = new GOOBStandalone();

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public static void setEngine(@NotNull GOOBEngine singleton) { engine = singleton; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public static GOOBEngine getEngine() { return engine; }
}
