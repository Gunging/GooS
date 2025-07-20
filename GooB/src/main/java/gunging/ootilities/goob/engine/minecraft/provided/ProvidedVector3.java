package gunging.ootilities.goob.engine.minecraft.provided;


import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3;
import org.jetbrains.annotations.NotNull;

/**
 * A provided wrapper for Vector 3 class
 *
 * @author Gunging
 * @since 1.0.0
 */
public class ProvidedVector3 extends GBMVector3<PV3> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ProvidedVector3(@NotNull ProvidedVector3Wrapper wrapper, @NotNull PV3 wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public double getX() { return unwrap().i; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public double getY() { return unwrap().j; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public double getZ() { return unwrap().k; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override
    public GBMVector3<PV3> normalize() {

        // Normalize
        double x = getX();
        double y = getY();
        double z = getZ();
        double len = Math.sqrt(x*x + y*y + z*z);

        // Wrap
        return (GBMVector3<PV3>) getWrapper().wrap(new PV3(x/len, y/len, z/len));
    }
}
