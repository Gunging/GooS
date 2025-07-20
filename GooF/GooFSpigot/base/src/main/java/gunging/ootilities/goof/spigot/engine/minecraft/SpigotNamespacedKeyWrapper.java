package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKeyWrapper;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps NamespacedKeys into SpigotNamespacedKeys
 *
 * @author Gunging
 * @since 1.0.0
 */
public class SpigotNamespacedKeyWrapper extends GBMNamespacedKeyWrapper<SpigotNamespacedKey, NamespacedKey> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotNamespacedKeyWrapper(@NotNull GOOBMinecraft minecraft) {
        super(minecraft);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     *
     * @exception IllegalArgumentException When the namespace or key do not fit Minecraft's pattern.
     */
    @Override
    public @NotNull SpigotNamespacedKey fromNamespaceAndPath(@NotNull String namespace, @NotNull String path) throws IllegalArgumentException {
        //noinspection deprecation
        return wrap(new NamespacedKey(namespace, path));
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull SpigotNamespacedKey wrap(@NotNull NamespacedKey namespacedKey) {
        return new SpigotNamespacedKey(this, namespacedKey);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<NamespacedKey> getWrappedClass() { return NamespacedKey.class; }
}
