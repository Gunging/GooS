package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayerWrapper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Players into SpigotPlayers
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotPlayerWrapper extends GBMPlayerWrapper<SpigotPlayer, Player> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotPlayerWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotPlayer wrap(@NotNull Player player) { return new SpigotPlayer(this, player); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<Player> getWrappedClass() { return Player.class; }
}
