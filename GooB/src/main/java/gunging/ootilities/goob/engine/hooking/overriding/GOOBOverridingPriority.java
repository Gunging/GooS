package gunging.ootilities.goob.engine.hooking.overriding;

/**
 * Average event bus experience with multiple event priority. These
 * are the priority levels where LOWEST runs first, HIGHEST runs last,
 * and MONITOR always runs at the end (even when the event is finalized
 * early) though, by convention, MONITOR is read-only!
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public enum GOOBOverridingPriority {

    /**
     * Runs first before anything else. Note that if
     * a prior overrideable to yours marks the event
     * as finalized, your overrideable won't get to run.
     *
     * @since 1.0.0
     */
    LOWEST,

    /**
     * Runs second, right after {@link #LOWEST}. Note that if
     * a prior overrideable to yours marks the event
     * as finalized, your overrideable won't get to run.
     *
     * @since 1.0.0
     */
    LOW,


    /**
     * Runs third, right after {@link #LOW}. Note that if
     * a prior overrideable to yours marks the event
     * as finalized, your overrideable won't get to run.
     *
     * @since 1.0.0
     */
    NORMAL,

    /**
     * Runs fourth, right after {@link #NORMAL}. Note that if
     * a prior overrideable to yours marks the event
     * as finalized, your overrideable won't get to run.
     *
     * @since 1.0.0
     */
    HIGH,


    /**
     * Runs fifth, right after {@link #HIGH}. Note that if
     * a prior overrideable to yours marks the event
     * as finalized, your overrideable won't get to run.
     *
     * @since 1.0.0
     */
    HIGHEST,


    /**
     * Runs last, right after {@link #HIGHEST} or after the
     * overridable is marked as finalized. By convention, no
     * edition must be done here and only reading.
     *
     * @since 1.0.0
     */
    MONITOR,
}
