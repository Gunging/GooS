package gunging.ootilities.goof.spigot;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.ootilities.OotilityFriend;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackCategory;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The final result of the foundation architecture, the capabilities
 * that were first defined in the base module are now filled.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class GOOFSpigot extends GOOFoundationSpigot {

    /**
     * @param engine The engine this foundation is part of.
     * @param server The Minecraft Server where this foundation is running on.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOFSpigot(@NotNull GOOBEngine engine, @NotNull Server server) { super(engine, server); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean initializeOnLoad(@NotNull Server server, @Nullable FriendlyFeedbackProvider ffp) {
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Initializing GooFoundation:$d Spigot$b... ");

        // todo Load version-dependent wrappers

        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Initialization complete.$s Success$b. ");
        return super.initializeOnLoad(server, ffp);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public boolean initializeOnEnable(@NotNull Server server, @Nullable FriendlyFeedbackProvider ffp) {
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Enabling GooFoundation:$d Spigot$b... ");

        // todo Plugin enable stuff

        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Enabling complete.$s Success$b. ");
        return super.initializeOnEnable(server, ffp);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public void shutdownOnDisable(@NotNull Server server, @Nullable FriendlyFeedbackProvider ffp) {
        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Shutting down GooFoundation:$d Spigot$b... ");

        // todo Plugin disable stuff

        OotilityFriend.Log(ffp, FriendlyFeedbackCategory.INFORMATION, "Shutdown complete.$s Concluded$b. ");
        super.shutdownOnDisable(server, ffp);
    }
}
