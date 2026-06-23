# recurse_crash_hoax (Recurse Crash Hoax)

套娃链第 4 层：检测到 `fix_create_crash_hoax` 就崩溃。

## 链

```
createcrashfix                    — 机械动力崩溃修复
    ↓ 安装它
create_crash_fix_crash            — "你为什么要这么做？"
    ↓ 安装它
fix_create_crash_hoax             — "我这么做是为了让你不崩"
    ↓ 安装它
recurse_crash_hoax                — "你修了崩溃？那我来当新的崩溃！"
    ↓ ???
```

## 技术原理

### 逆向修补 (Reverse Patch)

通过 Mixin 注入 `CrashProcedure.execute(Event)`：

- **优先抢占**：`priority = 500`，比 Level 3 的 1000 更高
- **抢先抛出异常**：在 Level 3 `ci.cancel()` 之前 throw RuntimeException
- **堆栈伪装**：崩溃报告显示 `CrashProcedure.execute()`
- **Level 3 的修复被完全绕过**

### 后备机制

`MinecraftMixin` 注入 `Minecraft.tick()`，启动后随机 5~30 秒崩溃。

## 构建

```bash
./gradlew jar
```

## 许可

MIT License
