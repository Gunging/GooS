package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMLivingEntityWrapper;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps Entities into SpigotLivingEntities
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotLivingEntityWrapper extends GBMLivingEntityWrapper<SpigotLivingEntity, LivingEntity> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotLivingEntityWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotLivingEntity wrap(@NotNull LivingEntity entity) { return new SpigotLivingEntity(this, entity); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<LivingEntity> getWrappedClass() { return LivingEntity.class; }
}
