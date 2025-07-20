package gunging.ootilities.goob.exploring;

import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMNetworkIndexed;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKey;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMNamespacedKeyWrapper;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * The manager class where Item Stack Statements are registered
 * to so they are accessible from anywhere.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class ExplorerManager implements GOOBFuel, GOOBMinecraftFuel {

    //region GOOBFuel
    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ExplorerManager(@NotNull GOOBMinecraft minecraft) { this.minecraft = minecraft; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }

    /**
     * The minecraft platform this is running in
     *
     * @since 1.0.0
     */
    @NotNull final GOOBMinecraft minecraft;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override @NotNull public GOOBMinecraft getMinecraft() { return minecraft; }
    //endregion

    /**
     * The list of registered explorer statements, indexed by namespace
     *
     * @since 1.0.0
     */
    @NotNull final HashMap<String, HashMap<String, ItemExplorerStatement<?>>> statementsByNamespace = new HashMap<>();

    /**
     * An index by integer numbers, not guaranteed to persist between sessions since
     * it is assigned at runtime, synced between server and clients when needed. This
     * allows to send statements as packets not by their Resource Location but by this
     * one index.
     *
     * @since 1.0.0
     */
    @NotNull final HashMap<Integer, ItemExplorerStatement<?>> statementsByNetwork = new HashMap<>();

    /**
     * The list of registered explorer statements
     *
     * @since 1.0.0
     */
    @NotNull final HashMap<GBMNamespacedKey<?>, ItemExplorerStatement<?>> registeredStatements = new HashMap<>();

    /**
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public HashMap<GBMNamespacedKey<?>, ItemExplorerStatement<?>> getRegisteredStatements() { return registeredStatements; }

    /**
     * Clears the registry of statements.
     * <br>
     * <i>Beware of old instances containing outdated network indices. </i>
     *
     * @since 1.0.0
     * @author Gunging
     */
    public void clearRegisteredStatements() {
        registeredStatements.clear();
        statementsByNamespace.clear();
        statementsByNetwork.clear();
    }

    /**
     * @param n The Network Index of this statement
     * @return The statement associated with this network index, if it exists
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable public ItemExplorerStatement<?> getByNetwork(int n) {
        return statementsByNetwork.get(n);
    }

    /**
     * @return The namespaced key wrapper to be used.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Nullable GBMNamespacedKeyWrapper<?,?> getKeys() { return getMinecraft().getNamespacedKeys(); }

    /**
     * @param statement The statement object itself
     * @param aliases The aliases this statement may be addressed by
     *
     * @return If this statement was successfully registered
     *
     * @since 1.0.0
     * @author Gunging
     */
    public boolean registerStatement(@NotNull ItemExplorerStatement<?> statement, boolean isServer, @NotNull String... aliases) {
        boolean registered = false;
        GBMNamespacedKeyWrapper<?,?> keys = getKeys();
        if (keys == null) { return false; }

        // Try registering its internal name, straight-up
        if (!registeredStatements.containsKey(statement.getStatementName())) {
            registeredStatements.put(statement.getStatementName(), statement);
            HashMap<String, ItemExplorerStatement<?>> byNamespace = statementsByNamespace.computeIfAbsent(statement.getStatementName().getNamespace(), k -> new HashMap<>());
            byNamespace.put(statement.getStatementName().getPath(), statement);
            registered = true;
        }

        // Try registering every alias
        for (String alias : aliases) {

            // Create a new resource key to register it by
            GBMNamespacedKey<?> aliasKey;
            try { aliasKey = keys.fromNamespaceAndPath(statement.getStatementName().getNamespace(), alias); } catch (RuntimeException ignored) { continue; }

            // Attempt to register it
            if (!registeredStatements.containsKey(aliasKey)) {
                registeredStatements.put(aliasKey, statement);
                HashMap<String, ItemExplorerStatement<?>> byNamespace = statementsByNamespace.computeIfAbsent(aliasKey.getNamespace(), k -> new HashMap<>());
                byNamespace.put(aliasKey.getPath(), statement);
                registered = true;
            }
        }

        // Assign network index, but only on the server-side
        if (registered && isServer) {
            int networkIndex = statementsByNetwork.size() + 1;
            if (statement instanceof GBMNetworkIndexed) { ((GBMNetworkIndexed) statement).setNetworkIndex(networkIndex); }
            statementsByNetwork.put(networkIndex, statement);
        }

        // Success if any alias succeeded
        return registered;
    }

    /**
     * @param namespacedKey The provided namespace:key identifier
     *
     * @return The Item Explorer Statement associated with this identifier
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null -> null")
    @Nullable public ItemExplorerStatement<?> findStatement(@Nullable GBMNamespacedKey<?> namespacedKey) {
        if (namespacedKey == null) { return  null; }
        return registeredStatements.get(namespacedKey);
    }

    /**
     * @param name The key identifier provided, using the default namespace {@link GOOBEngine#getDefaultNamespace()}
     *
     * @return The Item Explorer Statement associated with this identifier
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null -> null")
    @Nullable public ItemExplorerStatement<?> findStatement(@Nullable String name) {
        if (name == null) { return  null; }
        return findStatement(getEngine().getDefaultNamespace(), name);
    }

    /**
     * @param namespace The provided namespace
     * @param name The provided key identifier
     *
     * @return The Item Explorer Statement associated with this identifier
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null,_ -> null;_,null -> null")
    @Nullable public ItemExplorerStatement<?> findStatement(@Nullable String namespace, @Nullable String name) {
        if (name == null || namespace == null) { return  null; }

        // Build the Resource Location
        GBMNamespacedKey<?> namespacedKey = null;
        GBMNamespacedKeyWrapper<?,?> keys = getKeys();
        if (keys == null) { return null; }

        // Try to parse the namespaced key, either by a separator or by combining namespace and name
        try {
            int colonIndex = name.indexOf(':');
            if (colonIndex > 0) { namespacedKey = keys.fromNamespaceAndPath(name.substring(0, colonIndex), name.substring(0, colonIndex+1)); }
            if (namespacedKey == null) { namespacedKey = keys.fromNamespaceAndPath(namespace, name); }
        } catch (RuntimeException ignored) { return null; }

        // Well, try to find that namespaced key statement
        return findStatement(namespacedKey);
    }

    /**
     * The main function of this method is to decode which part of
     * the "keyOptions" argument is the path of the statement, and
     * what part of the statement is its options.
     *
     * @param namespace The namespace to be used
     * @param keyOptions The name of the statement and its options
     *
     * @return A built explorer statement if it could be decoded
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null,_ -> null;_,null -> null")
    @Nullable public ItemExplorerStatement<?> decodeStatement(@Nullable String namespace, @Nullable String keyOptions) {
        if (namespace == null || keyOptions == null) { return null; }

        /*
         * Honestly I can't think of a better way than a brute force attack.
         * This is because I don't want to make any assumption about the options
         */
        HashMap<String, ItemExplorerStatement<?>> byNamespace = statementsByNamespace.get(namespace);
        for (Map.Entry<String, ItemExplorerStatement<?>> pair : byNamespace.entrySet()) {
            String statement = pair.getKey();

            // Okay we found the one
            if (keyOptions.startsWith(statement)) {
                String options = keyOptions.substring(statement.length());

                // Yah gg
                return pair.getValue().withOptions(options);
            }
        }

        // None was found, so we use the default STANDARD
        ItemExplorerStatement<?> standard = getMinecraft().getDefaultStatement();
        if (standard == null) { return null; }
        return standard.withOptions(keyOptions);
    }

    /**
     * If there is no namespace, assumes the default namespace {@link GOOBEngine#getDefaultNamespace()}.
     * Some statements do not have options, and a popular form of options is a number range, some
     * examples:
     * <br>
     * <code>gungingoom:mainhand</code>
     * <br>
     * <code>gungingoom:enderchest3..18</code>
     * <br>
     * <code>offhand</code> - autocompleted to <code>gungingoom:offhand</code>
     * <br>
     * <code>0..5</code> - autocompleted to <code>gungingoom:standard0..5</code>
     *
     * @param statement A statement in the form namespace:keyOPTIONS
     *
     * @return A built explorer statement if it could be decoded
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null -> null")
    @Nullable public ItemExplorerStatement<?> decodeStatement(@Nullable String statement) {
        if (statement == null) { return null; }
        String namespace = getEngine().getDefaultNamespace();
        String keyOptions = statement;
        if (statement.contains(":")) {
            int namespaceDiv = statement.indexOf(':');
            namespace = statement.substring(0, namespaceDiv);
            keyOptions = statement.substring(namespaceDiv + 1);
        }

        // Decode key options
        return decodeStatement(namespace, keyOptions);
    }

    /**
     * @param statement The explorer statement as provided by the user
     *
     * @return A built Item Stack Explorer, if it could be built
     *
     * @since 1.0.0
     * @author Gunging
     */
    @Contract("null -> null")
    @Nullable public ItemStackExplorer<?> buildItemStackExplorer(@Nullable String statement) {
        if (statement == null) { return null; }
        ItemExplorerStatement<?> decoded = decodeStatement(statement);
        if (decoded == null) { return null; }
        return decoded.prepareExplorer();
    }
}
