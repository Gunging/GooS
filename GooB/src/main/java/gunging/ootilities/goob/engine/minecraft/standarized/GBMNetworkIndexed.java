package gunging.ootilities.goob.engine.minecraft.standarized;

/**
 * Not used much for server-sided implementations, but
 * for both client and server sided stuff it is essential
 * for some things to be synced over the network.
 * <br><br>
 * For such cases, this network index is meant to sync
 * over the network to identify the representations of
 * the same object in both sides.
 *
 * @since 1.0.0
 * @author Gunging
 */
public interface GBMNetworkIndexed {

    /**
     * <b>Only touch if you know what you are doing.</b> Assigns an index so that
     * it can be synced over the network in the form of a single number. Furthermore,
     * recommended to simplify Hash and Equals by checking if this is identical.
     * <br>
     * Equals will most likely check other options though.
     *
     * @param n The network index to assign this object
     *
     * @since 1.0.0
     * @author Gunging
     */
    void setNetworkIndex(int n);

    /**
     * @return The network index of this object, not guaranteed to persist across sessions.
     *
     * @see #setNetworkIndex(int)
     *
     * @since 1.0.0
     * @author Gunging
     */
    int getNetworkIndex();
}
