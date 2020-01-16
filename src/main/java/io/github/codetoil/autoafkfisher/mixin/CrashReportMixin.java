package io.github.codetoil.autoafkfisher.mixin;

import io.github.codetoil.autoafkfisher.AutoAfkFisher;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin
{
	//@ModifyVariable(at = @At(value = "FIELD", shift = At.Shift.BY, by = 5), method = "asString()Ljava/lang/String;", name="stringBuilder_1")
	//private StringBuilder asString(StringBuilder stringBuilder_1)
	//{
	//	stringBuilder_1.append("\nAuto AFK Fish is installed!\n");
	//	return stringBuilder_1;
	//}

	@Inject(at = @At(value = "RETURN"), method="addStackTrace(Ljava/lang/StringBuilder;)V")
	private void addStackTrace(StringBuilder builder, CallbackInfo info)
	{
		builder.append("\n\nAutoAFKFisher v" + AutoAfkFisher.VERSION + " is installed!");
	}
}
