package gunging.ootilities.goof.spigot.ootilities.friendly;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import gunging.ootilities.goob.ootilities.friendly.*;
import gunging.ootilities.goob.ootilities.friendly.palette.FriendlyFeedbackPalette;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Uses bukkit and spigot loggers to print messages onto the Minecraft Server
 * console, and optionally even sends those messages to players with developer
 * mode logging enabled.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotConsoleReproducer implements FriendlyReproducer, GOOBFuel, GOOBMinecraftFuel, FriendlySimpleReproducer {

    /**
     * Code returned when a null Friendly Feedback Provider was passed, which means
     * nothing is printed since we are skipping the Friendly Feedback system for now.
     *
     * @since 1.0.0
     */
    public static final int CANCELLED_NULL_FFP = 1;

    /**
     * The FFP Contents were printed successfully yay
     *
     * @since 1.0.0
     */
    public static final int SUCCESS = 2;

    /**
     * @param minecraft The engine this is running in
     * @param palette Colors to reproduce message with
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotConsoleReproducer(@NotNull GOOFoundationSpigot minecraft, @NotNull FriendlyFeedbackPalette palette) {
        this.minecraft = minecraft;
        setPalette(palette);
    }

    /**
     * @param palette Colors to reproduce message with
     *
     * @author Gunging
     * @since 1.0.0
     */
    public void setPalette(@NotNull FriendlyFeedbackPalette palette) {
        playerPalette = new SpigotPaletteOptions(palette, new SpigotConsoleContext(false));
        consolePalette = new SpigotPaletteOptions(palette, new SpigotConsoleContext(true));
    }

    /**
     * Spigot Palette with Player context
     *
     * @since 1.0.0
     */
    @NotNull SpigotPaletteOptions playerPalette;

    /**
     * Spigot Palette with Console context
     *
     * @since 1.0.0
     */
    @NotNull SpigotPaletteOptions consolePalette;

    /**
     * The list of players receiving console output to their chat
     *
     * @since 1.0.0
     */
    @NotNull ArrayList<SpigotPlayer> devLoggers = new ArrayList<>();

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public ArrayList<SpigotPlayer> getDevLoggers() { return devLoggers; }

    /**
     * The engine this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOFoundationSpigot minecraft;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return minecraft; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int reproduce(@Nullable FriendlyFeedbackProvider ffp, @NotNull FriendlyFeedbackCategory... categories) {

        // Nothing happens when null
        if (ffp == null) { return CANCELLED_NULL_FFP; }

        // For every category
        for (FriendlyFeedbackCategory cat : categories) {

            // For every message
            for (FriendlyFeedbackMessage message : ffp.getFeedback(cat)) {

                // Simply print to console without options
                reproduce(message);
            }
        }

        // Successfully printed out all contents of this Friendly Feedback Provider
        return SUCCESS;
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public int reproduce(@Nullable FriendlyFeedbackMessage message) {

        // Nothing happens when null
        if (message == null) { return CANCELLED_NULL_FFP; }

        // Parse the message
        String parsed = message.getMessage(consolePalette);
        getMinecraft().getPlugin().getServer().getConsoleSender().sendMessage(parsed);

        // Send to players too
        if (getDevLoggers().size() > 0) {
            String players = message.getMessage(playerPalette);
            for (SpigotPlayer player : getDevLoggers()) { player.sendChatMessage(players); }
        }

        // Success
        return SUCCESS;
    }
}
