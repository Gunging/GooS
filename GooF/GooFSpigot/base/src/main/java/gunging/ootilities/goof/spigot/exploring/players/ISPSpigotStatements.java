package gunging.ootilities.goof.spigot.exploring.players;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMEquipmentSlotted;
import gunging.ootilities.goob.exploring.players.ISPPlayerExplorer;
import gunging.ootilities.goob.exploring.players.ISPPlayerStatement;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import gunging.ootilities.goof.spigot.exploring.players.specalization.*;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotNamespacedKeyWrapper;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotPlayerWrapper;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * Special slot names for use with {@link ISPPlayerExplorer} elaboration.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class ISPSpigotStatements implements GOOBFuel, GOOBMinecraftFuel {

    /**
     * The engine this exception is running on
     *
     * @since 1.0.0
     */
    @NotNull final GOOFoundationSpigot minecraft;

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return minecraft; }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOBEngine getEngine() { return getMinecraft().getEngine(); }

    /**
     * @param slot The Equipment slot you search for
     *
     * @return The appropriate item stack slot statement for this equipment slot.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public static ISPPlayerStatement GetByEquipmentSlot(@NotNull GBMEquipmentSlotted<EquipmentSlot> slot) {

        // Spigot Statements are only to be used with Minecraft Spigot
        GOOBEngine base = GungingOotilitiesBase.getEngine();
        GOOBMinecraft mc = base.getMinecraft();
        if (!(mc instanceof GOOFoundationSpigot)) {
            base.scrutinize(GOOBScrutiny.TESTING_MID, "Spigot Explorer Statements were accessed from a different platform. ");

            // It actually makes no sense to cover for this one, if it crashes due to null it crashes. Sorry.
            //noinspection DataFlowIssue
            return null; }

        GOOFoundationSpigot spigot = (GOOFoundationSpigot) mc;
        return spigot.getPlayerStatements().getByEquipmentSlot(slot.getEquipmentSlot());
    }

    /**
     * @param slot The Equipment slot you search for
     *
     * @return The appropriate item stack slot statement for this equipment slot.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public ISPPlayerStatement getByEquipmentSlot(@NotNull EquipmentSlot slot) {
        switch (slot) {
            case FEET:
                return FEET;

            case HEAD:
                return HEAD;

            case LEGS:
                return LEGS;

            case CHEST:
                return CHEST;

            case OFF_HAND:
                return OFFHAND;

            case HAND:
            default:
                return MAINHAND;
        }
    }

    /**
     * @param minecraft The minecraft engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public ISPSpigotStatements(@NotNull GOOFoundationSpigot minecraft, @NotNull SpigotNamespacedKeyWrapper names) {
        this.minecraft = minecraft;
        SpigotPlayerWrapper wraps = getMinecraft().getPlayers();
        String space = getEngine().getDefaultNamespace();

        // Initialize "static" slot references
        STANDARD = new ISPSStandard(this, wraps, names.fromNamespaceAndPath(space, "standard"));
        MAINHAND = new ISPSMainhand(this, wraps, names.fromNamespaceAndPath(space, "player_mainhand"));
        OFFHAND = new ISPSOffhand(this, wraps, names.fromNamespaceAndPath(space, "player_offhand"));
        HEAD = new ISPSHead(this, wraps, names.fromNamespaceAndPath(space, "player_head"));
        CHEST = new ISPSChestplate(this, wraps, names.fromNamespaceAndPath(space, "player_chest"));
        LEGS = new ISPSLegs(this, wraps, names.fromNamespaceAndPath(space, "player_legs"));
        FEET = new ISPSFeet(this, wraps, names.fromNamespaceAndPath(space, "player_feet"));
        ARMOR = new ISPSArmor(this, wraps, names.fromNamespaceAndPath(space, "player_armor"));
        CURSOR = new ISPSCursor(this, wraps, names.fromNamespaceAndPath(space, "cursor"));
        MAIN = new ISPSMain(this, wraps, names.fromNamespaceAndPath(space, "main"));
        STASH = new ISPSStash(this, wraps, names.fromNamespaceAndPath(space, "stash"));
        HANDS = new ISPSHands(this, wraps, names.fromNamespaceAndPath(space, "player_hands"));
        HOTBAR = new ISPSHotbar(this, wraps, names.fromNamespaceAndPath(space, "hotbar"));
        ALL = new ISPSAll(this, wraps, names.fromNamespaceAndPath(space, "all"));
        CRAFTING = new ISPSCrafting(this, wraps, names.fromNamespaceAndPath(space, "crafting"));
        CRAFTING_RESULT = new ISPSCraftingResult(this, wraps, names.fromNamespaceAndPath(space, "craft_result"));
        ALL_CRAFTING = new ISPSAllCrafting(this, wraps, names.fromNamespaceAndPath(space, "all_crafting"));
        ALL_EXTENDED = new ISPSAllExtended(this, wraps, names.fromNamespaceAndPath(space, "all_extended"));
        ENDERCHEST = new ISPSEnderchest(this, wraps, names.fromNamespaceAndPath(space, "enderchest"));
        ALL_ENDERCHEST = new ISPSAllEnderchest(this, wraps, names.fromNamespaceAndPath(space, "all_enderchest"));
        ALL_EQUIPMENT = new ISPSAllEquipment(this, wraps, names.fromNamespaceAndPath(space, "all_player_equipment"));
    }

    /**
     * It is actually not a special slot, just
     * a simple numeric (or number range).
     *
     * @since 1.0.0
     */
    @NotNull public final ISPSStandard STANDARD;

    /**
     * The slot currently held by the player.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSMainhand MAINHAND;

    /**
     * The offhand slot of the player.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSOffhand OFFHAND;

    /**
     * The slot for armor helmet
     *
     * @since 1.0.0
     */
    @NotNull public ISPSHead HEAD;

    /**
     * The slot for armor chestplate
     *
     * @since 1.0.0
     */
    @NotNull public ISPSChestplate CHEST;

    /**
     * The slot for armor leggings
     *
     * @since 1.0.0
     */
    @NotNull public ISPSLegs LEGS;

    /**
     * The slot for armor boots
     *
     * @since 1.0.0
     */
    @NotNull public ISPSFeet FEET;

    /**
     * The four armor slots
     *
     * @since 1.0.0
     */
    @NotNull public ISPSArmor ARMOR;

    /**
     * The cursor slot, only really exists while the inventory GUI is open
     *
     * @since 1.0.0
     */
    @NotNull public ISPSCursor CURSOR;

    /**
     * All the normal storage slots of the inventory, basically
     * the combination of the hotbar and the stash.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSMain MAIN;

    /**
     * All the normal inventory storage slots except for those of
     * the hotbar. Basically (9-35) or something like that.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSStash STASH;

    /**
     * Both the main hand and the offhand
     *
     * @since 1.0.0
     */
    @NotNull public ISPSHands HANDS;

    /**
     * Stands for the first 9 slots (0-8) of the player inventory.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSHotbar HOTBAR;

    /**
     * Absolutely all the slots of the inventory (except crafting grid)
     *
     * @since 1.0.0
     */
    @NotNull public ISPSAll ALL;

    /**
     * Targets specific slots of the crafting grid.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSCrafting CRAFTING;

    /**
     * Targets the result slot of the crafting grid.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSCraftingResult CRAFTING_RESULT;

    /**
     * Targets all slots of the crafting grid.
     *
     * @since 1.0.0
     */
    @NotNull public ISPSAllCrafting ALL_CRAFTING;

    /**
     * Absolutely all the slots of the inventory including crafting grid
     *
     * @since 1.0.0
     */
    @NotNull public ISPSAllExtended ALL_EXTENDED;

    /**
     * A specific slot of the enderchest
     *
     * @since 1.0.0
     */
    @NotNull public ISPSEnderchest ENDERCHEST;

    /**
     * All the slots of the enderchest
     *
     * @since 1.0.0
     */
    @NotNull public ISPSAllEnderchest ALL_ENDERCHEST;

    /**
     * All the slots of the enderchest
     *
     * @since 1.0.0
     */
    @NotNull public ISPSAllEquipment  ALL_EQUIPMENT;
}
