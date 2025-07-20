package gunging.ootilities.goob.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.exploring.EXPStatementBased;
import gunging.ootilities.goob.exploring.ItemStackLocation;
import gunging.ootilities.goob.exploring.players.ISPPlayerLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * An actual slot in the inventory of an entity,
 * ready to read or modify that item yes.
 *
 * @since 1.0.0
 * @author Gunging
 */
public class ISEEntityLocation extends EXPStatementBased<GBMEntity<?>> implements ItemStackLocation<GBMEntity<?>> {

    /**
     * The entity owner of this inventory slot
     *
     * @since 1.0.0
     */
    @NotNull final GBMEntity<?> entity;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMEntity<?> getEntity() { return entity; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GBMEntity<?> getHolder() { return entity; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull Class<?> getHolderClass() { return getStatement().getElaboratorWrapper().getWrappedClass(); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override @NotNull public ISEEntityStatement getStatement() { return (ISEEntityStatement) super.getStatement(); }

    /**
     * @param entity The entity owner of this inventory slot
     * @param slot The statement that resulted in this slot, just to have its definition available
     *
     * @since 1.0.0
     * @author Gunging
     */
    public ISEEntityLocation(@NotNull GBMEntity<?> entity, @NotNull ISEEntityStatement slot) {
        super(slot);
        this.entity = entity;
    }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public void setItemStack(@Nullable GBMItemStack<?> stack) { getStatement().writeItemStack(getEntity(), stack); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @Nullable GBMItemStack<?> getItemStack() { return getStatement().readItemStack(getEntity()); }

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
