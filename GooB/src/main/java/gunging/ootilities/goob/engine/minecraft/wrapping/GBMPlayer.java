package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMCraftingSlots;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The fundamental functionality of Player, used by
 * GooB to perform its operations platform-independently.
 *
 * @param <E> The Player class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMPlayer<E> extends GBMLivingEntity<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMPlayer(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) { super(wrapper, wrap); }

    /**
     * @return The inventory of this player
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMPlayerInventory<?> getInventory();

    /**
     * @return The enderchest inventory of this player
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMInventory<?> getEnderchest();

    /**
     * @param item Item to put in the cursor of this inventory
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setCursor(@Nullable GBMItemStack<?> item);

    /**
     * @return The item in the cursor slot of this inventory
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract @Nullable GBMItemStack<?> getCursor();

    /**
     * @param slot The slot of the crafting slots to fetch
     *
     * @return The item in this slot, if it exists
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMItemStack<?> getCraftingItem(@NotNull GBMCraftingSlots slot) { return getCraftingItem(slot.ordinal()); }

    /**
     * @param slot The slot of the crafting slots
     * @param item The item to put in this slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public void setCraftingItem(@NotNull GBMCraftingSlots slot, @Nullable GBMItemStack<?> item) { setCraftingItem(slot.ordinal(), item); }

    /**
     * @param slot The slot of the crafting slots to fetch
     *
     * @return The item in this slot, if it exists
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract GBMItemStack<?> getCraftingItem(int slot);

    /**
     * @param slot The slot of the crafting slots
     * @param item The item to put in this slot.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void setCraftingItem(int slot, @Nullable GBMItemStack<?> item);

    /**
     * @return The inventory this player is currently seeing, if the GUI was open
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public abstract GBMInventoryView<?> getOpenedInventory();

    /**
     * Sends a chat message to this player, when called server-side as a packet,
     * and when called client-side simply shows it in the local chat console with
     * no regard to network connectivity.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public abstract void sendChatMessage(@NotNull String message);
}
