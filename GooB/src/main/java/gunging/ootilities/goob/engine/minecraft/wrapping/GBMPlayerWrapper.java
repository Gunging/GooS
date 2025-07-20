package gunging.ootilities.goob.engine.minecraft.wrapping;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBWrapperBase;
import org.jetbrains.annotations.NotNull;

/**
 * The platform-dependent interface for Players.
 *
 * @param <GooF> The wrapper class for Players
 * @param <E> The Player class of this platform
 *
 * @author Gunging
 * @since 1.0.0
 */
public abstract class GBMPlayerWrapper<GooF extends GBMPlayer<E>, E> extends GOOBWrapperBase<GooF, E> {

    /**
     * @param minecraft The minecraft engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public GBMPlayerWrapper(@NotNull GOOBMinecraft minecraft) {
        super(minecraft);
    }
}