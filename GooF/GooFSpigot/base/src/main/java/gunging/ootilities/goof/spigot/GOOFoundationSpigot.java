package gunging.ootilities.goof.spigot;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.hooking.GOOBDisableShutdown;
import gunging.ootilities.goob.engine.hooking.GOOBDuallyInitializable;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.provided.ProvidedVector3Wrapper;
import gunging.ootilities.goob.exploring.ItemExplorerStatement;
import gunging.ootilities.goob.ootilities.friendly.provided.GooBPalette;
import gunging.ootilities.goof.spigot.engine.minecraft.*;
import gunging.ootilities.goof.spigot.exploring.entities.ISESpigotStatements;
import gunging.ootilities.goof.spigot.exploring.players.ISPSpigotStatements;
import gunging.ootilities.goof.spigot.ootilities.friendly.SpigotConsoleReproducer;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;

/**
 * The foundation is the means of actually interacting with the
 * minecraft world, the platform this is running on essentially. <br>
 * In here we define the capabilities of the platform.
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GOOFoundationSpigot extends GOOBMinecraft implements GOOBDuallyInitializable<Server>, GOOBDisableShutdown<Server> {

    /**
     * @param engine The engine this foundation is part of.
     * @param server The Minecraft Server where this foundation is running on.
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GOOFoundationSpigot(@NotNull GOOBEngine engine, @NotNull Server server) {
        super(engine);
        this.server = server;
    }

    /**
     * The Minecraft Server where this foundation is running
     *
     * @since 1.0.0
     */
    @NotNull Server server;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull Server getServer() { return server; }

    //region Wrappers
    /**
     * @since 1.0.0
     */
    @NotNull SpigotItemStackWrapper itemStacks = new SpigotItemStackWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotItemStackWrapper getItemStacks() { return itemStacks; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotNamespacedKeyWrapper namespacedKeys = new SpigotNamespacedKeyWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotNamespacedKeyWrapper getNamespacedKeys() { return namespacedKeys; }

    /**
     * @since 1.0.0
     */
    @NotNull ProvidedVector3Wrapper vector3s = new ProvidedVector3Wrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ProvidedVector3Wrapper getVector3s() { return vector3s; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotEntityWrapper entities = new SpigotEntityWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotEntityWrapper getEntities() { return entities; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotLivingEntityWrapper livingEntities = new SpigotLivingEntityWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotLivingEntityWrapper getLivingEntities() { return livingEntities; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotEquipmentSlotWrapper equipmentSlots = new SpigotEquipmentSlotWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotEquipmentSlotWrapper getEquipmentSlots() { return equipmentSlots; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotPlayerWrapper players = new SpigotPlayerWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotPlayerWrapper getPlayers() { return players; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotPlayerInventoryWrapper playerInventories = new SpigotPlayerInventoryWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotPlayerInventoryWrapper getPlayerInventories() { return playerInventories; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotInventoryWrapper inventories = new SpigotInventoryWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotInventoryWrapper getInventories() { return inventories; }

    /**
     * @since 1.0.0
     */
    @NotNull SpigotInventoryViewWrapper inventoryViews = new SpigotInventoryViewWrapper(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotInventoryViewWrapper getInventoryViews() { return inventoryViews; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull ItemExplorerStatement<?> getDefaultStatement() { return getPlayerStatements().STANDARD; }
    //endregion

    //region Item Explorer
    /**
     * The Item Explorer statements for players
     *
     * @since 1.0.0
     */
    @NotNull final ISPSpigotStatements playerStatements = new ISPSpigotStatements(this, namespacedKeys);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull ISPSpigotStatements getPlayerStatements() { return playerStatements; }

    /**
     * The Item Explorer statements for players
     *
     * @since 1.0.0
     */
    @NotNull final ISESpigotStatements entityStatements = new ISESpigotStatements(this, namespacedKeys);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull ISESpigotStatements getEntityStatements() { return entityStatements; }
    //endregion

    //region Friendliness
    /**
     * The ability to print console messages through the Spigot Server console, and not the raw java console.
     *
     * @since 1.0.0
     */
    @NotNull SpigotConsoleReproducer spigotReproducer = new SpigotConsoleReproducer(this, new GooBPalette());

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public SpigotConsoleReproducer getSpigotReproducer() { return spigotReproducer; }
    //endregion
}
