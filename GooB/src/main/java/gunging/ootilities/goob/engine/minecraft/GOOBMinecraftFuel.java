package gunging.ootilities.goob.engine.minecraft;

import org.jetbrains.annotations.NotNull;

/**
 * For GooS Operations that run within GOOBMinecraft,
 * keeps a reference to their non-null parent which is
 * pretty useful.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface GOOBMinecraftFuel {

    /**
     * @return The GooB Engine this operation is running on
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull GOOBMinecraft getMinecraft();
}
