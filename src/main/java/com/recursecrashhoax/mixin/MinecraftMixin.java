package com.recursecrashhoax.mixin;

import com.recursecrashhoax.CrashDetector;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    private static int rc_ticksUntilCrash = -1;
    private static final Random rc_RNG = new Random();

    @Inject(method = "tick", at = @At("RETURN"))
    private void rc_onTick(CallbackInfo ci) {
        if (!CrashDetector.shouldCrash()) return;

        if (rc_ticksUntilCrash < 0) {
            rc_ticksUntilCrash = 100 + rc_RNG.nextInt(501);
        }

        if (--rc_ticksUntilCrash <= 0) {
            rc_ticksUntilCrash = -1;
            throw new RuntimeException("""
                    你修了崩溃？那我来当新的崩溃！
                    —— 递归永不停止。

                    Chain:
                      createcrashfix                             — 修复机械动力崩溃
                        -> create_crash_fix_crash                 — "你为什么要这么做？"
                        -> fix_create_crash_hoax                  — "我这么做是为了让你不崩"
                        -> recurse_crash_hoax                     — "你修了崩溃？那我来当新的崩溃！" ★
                        -> ???                                    — 等你的 Level 5
                    """);
        }
    }
}
