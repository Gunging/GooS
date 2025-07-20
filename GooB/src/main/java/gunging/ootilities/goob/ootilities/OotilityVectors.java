package gunging.ootilities.goob.ootilities;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.minecraft.provided.ProvidedMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3Wrapper;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.provided.ProvidedVector3Wrapper;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class that handles 3D-Space math operations.
 * <br><br>
 * Considering it is pure java math, this always runs on the default GOOBEngine 
 * and does not even query it. This class is completely standalone and consists
 * of only static methods.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class OotilityVectors implements GOOBFuel {

    //region GooS Default Static
    /**
     * @return The default instance of this class being called.
     *
     * @since 1.0.0
     * @author Gunging
     */
    static @NotNull OotilityVectors defaultInstance() {
        return GungingOotilitiesBase.getEngine().getOotilityVectors();
    }

    /**
     * @return The default wrapper for the Vector3 class
     * 
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3Wrapper<?,?> defaultWrapper() {

        /*
         * Jesus, what a mess to guarantee that @NotNull annotation
         *
         * Basically, if the default engine does not support vectors, we want to return an empty (0, 0, 0) vector.
         * To do that we need to know what platform-dependent vector wrapper class runs in this minecraft version.
         *
         * But if the engine also does not support minecraft, I return a wrapper with a default vector wrapper
         * implementation contained entirely in GooB. Ultimately returning a vector of (0, 0, 0) anyway.
         */
        GOOBMinecraft minecraft = GungingOotilitiesBase.getEngine().getMinecraft();
        
        // No minecraft? Provided default
        if (minecraft == null) { return (new ProvidedMinecraft(defaultInstance().getEngine())).getVector3s(); }
        GBMVector3Wrapper<?,?> wrapper = minecraft.getVector3s();
        
        // No vectors? Provided default
        if (wrapper == null) { return new ProvidedVector3Wrapper(minecraft); }
        
        // Use the correct implementation
        return wrapper;
    }

    /**
     * @return The default wrapper for the Vector3 class
     * 
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3Wrapper<?,?> effectiveWrapper() {
        
        // No minecraft in my engine? Defaults to the default
        GOOBMinecraft minecraft = getEngine().getMinecraft();
        if (minecraft == null) { return defaultWrapper(); }
        
        // No wrapper in my foundation? Defaults to default
        GBMVector3Wrapper<?,?> wrapper = minecraft.getVector3s();
        if (wrapper == null) { return defaultWrapper(); }
        
        // Use the correct implementation
        return wrapper;
    }
    //endregion

    //region SVFL
    /**
     * SVF: Side-Vertical-Offset(-Level) relative coordinates, not to be confused with DAH (Dodge-Above-Heading[-Base])
     * or the absolute XYZ coords. The SVF coordinate system points in the direction of an entity, usually a player or
     * caster of an action. Sometimes, this action involves a second body with its own relative coordinates DAH, but the
     * math for those is identical to SVF just with different names to avoid confusion when combining SVF and DAH.
     *
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @param sOff Relative sideways direction offset
     * @param vOff Relative vertical direction offset
     * @param fOff Relative forward direction offset
     * @param xOff Absolute X-direction offset
     * @param yOff Absolute Y-direction offset
     * @param zOff Absolute Z-direction offset
     *
     * @return A vector (not normalized) representing this SVF transformation.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> TransformSVF(double pitch, double yaw, double sOff, double vOff, double fOff, double xOff, double yOff, double zOff) {
        return TransformSVFL(pitch, yaw, sOff, vOff, fOff, 0, xOff, yOff, zOff);
    }

    /**
     * SVF: Side-Vertical-Offset-Level relative coordinates, not to be confused with DAH (Dodge-Above-Heading-Base)
     * or the absolute XYZ coords. The SVFL coordinate system points in the direction of an entity, usually a player or
     * caster of an action. Sometimes, this action involves a second body with its own relative coordinates DAHB, but the
     * math for those is identical to SVF just with different names to avoid confusion when combining SVFL and DAHB.
     * <br><br>
     * As for the Level (or Base) vector, it is in the same direction as the forward one but with no vertical component.
     * Often useful because it just feels right for some things to move only in this direction.
     *
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @param sOff Relative sideways direction offset
     * @param vOff Relative vertical direction offset
     * @param fOff Relative forward direction offset
     * @param lOff Relative level direction offset, which is in the same direction as forward but no vertical component.
     * @param xOff Absolute X-direction offset
     * @param yOff Absolute Y-direction offset
     * @param zOff Absolute Z-direction offset
     *
     * @return A vector (not normalized) representing this SVF transformation.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> TransformSVFL(double pitch, double yaw, double sOff, double vOff, double lOff, double fOff, double xOff, double yOff, double zOff) {
        return defaultInstance().transformSVFL(pitch, yaw, sOff, vOff, lOff, fOff, xOff, yOff, zOff);
    }
    
    /**
     * SVF: Side-Vertical-Offset-Level relative coordinates, not to be confused with DAH (Dodge-Above-Heading-Base)
     * or the absolute XYZ coords. The SVFL coordinate system points in the direction of an entity, usually a player or
     * caster of an action. Sometimes, this action involves a second body with its own relative coordinates DAHB, but the
     * math for those is identical to SVF just with different names to avoid confusion when combining SVFL and DAHB.
     * <br><br>
     * As for the Level (or Base) vector, it is in the same direction as the forward one but with no vertical component.
     * Often useful because it just feels right for some things to move only in this direction.
     *
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @param sOff Relative sideways direction offset
     * @param vOff Relative vertical direction offset
     * @param fOff Relative forward direction offset
     * @param lOff Relative level direction offset, which is in the same direction as forward but no vertical component.
     * @param xOff Absolute X-direction offset
     * @param yOff Absolute Y-direction offset
     * @param zOff Absolute Z-direction offset
     *
     * @return A vector (not normalized) representing this SVF transformation.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @NotNull public GBMVector3<?> transformSVFL(double pitch, double yaw, double sOff, double vOff, double lOff, double fOff, double xOff, double yOff, double zOff) {

        // Polar angle Theta
        double tF = (0.5 * Math.PI) + pitch;
        double tV = pitch;
        //double tS = (0.5 * Math.PI);
        //double tL = (0.5 * Math.PI);

        // Azimuthal angle Phi
        double pF = -yaw;
        double pV = pF;
        double pS = pF + (0.5 * Math.PI);
        double pL = pF;

        // Calculate absolutes
        double x = xOff + (fOff * Math.sin(pF) * Math.sin(tF)) + (vOff * Math.sin(pV) * Math.sin(tV))  + (sOff * Math.sin(pS))   + (lOff * Math.sin(pL));
        double y = yOff + (fOff * Math.cos(tF))                + (vOff * Math.cos(tV));
        double z = zOff + (fOff * Math.cos(pF) * Math.sin(tF)) + (vOff * Math.cos(pV) * Math.sin(tV))  + (sOff * Math.cos(pS))   + (lOff * Math.cos(pL));

        //double x = xOff + (fOff * Math.sin(pF) * Math.sin(tF)) + (vOff * Math.sin(pV) * Math.sin(tV))  + (sOff * Math.sin(pS) * Math.sin(tS))   + (lOff * Math.sin(pL) * Math.sin(tL));
        //double y = yOff + (fOff * Math.cos(tF))                + (vOff * Math.cos(tV))                 + (sOff * Math.cos(tS))                  + (lOff * Math.cos(tL));
        //double z = zOff + (fOff * Math.cos(pF) * Math.sin(tF)) + (vOff * Math.cos(pV) * Math.sin(tV))  + (sOff * Math.cos(pS) * Math.sin(tS))   + (lOff * Math.cos(pL) * Math.sin(tL));

        // Bake vector
        return effectiveWrapper().wrap(x, y, z);
    }

    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians. Ignored.
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The side unit vector, rectangular to the forward vector and having no component along the Y-axis
     *
     * @see #forward(double, double)
     * @see #vertical(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> Side(double pitch, double yaw) {
        return defaultInstance().side(pitch, yaw);
    }
    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians. Ignored.
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The side unit vector, rectangular to the forward vector and having no component along the Y-axis
     *
     * @see #forward(double, double)
     * @see #vertical(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> side(double pitch, double yaw) {

        // Polar angle Theta
        //double tS = (0.5 * Math.PI);

        // Azimuthal angle Phi
        double pS = (0.5 * Math.PI) - yaw;

        // Bake vector
        return effectiveWrapper().wrap(Math.sin(pS), 0, Math.cos(pS));
        //return effectiveWrapper().wrap(Math.sin(pS) * Math.sin(tS), Math.cos(tS), Math.cos(pS) * Math.sin(tS));
    }

    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The vertical unit vector, rectangular to the forward and side vectors
     *
     * @see #forward(double, double)
     * @see #side(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> Vertical(double pitch, double yaw) {
        return defaultInstance().vertical(pitch, yaw);
    }
    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The vertical unit vector, rectangular to the forward and side vectors
     *
     * @see #forward(double, double)
     * @see #side(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @NotNull public GBMVector3<?> vertical(double pitch, double yaw) {

        // Polar angle Theta
        double tV = pitch;

        // Azimuthal angle Phi
        double pV = -yaw;

        // Bake vector
        return effectiveWrapper().wrap(Math.sin(pV) * Math.sin(tV), Math.cos(tV), Math.cos(pV) * Math.sin(tV));
    }

    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The forward unit vector, in the direction these angles are facing
     *
     * @see #vertical(double, double)
     * @see #side(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> Forward(double pitch, double yaw) {
        return defaultInstance().forward(pitch, yaw);
    }
    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The forward unit vector, in the direction these angles are facing
     *
     * @see #vertical(double, double)
     * @see #side(double, double)
     * @see #TransformSVF(double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> forward(double pitch, double yaw) {

        // Polar angle Theta
        double tF = (0.5 * Math.PI) + pitch;

        // Azimuthal angle Phi
        double pF = -yaw;

        // Bake vector
        return effectiveWrapper().wrap(Math.sin(pF) * Math.sin(tF), Math.cos(tF), Math.cos(pF) * Math.sin(tF));
    }

    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians. Ignored.
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The level unit vector, in the direction as the forward vector but no vertical component
     *
     * @see #forward(double, double)
     * @see #vertical(double, double)
     * @see #side(double, double)
     * @see #transformSVFL(double, double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> Level(double pitch, double yaw) {
        return defaultInstance().level(pitch, yaw);
    }
    /**
     * @param pitch The rotation along the X-axis, in the direction from the Y-axis to the Z-axis, in radians. Ignored.
     * @param yaw The rotation along the Y-axis, in the direction from the Z-axis to the X-axis, in radians
     *
     * @return The level unit vector, in the direction as the forward vector but no vertical component
     *
     * @see #forward(double, double)
     * @see #vertical(double, double)
     * @see #side(double, double)
     * @see #transformSVFL(double, double, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> level(double pitch, double yaw) {

        // Polar angle Theta
        //double tL = (0.5 * Math.PI);

        // Azimuthal angle Phi
        double pL = -yaw;

        // Bake vector
        return effectiveWrapper().wrap(Math.sin(pL), 0, Math.cos(pL));
        //return effectiveWrapper().wrap(Math.sin(pL) * Math.sin(tL), Math.cos(tL), Math.cos(pL) * Math.sin(tL));
    }
    //endregion

    /**
     * @return An empty vector, (0, 0, 0), wrapped, according to the default static GOOBEngine singleton.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> Zero() { return defaultWrapper().zero(); }

    /**
     * @param origin The first point
     * @param target The second point
     * @param range The acceptable distance from the origin
     *
     * @return If the target point is in range of the origin
     *
     * @since 1.0.0
     * @author Gunging
     */
    public static boolean inRange(@NotNull GBMVector3<?> origin, @NotNull GBMVector3<?> target, double range) {

        // Silly math
        double dx = (target.getX() - origin.getX());
        double dy = (target.getY() - origin.getY());
        double dz = (target.getZ() - origin.getZ());
        double D = dx * dx + dy * dy + dz * dz;

        // The range must exceed or equal the distance
        return (range * range) >= D;
    }

    //region GOOBFuel
    /**
     * @param engine The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public OotilityVectors(@NotNull GOOBEngine engine) { this.engine = engine; }

    /**
     * The engine this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBEngine engine;

    
    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBEngine getEngine() { return engine; }
    //endregion
}
