package gunging.ootilities.goob.exploring.entities;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.exploring.ItemExplorerElaborator;
import org.jetbrains.annotations.NotNull;

/**
 * An Item Stack Elaborator that provides an entity. It might
 * be a player, but not necessarily. This will still work for
 * players, but it may only really access their Armor, Mainhand,
 * and Offhand items.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ISEEntityElaborator implements ItemExplorerElaborator<GBMEntity<?>> {

    /**
     * The player provided to elaborate the Item Explorer Statement
     *
     * @since 1.0.0
     */
    @NotNull GBMEntity<?> entity;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public GBMEntity<?> getEntity() { return entity; }

    /**
     * @param who Entity provided to elaborate the Item Explorer Statement
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISEEntityElaborator(@NotNull GBMEntity<?> who) { entity = who; }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public @NotNull GBMEntity<?> getElaboratee() { return getEntity(); }
}