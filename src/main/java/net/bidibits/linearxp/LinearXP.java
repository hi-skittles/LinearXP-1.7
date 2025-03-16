package net.bidibits.linearxp;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = LinearXP.MODID, version = LinearXP.VERSION, acceptableRemoteVersions = "*")
public class LinearXP {
    public static final String MODID = "linearxp";
    public static final String VERSION = "1.0.4";
    public static int xpPerLevel;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        xpPerLevel = config.getInt("xpPerLevel", "general", 25, 1, Integer.MAX_VALUE, "Raw XP required per level");
        config.save();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }
}