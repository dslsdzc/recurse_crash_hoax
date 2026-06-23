package com.recursecrashhoax;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 套娃链第 4 层：检测到 fix_create_crash_hoax 就崩
 *
 * <p>原链：
 * <ol>
 *   <li>createcrashfix — 机械动力的崩溃修复</li>
 *   <li>create_crash_fix_crash — 加载 ① 就崩 ("你为什么要这么做？")</li>
 *   <li>fix_create_crash_hoax — Mixin 吞掉 ② 的异常 ("我这么做是为了让你不崩")</li>
 *   <li><b>recurse_crash_hoax</b> — 检测到 ③ 就崩 (本 mod)</li>
 * </ol>
 *
 * <h3>崩溃机制</h3>
 * <ul>
 *   <li>{@link com.recursecrashhoax.mixin.MinecraftMixin MinecraftMixin} 注入
 *       {@code Minecraft.tick()} 的 {@code @At("RETURN")}，每次 tick 完检查</li>
 *   <li>启动后随机 100~600 ticks（≈5~30 秒）才崩，每次启动不同</li>
 *   <li>{@code @At("RETURN")} 让崩溃看起来像是 tick 内部出了异常</li>
 * </ul>
 *
 * <h3>留了一线生机</h3>
 * <ul>
 *   <li>{@link CrashDetector#shouldCrash()} — 独立静态方法，可用 {@code @Redirect} 拦截（见该类 javadoc）</li>
 *   <li>JVM 参数 {@code - Drecurse_crash_hoax.bypass=true} — 隐藏后门</li>
 * </ul>
 */
@Mod(RecurseCrashHoax.MODID)
public class RecurseCrashHoax {

    public static final String MODID = "recurse_crash_hoax";
    public static final Logger LOGGER = LogManager.getLogger();

    public RecurseCrashHoax() {
        LOGGER.info("RecurseCrashHoax 加载中……");
        LOGGER.info("\"你修了崩溃？那我来当新的崩溃！\"");
        LOGGER.info("不定时炸弹已就绪 —— 5~30 秒后你自会明白。");
    }
}
