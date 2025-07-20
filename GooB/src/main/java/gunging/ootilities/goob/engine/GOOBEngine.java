package gunging.ootilities.goob.engine;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.ootilities.*;
import gunging.ootilities.goob.ootilities.friendly.provided.GooDFriend;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The GOOB Engine object is essentially the aggregation of all
 * the GooS ootilities, built (usually upon startup) based on all
 * available environment-dependent compatibilities.
 * <br><br>
 * I recommend simply building it once upon startup, assigning its
 * singleton, and keeping it as a glorified static reference; unless
 * you for some reason want different parallel GOOB Engines.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class GOOBEngine implements GOOBFuel {

    //region GOOBEngine Dynamic Capabilities
    /**
     * @return Ootilities that deal with minecraft, platform-dependently, of this GOOB Engine.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public OotilityMinecraft getOotilityMinecraft() {
        scrutinize(GOOBScrutiny.TESTING_MID, "Trying to access OotilityMinecraft in an engine that does not support it. ");
        return null;
    }

    /**
     * @return Everything that deals with minecraft, platform-dependently, of this GOOB Engine.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GOOBMinecraft getMinecraft() {
        scrutinize(GOOBScrutiny.TESTING_MID, "Trying to access Minecraft in an engine that does not support it. ");
        return null;
    }
    //endregion

    //region GOOBEngine Default Capabilities
    /**
     * @since 1.0.0
     */
    @NotNull OotilityVectors ootilityVectors = new OotilityVectors(this);
    /**
     * @return Ootilities that deal with vectors, platform-dependently, of this GOOB Engine.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public OotilityVectors getOotilityVectors() {
        scrutinize(GOOBScrutiny.DEVELOPMENT_HIGH, "Trying to access OotilityVectors in an engine that does not explicitly support it. ");
        return ootilityVectors;
    }

    /**
     * @since 1.0.0
     */
    @NotNull OotilityNumbers ootilityNumbers = new OotilityNumbers(this);
    /**
     * @return Ootilities that deal with numbers of this GOOB Engine.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull OotilityNumbers getOotilityNumbers() { return ootilityNumbers; }

    /**
     * @since 1.0.0
     */
    @NotNull OotilityStrings ootilityStrings = new OotilityStrings(this);
    /**
     * @return Ootilities that deal with strings of this GOOB Engine.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull OotilityStrings getOotilityStrings() { return ootilityStrings; }

    /**
     * @since 1.0.0
     */
    @NotNull OotilityFriend ootilityFriend = new GooDFriend(this);
    /**
     * @return Ootilities that deal with the Friendly Feedback Provider system.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull OotilityFriend getOotilityFriend() { return ootilityFriend; }
    //endregion

    //region GOOBEngine Default Methods
    /**
     * @return The default namespace to use for Namespaced Keys.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract String getDefaultNamespace();

    /**
     * @return The java-developer scrutiny level setting for this engine
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public GOOBScrutiny getScrutiny() { return GOOBScrutiny.TESTING_MID; }

    /**
     * @param scrutiny The lowest scrutiny level this operation going wrong causes a crash.
     * @return If, under the provided scrutiny level, GooS should throw a java exception instead of failing silently.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public boolean shouldCrash(@NotNull GOOBScrutiny scrutiny) { return getScrutiny().ordinal() <= scrutiny.ordinal(); }

    /**
     * This will throw an exception if this scrutiny level
     * is met or exceeded. Otherwise, nothing happens.
     *
     * @param message Message of the exception
     * @param scrutiny The scrutiny level needed for this exception to fire
     *
     * @author Gunging
     * @since 1.0.0
     */
    public void scrutinize(@NotNull GOOBScrutiny scrutiny, @NotNull String message) {
        if (shouldCrash(scrutiny)) { throw new GOOBScrutinyException(this, scrutiny, message); }
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return this; }
    //endregion
}
