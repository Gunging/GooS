package gunging.ootilities.goob.exploring.players;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import org.jetbrains.annotations.NotNull;

/**
 * The most fundamental ItemStack slot elaborator is one that provides
 * a player. Most applications deal with the player inventory, after all,
 * then a player is required to fully realize these slots.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISPPlayerElaborator implements ItemExplorerElaborator<GBMPlayer<?>> {

    /**
     * The player provided to elaborate the ItemStack slot
     *
     * @since 1.0.0
     */
    @NotNull GBMPlayer<?> player;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public GBMPlayer<?> getPlayer() { return player; }

    /**
     * @param who Player provided to elaborate the ItemStack slot
     * @author Gunging
     * @since 1.0.0
     */
    public ISPPlayerElaborator(@NotNull GBMPlayer<?> who) { player = who; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GBMPlayer<?> getElaboratee() {return getPlayer(); }
}