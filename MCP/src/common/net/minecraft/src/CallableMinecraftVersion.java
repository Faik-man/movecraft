package net.minecraft.src;

import java.util.concurrent.Callable;

public class CallableMinecraftVersion implements Callable
{
    /** Gets the Crash Rrport current Minecraft version. */
    final CrashReport crashReportMinecraftVersion;

    public CallableMinecraftVersion(CrashReport par1CrashReport)
    {
        this.crashReportMinecraftVersion = par1CrashReport;
    }

    public String func_71493_a()
    {
        return "1.3.2";
    }

    public Object call()
    {
        return this.func_71493_a();
    }
}
