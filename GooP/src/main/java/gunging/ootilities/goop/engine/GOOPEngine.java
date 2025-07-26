package gunging.ootilities.goop.engine;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.hooking.GOOBDisableShutdown;
import gunging.ootilities.goob.engine.hooking.GOOBDuallyInitializable;
import gunging.ootilities.goob.ootilities.OotilityMinecraft;
import gunging.ootilities.goob.ootilities.friendly.FriendlyFeedbackProvider;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A powerful GooB engine that can hook onto various third
 * party plugins and work in many minecraft versions from
 * bukkit-based serverside.
 *
 * @author Gunging
 * @since 2.0.0
 */
public class GOOPEngine extends GOOBEngine implements GOOBDuallyInitializable<JavaPlugin>, GOOBDisableShutdown<JavaPlugin> {

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public @NotNull String getDefaultNamespace() { return "gungingoop"; }

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public @NotNull GOOBScrutiny getScrutiny() { return GOOBScrutiny.PRODUCTION_UNBREAKABLE; }

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public boolean initializeOnLoad(@NotNull JavaPlugin server, @Nullable FriendlyFeedbackProvider ffp) {

        // The GooFoundation takes care of actual initialization lol
        return getMinecraft().initializeOnLoad(server, ffp);
    }

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public boolean initializeOnEnable(@NotNull JavaPlugin server, @Nullable FriendlyFeedbackProvider ffp) {

        // The GooFoundation takes care of actual enabling lol
        return getMinecraft().initializeOnEnable(server, ffp);
    }

    /**
     * @since 2.0.0
     */
    @SuppressWarnings("NotNullFieldNotInitialized")
    @NotNull GOOFoundationSpigot spigotPlatform;

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return spigotPlatform; }

    /**
     * @since 2.0.0
     */
    @SuppressWarnings("NotNullFieldNotInitialized")
    @NotNull OotilityMinecraft ootilityMinecraft;

    /**
     * @author Gunging
     * @since 2.0.0
     */
    @Override public @NotNull OotilityMinecraft getOotilityMinecraft() { return ootilityMinecraft; }

    /**
     * <b>Only touch if you know what you are doing.</b> Normally, this is
     * configured during server startup by the {@link GOOPEngineBuilder}
     *
     * @param goof The chosen GooFoundation for this GooPlugin.
     *
     * @author Gunging
     * @since 2.0.0
     */
    public void configureFoundation(@NotNull GOOFoundationSpigot goof) {
        spigotPlatform = goof;
        ootilityMinecraft = new OotilityMinecraft(goof);
    }
}
