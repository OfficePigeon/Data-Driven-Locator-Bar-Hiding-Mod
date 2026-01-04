package fun.wich.mixin;

import fun.wich.DataDrivenLocatorBarHidingMod;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class DataDrivenLocatorBarLivingEntityMixin extends Entity implements Attackable, ServerWaypoint {
	@Shadow public abstract AttributeContainer getAttributes();
	@Shadow protected abstract void updateAttribute(RegistryEntry<EntityAttribute> attribute);
	@Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

	public DataDrivenLocatorBarLivingEntityMixin(EntityType<?> type, World world) { super(type, world); }

	@Inject(method="tickStatusEffects", at=@At("HEAD"))
	private void DataDrivenLocatorBarHiding_tickStatusEffects(CallbackInfo ci) {
		if (this.getEntityWorld() instanceof ServerWorld) {
			AttributeContainer attributes = this.getAttributes();
			EntityAttributeInstance instance = attributes.getCustomInstance(EntityAttributes.WAYPOINT_TRANSMIT_RANGE);
			if (instance != null) {
				instance.removeModifier(DataDrivenLocatorBarHidingMod.DISABLE_TRACKING.id());
				for (EquipmentSlot slot : EquipmentSlot.values()) {
					if (this.getEquippedStack(slot).isIn(DataDrivenLocatorBarHidingMod.TAG_LOCATOR_BAR_HIDDEN)) {
						instance.addPersistentModifier(DataDrivenLocatorBarHidingMod.DISABLE_TRACKING);
						break;
					}
				}
				this.updateAttribute(EntityAttributes.WAYPOINT_TRANSMIT_RANGE);
			}
		}
	}
}
