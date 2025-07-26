package gunging.ootilities.goop.engine;

import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import gunging.ootilities.goof.spigot.GOOFSpigot;
import gunging.ootilities.goof.spigot.GOOFSpigotHook;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class will handle the building of the GooP Engine
 * used for the Serverside GooP Plugin.
 *
 * @author Gunging
 * @since 2.0.0
 */
public class GOOPEngineBuilder {

    /**
     * @param plugin The minecraft server and plugins environment
     * @return A GOOPEngine for this environment, if one could be built
     *
     * @author Gunging
     * @since 2.0.0
     */
    @Nullable public static GOOPEngine build(@NotNull JavaPlugin plugin) {

        // Begin with an empty one
        GOOPEngine build = new GOOPEngine();
        FriendlyFeedbackProvider ffp = build.getOotilityFriend().newFFP();

        //todo Try both Spigot and Paper foundations, with preference for Paper of course

        // Try to build the spigot foundation
        final GOOFSpigot foundation = new GOOFSpigotHook().hookOnLoad(build, plugin, ffp);
        if (foundation == null) { return null; }

        // Must be reproduced manually since we are only initializing it now
        foundation.getSpigotReproducer().setPalette(new GooPPalette());
        build.getOotilityFriend().getDefaultReproducer().reproduce(ffp);

        //todo The foundation initialize load probably builds its GooCompatibilities,
        //     but maybe something has to be done or written or checked here too.

        // Configure it and build
        build.configureFoundation(foundation);

        // Print and complete
        return build;
    }
}
