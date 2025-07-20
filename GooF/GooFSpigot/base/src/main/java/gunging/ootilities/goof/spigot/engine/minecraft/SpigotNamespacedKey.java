package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Wrapper for the Spigot NamespacedKey
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotNamespacedKey extends GBMNamespacedKey<NamespacedKey> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotNamespacedKey(@NotNull SpigotNamespacedKeyWrapper wrapper, @NotNull NamespacedKey wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getPath() { return unwrap().getKey(); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull String getNamespace() { return unwrap().getNamespace(); }
}
