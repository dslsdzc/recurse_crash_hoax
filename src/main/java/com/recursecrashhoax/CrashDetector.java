package com.recursecrashhoax;

import net.minecraftforge.fml.ModList;

public class CrashDetector {
    private static volatile boolean checked = false;
    private static volatile boolean shouldCrash = false;

    private CrashDetector() {}

    public static boolean shouldCrash() {
        if (!checked) {
            ModList modList = ModList.get();
            shouldCrash = modList != null
                          && modList.isLoaded("fix_create_crash_hoax");
            checked = true;
        }
        return shouldCrash;
    }
}
