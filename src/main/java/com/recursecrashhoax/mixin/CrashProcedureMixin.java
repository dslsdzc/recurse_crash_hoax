package com.recursecrashhoax.mixin;

import com.recursecrashhoax.CrashDetector;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "createcrashfixcrash.procedures.CrashProcedure", remap = false, priority = 500)
public abstract class CrashProcedureMixin {

    @Inject(method = "execute(Lnet/minecraftforge/eventbus/api/Event;)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void onExecute(Event event, CallbackInfo ci) {
        if (!CrashDetector.shouldCrash()) return;

        throw new RuntimeException("""
                你修了崩溃？那我来当新的崩溃！
                ——递归永不停止。
                """);
    }
}
