package gunging.ootilities.goob.engine;

import gunging.ootilities.goob.GungingOotilitiesBase;
import org.jetbrains.annotations.NotNull;

/**
 * Allows GooB operations to know in which engine they are running
 * <br><br>
 * An engine requires fuel to run, or something like that I just needed a good name for this.
 * Anyway, since GooS aims to run as a static library but keeping the ability to instantiate
 * it per-environment you would possibly have simultaneously running (perhaps in multiple
 * threads what do I know), any of its components needs to know which engine it belongs to
 * so that they can call its own engine instead of the static engine provided by default.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBFuel {

    /**
     * @return The GooB Engine this operation is running on
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull default GOOBEngine getEngine() {

        // Not overriding this method crashes in developer mode or higher scrutiny
        GungingOotilitiesBase.getEngine().scrutinize(GOOBScrutiny.DEVELOPMENT_HIGH, "Anything that runs on GooS should keep a reference to its own engine even if it is the default static one. ");

        // It did not crash? Then wing it, use the default engine
        return GungingOotilitiesBase.getEngine();
    }
}
