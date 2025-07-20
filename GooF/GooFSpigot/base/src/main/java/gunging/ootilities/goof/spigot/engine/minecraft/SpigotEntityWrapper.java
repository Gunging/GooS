package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntityWrapper;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Entities into SpigotEntities
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotEntityWrapper extends GBMEntityWrapper<SpigotEntity, Entity> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotEntityWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotEntity wrap(@NotNull Entity entity) { return new SpigotEntity(this, entity); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<Entity> getWrappedClass() { return Entity.class; }
}
