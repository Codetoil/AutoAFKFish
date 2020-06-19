package io.github.codetoil.autoafkfisher.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin
{
	@Inject(at = @At(value = "RETURN"), method="addStackTrace(Ljava/lang/StringBuilder;)V")
	private void addStackTrace(StringBuilder builder, CallbackInfo info)
	{
		Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("autoafkfisher");
		if (container.isPresent())
		{
			builder.append("\n\nAutoAFKFisher v").append(container.get().getMetadata().getVersion()).append(" is installed!");
		}
		else
		{
			builder.append("\n\nAutoAFKFisher of unknown version is installed!");
		}
	}
}
