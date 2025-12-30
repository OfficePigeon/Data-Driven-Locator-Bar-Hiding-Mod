package fun.wich;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class DataDrivenLocatorBarHidingMod implements ModInitializer {
	public static final String MOD_ID = "wich";
	public static final TagKey<Item> TAG_LOCATOR_BAR_HIDDEN = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "locator_bar_hidden"));
	public static final EntityAttributeModifier DISABLE_TRACKING = new EntityAttributeModifier(
			Identifier.of(DataDrivenLocatorBarHidingMod.MOD_ID, "waypoint_transmit_range_hide"), -1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
	);
	@Override
	public void onInitialize() { }
}