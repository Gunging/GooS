package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEquipmentSlot;
import gunging.ootilities.goof.spigot.GOOFoundationSpigot;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class SpigotEquipmentSlot extends GBMEquipmentSlot<EquipmentSlot> {

    /**
     * @param wrapper The wrapper of this wrap
     * @param wrap The platform object to wrap
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotEquipmentSlot(@NotNull SpigotEquipmentSlotWrapper wrapper, @NotNull EquipmentSlot wrap) {
        super(wrapper, wrap);
    }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull GOOFoundationSpigot getMinecraft() { return (GOOFoundationSpigot) super.getMinecraft(); }
}
