package gunging.ootilities.goob.engine.hooking.overriding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * A literal event bus for every method that needs a mini event bus for itself.
 *
 * @param <Return> The return type of this operation
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class GOOBOverridingBus<Return> {

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> lowest = new ArrayList<>();

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> low = new ArrayList<>();

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> normal = new ArrayList<>();

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> high = new ArrayList<>();

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> highest = new ArrayList<>();

    /**
     * @see GOOBOverridingPriority
     *
     * @since 1.0.0
     */
    ArrayList<GOOBStaticOverrideable<Return>> monitor = new ArrayList<>();

    /**
     * @param process The operation to perform
     *
     * @since 1.0.0
     * @author Gunging
     */
    public void put(@NotNull GOOBStaticOverrideable<Return> process) {
        put(process, GOOBOverridingPriority.NORMAL);
    }
    /**
     * @param process The operation to perform
     * @param priority The priority by which to perform it
     *
     * @since 1.0.0
     * @author Gunging
     */
    public void put(@NotNull GOOBStaticOverrideable<Return> process, @NotNull GOOBOverridingPriority priority) {
        switch (priority) {
            case LOWEST: lowest.add(process); break;
            case LOW: low.add(process); break;
            case HIGH: high.add(process); break;
            case HIGHEST: highest.add(process); break;
            case MONITOR: monitor.add(process); break;

            case NORMAL:
            default: normal.add(process); break;
        }
    }

    /**
     * @param origin The object being processed
     * @param args The arguments by which to process it
     *
     * @return The processed result.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public GOOBStaticOverrideableResult<Return> run(@NotNull GOOBStaticOverrideableResult<Return> origin, @Nullable Object[] args) {
        GOOBStaticOverrideableResult<Return> ret = origin;

        // Run by priority, if it finished run monitor
        if (lowest.size() > 0) {
            ret = runPriority(lowest, ret, args, false);
            if (ret.isFinished()) {
                ret = runPriority(monitor, ret, args, true);
                return ret; } }
        if (low.size() > 0) {
            ret = runPriority(low, ret, args, false);
            if (ret.isFinished()) {
                ret = runPriority(monitor, ret, args, true);
                return ret; } }
        if (normal.size() > 0) {
            ret = runPriority(normal, ret, args, false);
            if (ret.isFinished()) {
                ret = runPriority(monitor, ret, args, true);
                return ret; } }
        if (high.size() > 0) {
            ret = runPriority(high, ret, args, false);
            if (ret.isFinished()) {
                ret = runPriority(monitor, ret, args, true);
                return ret; } }
        if (highest.size() > 0) {
            ret = runPriority(highest, ret, args, false); }
        ret = runPriority(monitor, ret, args, true);
        return ret;
    }

    /**
     * @param origin The object being processed
     * @param args The arguments by which to process it
     *
     * @return The processed result.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull private GOOBStaticOverrideableResult<Return> runPriority(@NotNull ArrayList<GOOBStaticOverrideable<Return>> priority, @NotNull GOOBStaticOverrideableResult<Return> origin, @Nullable Object[] args, boolean asMonitor) {
        GOOBStaticOverrideableResult<Return> ret = origin;

        // Process by each process in the list
        for (GOOBStaticOverrideable<Return> process : priority) {
            ret = process.process(ret, args);

            // Early cancel when finished
            if (ret.isFinished() && !asMonitor) { return ret; }
        }

        // Done
        return ret;
    }
}
