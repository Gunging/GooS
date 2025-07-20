package gunging.ootilities.goob.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.exploring.EXPStatementBased;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import gunging.ootilities.goob.exploring.ItemStackExplorer;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * ItemStack Explorer meant to target an Entity.
 *
 * @since 1.0.0
 * @author Gunging
 */
public class ISEEntityExplorer extends EXPStatementBased<GBMEntity<?>> implements ItemStackExplorer<GBMEntity<?>> {

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISEEntityStatement getStatement() { return (ISEEntityStatement) super.getStatement(); }

    /**
     * Reference a special slot of the inventory, or family of slots
     *
     * @param slot The special code slot you are referencing
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISEEntityExplorer(@NotNull ISEEntityStatement slot) { super(slot);  }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public @NotNull ArrayList<? extends ItemStackExplorer<GBMEntity<?>>> elaborate(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        return getStatement().whenElaborated(elaborator);
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override
    public @Nullable ItemStackLocation<? extends GBMEntity<?>> realize(@NotNull ItemExplorerElaborator<? extends GBMEntity<?>> elaborator) {
        return getStatement().whenRealized(elaborator);
    }
}
