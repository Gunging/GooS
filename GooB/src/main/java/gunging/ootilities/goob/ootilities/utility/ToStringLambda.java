package gunging.ootilities.goob.ootilities.utility;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Ever wished to write your own toString method on the spot?
 * <br>
 * Well now you can with this handy transcriber.
 * <p></p>
 * Check this example out:
 * <p></p>
 * <code>
 * ArrayList itemNames = OotilityStrings.TranscribeList(itemsList, (o)->OotilityMinecraft.GetItemName( ((ItemStack)o) ));
 * </code>
 *
 * @see gunging.ootilities.goob.ootilities.OotilityStrings#TranscribeList(List, ToStringLambda)
 *
 * @since 1.0.0
 * @author Gunging
 */
@FunctionalInterface
public interface ToStringLambda {
    /**
     * It's basically an external toString() method
     * that you get to choose :)
     *
     * @param o Object you'd like to rewrite
     *
     * @return A string representation of it.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull String rewrite(@Nullable Object o);
}
