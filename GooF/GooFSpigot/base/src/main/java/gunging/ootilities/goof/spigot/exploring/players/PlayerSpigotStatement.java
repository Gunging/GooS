package gunging.ootilities.goof.spigot.exploring.players;

import org.jetbrains.annotations.NotNull;

/**
 * An interface to identify Item Explorer Statements for Bukkit Spigot
 *
 * @since 1.0.0
 * @author Gunging
 */
public interface PlayerSpigotStatement {

    /**
     * @return A reference to the engines' spigot statements class
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull ISPSpigotStatements getStatements();
}
