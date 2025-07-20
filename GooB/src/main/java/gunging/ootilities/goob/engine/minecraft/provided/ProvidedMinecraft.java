package gunging.ootilities.goob.engine.minecraft.provided;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3Wrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An empty implementation of minecraft for things that
 * can be provided by GOOB if the platform doesn't have
 * them, such as Vectors.
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ProvidedMinecraft extends GOOBMinecraft {

    /**
     * @param engine The engine this is defaulting on
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ProvidedMinecraft(@NotNull GOOBEngine engine) {
        super(engine);
        this.vector3s = new ProvidedVector3Wrapper(this);
    }

    /**
     * @since 1.0.0
     */
    @NotNull ProvidedVector3Wrapper vector3s;


    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GBMVector3Wrapper<?,?> getVector3s() { return vector3s; }
}
