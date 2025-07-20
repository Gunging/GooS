package gunging.ootilities.goof.spigot.exploring.entities;

import gunging.ootilities.goob.GungingOotilitiesBase;
import gunging.ootilities.goob.engine.GOOBEngine;
import gunging.ootilities.goob.engine.GOOBFuel;
import gunging.ootilities.goob.engine.GOOBScrutiny;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.GOOBMinecraftFuel;
import gunging.ootilities.goob.engine.minecraft.standarized.GBMEquipmentSlotted;
import gunging.ootilities.goob.exploring.entities.ISEEntityExplorer;
import gunging.ootilities.goob.exploring.entities.ISEEntityStatement;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import gunging.ootilities.goof.spigot.exploring.entities.specialization.*;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotNamespacedKeyWrapper;
import gunging.ootilities.goof.spigot.engine.minecraft.SpigotPlayerWrapper;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

/**
 * Special slot names for use with {@link ISEEntityExplorer} elaboration.
 *
 * @since 1.0.0
 * @author Gunging
 */
@SuppressWarnings("unused")
public class ISESpigotStatements implements GOOBFuel, GOOBMinecraftFuel  {

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
    @NotNull public static ISEEntityStatement GetByEquipmentSlot(@NotNull GBMEquipmentSlotted<EquipmentSlot> slot) {

        // Spigot Statements are only to be used with Minecraft Spigot
        GOOBEngine base = GungingOotilitiesBase.getEngine();
        GOOBMinecraft mc = base.getMinecraft();
        if (!(mc instanceof GOOFoundationSpigot)) {
            base.scrutinize(GOOBScrutiny.TESTING_MID, "Spigot Explorer Statements were accessed from a different platform. ");

            // It actually makes no sense to cover for this one, if it crashes due to null it crashes. Sorry.
            //noinspection DataFlowIssue
            return null; }

        GOOFoundationSpigot spigot = (GOOFoundationSpigot) mc;
        return spigot.getEntityStatements().getByEquipmentSlot(slot.getEquipmentSlot());
    }


    /**
     * @param slot The Equipment slot you search for
     *
     * @return The appropriate item stack slot statement for this equipment slot.
     *
     * @since 1.0.0
     * @author Gunging
     */
    @NotNull public ISEEntityStatement getByEquipmentSlot(@NotNull EquipmentSlot slot) {
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
    public ISESpigotStatements(@NotNull GOOFoundationSpigot minecraft, @NotNull SpigotNamespacedKeyWrapper names) {
        this.minecraft = minecraft;
        SpigotPlayerWrapper wraps = getMinecraft().getPlayers();
        String space = getEngine().getDefaultNamespace();

        // Initialize "static" slot references
        MAINHAND = new ISESMainhand(this, wraps, names.fromNamespaceAndPath(space, "mainhand"));
        OFFHAND = new ISESOffhand(this, wraps, names.fromNamespaceAndPath(space, "offhand"));
        HEAD = new ISESHead(this, wraps, names.fromNamespaceAndPath(space, "head"));
        CHEST = new ISESChest(this, wraps, names.fromNamespaceAndPath(space, "chest"));
        LEGS = new ISESLegs(this, wraps, names.fromNamespaceAndPath(space, "legs"));
        FEET = new ISESFeet(this, wraps, names.fromNamespaceAndPath(space, "feet"));
        ARMOR = new ISESArmor(this, wraps, names.fromNamespaceAndPath(space, "armor"));
        HANDS = new ISESHands(this, wraps, names.fromNamespaceAndPath(space, "hands"));
        ALL_EQUIPMENT = new ISESAllEquipment(this, wraps, names.fromNamespaceAndPath(space, "all_equipment"));
    }

    /**
     * Represents the four armor slots
     *
     * @since 1.0.0
     */
    @NotNull public ISESArmor ARMOR;
    /**
     * Represents both the hand slots
     *
     * @since 1.0.0
     */
    @NotNull public ISESHands HANDS;

    /**
     * The slot currently held by the entity mainhand.
     *
     * @since 1.0.0
     */
    @NotNull public ISESMainhand MAINHAND;

    /**
     * The slot currently held by the entity offhand.
     *
     * @since 1.0.0
     */
    @NotNull public ISESOffhand OFFHAND;

    /**
     * The slot currently held by the entity helmet.
     *
     * @since 1.0.0
     */
    @NotNull public ISESHead HEAD;

    /**
     * The slot currently held by the entity chest.
     *
     * @since 1.0.0
     */
    @NotNull public ISESChest CHEST;

    /**
     * The slot currently held by the entity chest.
     *
     * @since 1.0.0
     */
    @NotNull public ISESLegs LEGS;

    /**
     * The slot currently held by the entity chest.
     *
     * @since 1.0.0
     */
    @NotNull public ISESFeet FEET;

    /**
     * Represents all the slots of this entity (four armor and two hands)
     *
     * @since 1.0.0
     */
    @NotNull public ISESAllEquipment ALL_EQUIPMENT;
}
