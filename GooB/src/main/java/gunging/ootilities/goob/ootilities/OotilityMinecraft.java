package gunging.ootilities.goob.ootilities;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.hooking.overriding.GOOBOverridingBus;
import gunging.ootilities.goob.engine.hooking.overriding.GOOBStaticOverrideableResult;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEntity;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMItemStack;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMVector3;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utility methods that deal with things you'd run into in
 * minecraft, they deal with entities and items and so forth.
 * <br><br>
 * The static methods simply reference the OotilityMinecraft
 * instance running on the default GOOBEngine singleton.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class OotilityMinecraft implements GOOBFuel, GOOBMinecraftFuel {

    //region GooS Default Static
    /**
     * @return The default instance of this class being called.
     *
     * @since 1.0.0
     * @author Gunging
     */
    static @Nullable OotilityMinecraft defaultInstance() {
        return GungingOotilitiesBase.getEngine().getOotilityMinecraft();
    }
    //endregion

    //region Entity
    /**
     * SVF: Side-Vertical-Offset(-Level) relative coordinates, not to be confused with DAH (Dodge-Above-Heading[-Base])
     * or the absolute XYZ coords. The SVF coordinate system points in the direction of an entity, usually a player or
     * caster of an action. Sometimes, this action involves a second body with its own relative coordinates DAH, but the
     * math for those is identical to SVF just with different names to avoid confusion when combining SVF and DAH.
     *
     * @param who The entity whose facing to use as relative axes.
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
    @NotNull
    public static GBMVector3<?> EntityTransformSVF(@NotNull GBMEntity<?> who, double sOff, double vOff, double fOff, double xOff, double yOff, double zOff) {
        return EntityTransformSVFL(who, sOff, vOff, fOff, 0, xOff, yOff, zOff);
    }
    /**
     * @param who The entity whose facing to use as relative axes.
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
    @NotNull public static GBMVector3<?> EntityTransformSVFL(@NotNull GBMEntity<?> who, double sOff, double vOff, double fOff, double lOff, double xOff, double yOff, double zOff) {
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) { return OotilityVectors.TransformSVFL(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw()), sOff, vOff, fOff, lOff, xOff, yOff, zOff); }
        return defaultInstance.entityTransformSVFL(who, sOff, vOff, fOff, lOff, xOff, yOff, zOff);
    }
    /**
     * @param who The entity whose facing to use as relative axes.
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
    @NotNull public GBMVector3<?> entityTransformSVFL(@NotNull GBMEntity<?> who, double sOff, double vOff, double fOff, double lOff, double xOff, double yOff, double zOff) {
        return getEngine().getOotilityVectors().transformSVFL(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw()), sOff, vOff, fOff, lOff, xOff, yOff, zOff);
    }

    /**
     * @param who The entity in question
     *
     * @return The side unit vector, rectangular to the forward vector and having no component along the Y-axis
     *
     * @see #entityVertical(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> EntitySide(@NotNull GBMEntity<?> who) {
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) { return OotilityVectors.Side(0, Math.toRadians(who.getYaw())); }
        return defaultInstance.entitySide(who);
    }
    /**
     * @param who The entity in question
     *
     * @return The side unit vector, rectangular to the forward vector and having no component along the Y-axis
     *
     * @see #entityVertical(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> entitySide(@NotNull GBMEntity<?> who) {
        return getEngine().getOotilityVectors().side(0, Math.toRadians(who.getYaw()));
    }

    /**
     * @param who The entity in question
     *
     * @return The vertical unit vector, rectangular to the forward and side vectors
     *
     * @see #entitySide(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> EntityVertical(@NotNull GBMEntity<?> who) {
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) { return OotilityVectors.Vertical(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw())); }
        return defaultInstance.entityVertical(who);
    }
    /**
     * @param who The entity in question
     *
     * @return The vertical unit vector, rectangular to the forward and side vectors
     *
     * @see #entitySide(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> entityVertical(@NotNull GBMEntity<?> who) {
        return getEngine().getOotilityVectors().vertical(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw()));
    }

    /**
     * @param who The entity in question
     *
     * @return The forward vector, in the direction they entity is looking.
     *
     * @see #entitySide(GBMEntity)
     * @see #entityVertical(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> EntityForward(@NotNull GBMEntity<?> who) {
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) { return OotilityVectors.Forward(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw())); }
        return defaultInstance.entityVertical(who);
    }
    /**
     * @param who The entity in question
     *
     * @return The forward vector, in the direction they entity is looking.
     *
     * @see #entitySide(GBMEntity)
     * @see #entityVertical(GBMEntity)
     * @see #EntityTransformSVF(GBMEntity, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> entityForward(@NotNull GBMEntity<?> who) {
        return getEngine().getOotilityVectors().forward(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw()));
    }

    /**
     * @param who The entity in question
     *
     * @return The level unit vector, in the direction as the forward vector but no vertical component
     *
     * @see #entitySide(GBMEntity)
     * @see #entityVertical(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVFL(GBMEntity, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static GBMVector3<?> EntityLevel(@NotNull GBMEntity<?> who) {
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) { return OotilityVectors.Level(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw())); }
        return defaultInstance.entityVertical(who);
    }
    /**
     * @param who The entity in question
     *
     * @return The level unit vector, in the direction as the forward vector but no vertical component
     *
     * @see #entitySide(GBMEntity)
     * @see #entityVertical(GBMEntity)
     * @see #entityForward(GBMEntity)
     * @see #EntityTransformSVFL(GBMEntity, double, double, double, double, double, double, double)
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GBMVector3<?> entityLevel(@NotNull GBMEntity<?> who) {
        return getEngine().getOotilityVectors().level(Math.toRadians(who.getPitch()), Math.toRadians(who.getYaw()));
    }
    //endregion

    //region Item Stack
    /**
     * This method is intended for logging or displaying, something user-friendly.
     *
     * @param item The item to get the name from, prepended by its amount.
     *
     * @return The item's name, or its material if it has no name, or null if it is null.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String GetItemName(@Nullable GBMItemStack<?> item) { return GetItemName(item, true); }
    /**
     * This method is intended for logging or displaying, something user-friendly.
     *
     * @param item The item to get the name from
     * @param includeAmount If the amount should be prepended
     *
     * @return The item's name, or its material if it has no name, or null if it is null.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static String GetItemName(@Nullable GBMItemStack<?> item, boolean includeAmount) {

        // Null?
        if (item == null) { return "null"; }

        // Amount?
        String amountText = "";
        if (includeAmount) { amountText = item.getCount() + "x"; }

        // Use its display name, or its material name in a readable format
        String nameText = item.getDisplayName();
        if (nameText == null) { nameText = OotilityStrings.TitleCaseConversion(item.getMaterialName().replace(":", " ")); }

        // Return type
        return amountText + nameText;
    }

    /**
     * @return Renames this item, foolproofly
     *
     * @param item Item to rename
     * @param name Name to give this item
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public static GBMItemStack<?> SetItemName(@Nullable GBMItemStack<?> item, @Nullable String name) {
        if (item == null) { return null; }

        // When no default instance... we can at least delegate to the wrapper
        OotilityMinecraft defaultInstance = defaultInstance();
        if (defaultInstance == null) {
            item.setItemName(name);
            return item;
        }

        // Delegate to wrapper
        return defaultInstance.setItemName(item, name);
    }
    /**
     * @return Renames this item, foolproofly
     *
     * @param item Item to rename
     * @param name Name to give this item
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public GBMItemStack<?> setItemName(@Nullable GBMItemStack<?> item, @Nullable String name) {
        if (item == null) { return null; }

        // Prepare the result
        GOOBStaticOverrideableResult<GBMItemStack<?>> ret = new GOOBStaticOverrideableResult<>(item.clone());

        // Run through the override bus
        ret = setItemName.run(ret, new Object[]{ name });
        GBMItemStack<?> res = ret.getResult();

        // Check if it was finished or cancelled
        if (ret.isCancelled()) { return null; }
        if (ret.isFinished()) { return res; }

        // Done
        if (res == null) { return null; }
        res.setItemName(name);
        return res;
    }
    /**
     * Event-bus-like overrides for this method
     *
     * @since 1.0.0
     */
    @NotNull public final GOOBOverridingBus<GBMItemStack<?>> setItemName = new GOOBOverridingBus<>();
    //endregion

    //region GOOBFuel
    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public OotilityMinecraft(@NotNull GOOBMinecraft minecraft) { this.minecraft = minecraft; }

    /**
     * The engine this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBMinecraft minecraft;

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @Override public @NotNull GOOBEngine getEngine() { return minecraft.getEngine(); }

    /**
     * @since 1.0.0
     * @author Gunging
     */
    public @NotNull GOOBMinecraft getMinecraft() { return minecraft; }
    //endregion
}
