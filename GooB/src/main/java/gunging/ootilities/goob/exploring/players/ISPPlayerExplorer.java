package gunging.ootilities.goob.exploring.players;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMPlayer;
import gunging.ootilities.goob.exploring.EXPStatementBased;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * ItemStack explorer meant to work with the player inventory.
 *
 * @since 1.0.0
 * @author Gunging
 */
public class ISPPlayerExplorer extends EXPStatementBased<GBMPlayer<?>> implements ItemStackExplorer<GBMPlayer<?>> {

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISPPlayerStatement getStatement() { return (ISPPlayerStatement) super.getStatement(); }

    /**
     * Reference a special slot of the inventory, or family of slots
     *
     * @param slot The special code slot you are referencing
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPPlayerExplorer(@NotNull ISPPlayerStatement slot) { super(slot);  }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public @NotNull ArrayList<? extends ItemStackExplorer<GBMPlayer<?>>> elaborate(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        return getStatement().whenElaborated(elaborator);
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public @Nullable ItemStackLocation<? extends GBMPlayer<?>> realize(@NotNull ItemExplorerElaborator<? extends GBMPlayer<?>> elaborator) {
        return getStatement().whenRealized(elaborator);
    }
}
