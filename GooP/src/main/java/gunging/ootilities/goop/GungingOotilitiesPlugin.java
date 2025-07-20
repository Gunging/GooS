package gunging.ootilities.goop;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackCategory;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import gunging.ootilities.goop.engine.GOOPEngine;
import gunging.ootilities.goop.engine.GOOPEngineBuilder;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The plugin implementation of the GooSuite
 *
 * @author Gunging
 * @since 2.0.0
 */
public class GungingOotilitiesPlugin extends JavaPlugin {

    /**
     * Guaranteed to not be null as long as the plugin loaded correctly.
     *
     * @since 2.0.0
     * @author Gunging
     */
    public static GOOPEngine getGooP() { return engine; }

    /**
     * @since 2.0.0
     */
    static GOOPEngine engine;

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public void onLoad() {
        /*
         * When GooP builds its engine, I carefully check the server for minecraft
         * version, bukkit API version, third party compatibilities, and so on. All
         * that this does is go over everything in there that GooP could add support
         * to and choose the appropriate modules to compose its engine from.
         *
         * That is why the GOOPEngine is built dynamically.
         *
         * If you wanted to build an engine yourself for your own custom needs, it may
         * be easiest to just override its abstract methods, then create a new instance
         * of it like any other class.
         *
         * CustomGooPEngine myEngine = new CustomGooPEngine();
         * GungingOotilitiesBase.setEngine(myEngine);
         */
        engine = GOOPEngineBuilder.build(getServer());

        // If it is null, GooP failed to load.
        if (engine == null) {
            FriendlyFeedbackProvider ffp = GungingOotilitiesBase.getEngine().getOotilityFriend().newFFPAuto();
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "$fFailed to load GooP in this environment.$b ");
            return; }

        // If it exists, GooP loaded, yay.
        GungingOotilitiesBase.setEngine(engine); // Recommended to set the default static reference before initialization

        // Initialize
        FriendlyFeedbackProvider ffp = getGooP().getOotilityFriend().newFFPAuto();
        getGooP().initializeOnLoad(getServer(), ffp);
    }

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public void onEnable() {

        // If this is not a GOOPEngine, we failed to load.
        if (getGooP() == null) {
            FriendlyFeedbackProvider ffp = GungingOotilitiesBase.getEngine().getOotilityFriend().newFFPAuto();
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "$fSkipped enabling GooP in this environment.$b ");
            return; }

        // Enable
        FriendlyFeedbackProvider ffp = getGooP().getOotilityFriend().newFFPAuto();
        getGooP().initializeOnEnable(getServer(), ffp);
    }

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public void onDisable() {

        // If this is not a GOOPEngine, we failed to load.
        if (getGooP() == null) {
            FriendlyFeedbackProvider ffp = GungingOotilitiesBase.getEngine().getOotilityFriend().newFFPAuto();
            OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "$fSkipped disabling GooP in this environment.$b ");
            return; }

        // Enable
        FriendlyFeedbackProvider ffp = getGooP().getOotilityFriend().newFFPAuto();
        getGooP().shutdownOnDisable(getServer(), ffp);
    }
}
