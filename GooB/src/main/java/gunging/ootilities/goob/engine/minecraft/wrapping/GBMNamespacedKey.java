package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBWrap;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapBase;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * Something with the functionality of Minecraft's Namespaced Keys
 * or Resource Locations (depending on the platform you are in)
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMNamespacedKey<E> extends GOOBWrapBase<E> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMNamespacedKey(@NotNull GOOBWrapperBase<? extends GOOBWrap<E>, E> wrapper, @NotNull E wrap) {
        super(wrapper, wrap);
    }

    /**
     * @return The key of this Namespaced Key
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract String getPath();

    /**
     * @return The namespace of this Namespaced Key
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull public abstract String getNamespace();
}
