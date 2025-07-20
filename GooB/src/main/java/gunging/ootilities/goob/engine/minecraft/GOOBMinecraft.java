package gunging.ootilities.goob.engine.minecraft;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.wrapping.*;
import gunging.ootilities.goob.exploring.ExplorerManager;
import gunging.ootilities.goob.exploring.ItemExplorerStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The reference to all platform-dependent implementations
 * that are active for this environment, built during load.
 *
 * @author Gunging
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class GOOBMinecraft implements GOOBFuel {

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public GOOBMinecraft(@NotNull GOOBEngine engine) { this.engine = engine; }

    /**
     * The engine this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBEngine engine;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return engine; }

    //region Wrappers
    /**
     * @return The wrapper to interface with Item Stacks
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMItemStackWrapper<?,?> getItemStacks() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Item Stacks are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Namespaced Keys
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMNamespacedKeyWrapper<?,?> getNamespacedKeys() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Namespaced Keys are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Vectors
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMVector3Wrapper<?,?> getVector3s() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Vectors are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Entities
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMEntityWrapper<?,?> getEntities() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Entities are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Living Entities
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMLivingEntityWrapper<?,?> getLivingEntities() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Living Entities are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Players
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMPlayerWrapper<?,?> getPlayers() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Players are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Equipment Slots
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMEquipmentSlotWrapper<?,?> getEquipmentSlots() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Equipment Slots are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Inventory Containers
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMInventoryWrapper<?,?> getInventories() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Inventories are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Player Inventories
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMPlayerInventoryWrapper<?,?> getPlayerInventories() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Player Inventories are not supported on this platform. ");
        return null;
    }

    /**
     * @return The wrapper to interface with Inventory Views
     *
     * @author Gunging
     * @since 1.0.0
     */
    @Nullable public GBMInventoryViewWrapper<?,?> getInventoryViews() {
        getEngine().scrutinize(GOOBScrutiny.TESTING_MID, "Inventory Views are not supported on this platform. ");
        return null;
    }
    //endregion

    //region Item Explorer
    /**
     * The Item Explorer system running on this engine
     *
     * @since 1.0.0
     */
    @NotNull ExplorerManager itemExplorer = new ExplorerManager(this);

    /**
     * @author Gunging
     * @since 1.0.0
     */
    public @NotNull ExplorerManager getItemExplorer() { return itemExplorer; }

    /**
     * When someone sends an Explorer Statement to the {@link ExplorerManager#decodeStatement(String)}
     *
     * @author Gunging
     * @since 1.0.0
     */
    public @Nullable ItemExplorerStatement<?> getDefaultStatement() { return null; }
    //endregion
}
