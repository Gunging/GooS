package gunging.ootilities.goof.spigot;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.hooking.GOOBDualInitHook;
import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackCategory;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This will receive the minecraft server when GooP loads
 * and determine if it is appropriate to be used as the
 * GooFoundation of the engine.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GOOFSpigotHook implements GOOBDualInitHook<GOOFSpigot, Server> {

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GOOFSpigot hookOnLoad(@NotNull GOOBEngine engine, @NotNull Server server, @Nullable FriendlyFeedbackProvider ffp) {
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Hooking GooFoundation:$d Spigot$b... ");

        // todo Check that this environment is compatible with spigot
        GOOFSpigot goof = new GOOFSpigot(engine, server);

        // Configure it so that Friendly Feedback Providers don't use the Java Console and rather use the Server Console
        engine.getOotilityFriend().setDefaultReproducer(goof.getSpigotReproducer());

        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Hooking complete.$s Success$b. ");
        return goof;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @Nullable GOOFSpigot hookOnEnable(@NotNull GOOBEngine engine, @NotNull Server server, @Nullable FriendlyFeedbackProvider ffp) {
        engine.scrutinize(GOOBScrutiny.TESTING_MID, "Hooking onto the Spigot GooFoundation must occur during Plugin Load, not Enable. ");
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Hooking must occur during load step.$f Cancelled$b. ");
        return null;
    }
}
