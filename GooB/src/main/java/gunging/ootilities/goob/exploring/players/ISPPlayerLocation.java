package gunging.ootilities.goob.exploring.players;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.EXPStatementBased;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import gunging.ootilities.goob.exploring.entities.ISEEntityLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * An actual slot in the inventory of a player,
 * ready to read or modify that item yes.
 *
 * @since 1.0.0
 * @author Gunging
 */
public class ISPPlayerLocation extends EXPStatementBased<GBMPlayer<?>> implements ItemStackLocation<GBMPlayer<?>> {

    /**
     * The player owner of this inventory slot
     *
     * @since 1.0.0
     */
    @NotNull final GBMPlayer<?> player;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMPlayer<?> getPlayer() { return player; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GBMPlayer<?> getHolder() { return getPlayer(); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull Class<?> getHolderClass() { return getStatement().getElaboratorWrapper().getWrappedClass(); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISPPlayerStatement getStatement() { return (ISPPlayerStatement) super.getStatement(); }

    /**
     * @param player The player owner of this inventory slot
     * @param slot The special inventory slot being targeted
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISPPlayerLocation(@NotNull GBMPlayer<?> player, @NotNull ISPPlayerStatement slot) {
        super(slot);
        this.player = player;
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public void setItemStack(@Nullable GBMItemStack<?> stack) { getStatement().writeItemStack(getPlayer(), stack); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @Nullable GBMItemStack<?> getItemStack() { return getStatement().readItemStack(getPlayer()); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public boolean equals(@Nullable Object obj) {

        // It has to be an ItemStack Location
        if (!(obj instanceof ItemStackLocation<?>)) { return false; }

        // I cast... manual breathing
        ItemStackLocation<?> other = (ItemStackLocation<?>) obj;

        // Check UUID of holder
        UUID ofHolder = null;
        if (obj instanceof ISEEntityLocation) {
            ofHolder = ((ISEEntityLocation) obj).getHolder().getUUID();
        } else if (obj instanceof ISPPlayerLocation) {
            ofHolder = ((ISPPlayerLocation) obj).getHolder().getUUID();
        }

        // No holder? No service
        if (ofHolder == null) { return false; }

        // Different holders? Not equal!
        if (!ofHolder.equals(getHolder().getUUID())) { return false; }

        // Any of the slots may be elaborated? Abstractly, not equals
        if (!other.getStatement().isFundamental()) { return false; }
        if (!getStatement().isFundamental()) { return false; }

        // Finally, must match slot
        return other.getStatement().equals(getStatement());
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override  public String toString() { return "|" + getHolder().getDebugName() + "|" + getStatement(); }
}
