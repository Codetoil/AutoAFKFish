package io.github.codetoil.autoafkfisher.mixin;

import io.github.codetoil.autoafkfisher.AutoAfkFisher;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin
{
	@Shadow public abstract Hand getActiveHand();

	//@Inject(at = @At("RETURN"), method = "isUsingItem", cancellable = true)
	public void isUsingItem(CallbackInfoReturnable<Boolean> ci)
	{
		ci.setReturnValue(ci.getReturnValue() || (this.getActiveHand() != null && AutoAfkFisher.isafkon));
	}
}
