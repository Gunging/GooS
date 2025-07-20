package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Wrapper for the Spigot Entity
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotEntity extends GBMEntity<Entity> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotEntity(@NotNull SpigotEntityWrapper wrapper, @NotNull Entity wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }

    //region Entity
    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public double getPitch() { return unwrap().getLocation().getPitch(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public double getYaw() { return unwrap().getLocation().getYaw(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull UUID getUUID() { return unwrap().getUniqueId(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getDebugName() { return unwrap().getName(); }
    //endregion
}
