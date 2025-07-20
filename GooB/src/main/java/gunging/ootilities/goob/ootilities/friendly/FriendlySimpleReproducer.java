package gunging.ootilities.goob.ootilities.friendly;

import org.jetbrains.annotations.Nullable;

/**
 * Meant as an extension to the {@link FriendlyReproducer} interface,
 * for reproducers that can display messages without worrying about
 * their context too much.
 * <br><br>
 * To be honest, reproducers that care about the full context are
 * rather marginal, I have a few in mind, but they are very specific
 * implementations.
 *
 * @author Gunging
 * @since 1.0.0
 */
@FunctionalInterface
public interface FriendlySimpleReproducer {

    /**
     * @param message Message to reproduce
     *
     * @return ZERO if reproduction was unsuccessful, any other code is
     *         implementation-dependant. POSITIVES are assumed to be a
     *         success, while NEGATIVES may be treated as more-specific
     *         failure codes.
     *
     * @author Gunging
     * @since 1.0.0
     */
    int reproduce(@Nullable FriendlyFeedbackMessage message);
}
