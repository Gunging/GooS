package gunging.ootilities.goob.engine.minecraft.provided;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3Wrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Vector;

/**
 * Wraps Java Vectors into Provided Vectors
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ProvidedVector3Wrapper extends GBMVector3Wrapper<ProvidedVector3, PV3> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ProvidedVector3Wrapper(@NotNull GOOBMinecraft minecraft) {
        super(minecraft);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull ProvidedVector3 wrap(double x, double y, double z) {
        return new ProvidedVector3(this, new PV3(x, y, z));
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull ProvidedVector3 wrap(@NotNull PV3 vector) { return new ProvidedVector3(this, vector); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public @NotNull ProvidedVector3 zero() { return wrap(0, 0, 0); }

    @Override
    public @NotNull Class<PV3> getWrappedClass() {
        return PV3.class;
    }
}
