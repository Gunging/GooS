package gunging.ootilities.goob.engine.minecraft.provided;

/**
 * Simplest ever Vector 3 class with no utility methods
 *
 * @author Gunging
 * @since 1.0.0
 */
public class PV3 {

    /**
     * @param i The i component
     * @param j The j component
     * @param k The k component
     *
     * @author Gunging
     * @since 1.0.0
     */
    public PV3(double i, double j, double k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /**
     * @since 1.0.0
     */
    public double i;
    /**
     * @since 1.0.0
     */
    public double j;
    /**
     * @since 1.0.0
     */
    public double k;
}
