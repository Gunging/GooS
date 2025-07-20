package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Namespaced Keys
 *
 * @param <GooF> The wrapper class for Namespaced Keys
 * @param <E> The Namespaced Key class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMNamespacedKeyWrapper<GooF extends GBMNamespacedKey<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMNamespacedKeyWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @param namespace The namespace of this key
     * @param path The key or path of this key
     *
     * @return A wrapped namespaced key
     *
     * @exception RuntimeException When the namespace or key do not fit Minecraft's pattern.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract GBMNamespacedKey<E> fromNamespaceAndPath(@NotNull String namespace, @NotNull String path) throws RuntimeException;
}
