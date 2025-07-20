package gunging.ootilities.goob.exploring;

import org.jetbrains.annotations.NotNull;

/**
 * This is the physical existing thing that we will be exploring, it represents the actual
 * instance of something in the world that we are about to explore.
 * <p></p>
 * For example, if you sought the "ec20" 20th slot of an enderchest, you must provide a
 * Player object as the elaborator in order to access that ender chest.
 * <br><br>
 * As for why it is called the "Elaborator," well, suppose you received a statement "ec5-10"
 * that encodes for 6 different slots, then elaborating it means separating it into six
 * different statements. The function to do this takes an elaborator as an argument, which
 * is where this class gets its name.
 *
 * @see ItemExplorerStatement
 *
 * @param <Elaboratee> The class that this provides for statements to be resolved and elaborated.
 *
 * @author Gunging
 * @since 1.0.0
 */
public interface ItemExplorerElaborator<Elaboratee> {

    /**
     * @return The holder or container that will be used to
     *         resolve Item Stack exploration.
     *
     * @author Gunging
     * @since 1.0.0
     */
    @NotNull Elaboratee getElaboratee();
}
