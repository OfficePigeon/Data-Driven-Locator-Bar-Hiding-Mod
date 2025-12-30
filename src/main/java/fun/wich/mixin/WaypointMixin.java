package fun.wich.mixin;

import net.minecraft.item.Item;
import net.minecraft.world.waypoint.Waypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Waypoint.class)
public interface WaypointMixin {
	//Removes the hard-coded attribute modifier for disabling waypoint tracking
	@Inject(method="disableTracking", at=@At("HEAD"), cancellable=true)
	private static void DataDrivenLocatorBarHiding_disableTracking(Item.Settings settings, CallbackInfoReturnable<Item.Settings> cir) {
		cir.setReturnValue(settings);
	}
}
