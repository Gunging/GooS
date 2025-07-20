package gunging.ootilities.goof.spigot.engine.minecraft;

import gunging.ootilities.goob.engine.minecraft.GOOBMinecraft;
import gunging.ootilities.goob.engine.minecraft.wrapping.GBMEquipmentSlotWrapper;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class SpigotEquipmentSlotWrapper extends GBMEquipmentSlotWrapper<SpigotEquipmentSlot, EquipmentSlot> {

    /**
     * @param minecraft The engine this runs in
     *
     * @author Gunging
     * @since 1.0.0
     */
    public SpigotEquipmentSlotWrapper(@NotNull GOOBMinecraft minecraft) { super(minecraft); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull SpigotEquipmentSlot wrap(@NotNull EquipmentSlot slot) { return new SpigotEquipmentSlot(this, slot); }

    /**
     * @author Gunging
     * @since 1.0.0
     */
    @Override public @NotNull Class<EquipmentSlot> getWrappedClass() { return EquipmentSlot.class; }
}
