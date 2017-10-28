package kr.kgaons.itemutility;

import kr.kgaons.itemutility.commands.MainCommand;
import kr.kgaons.itemutility.utils.Util;
import kr.kgaons.itemutility.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemUtility extends JavaPlugin {
    final String VERSION = getDescription().getVersion();
    final String PREFIX = "§6§l[ItemUtility] §f";
    public static ItemUtility INSTANCE = null;
    public Config config = null;

    @Override
    public void onEnable() {
        Util.enablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion();
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());

        config = new Config();

        if(getConfiguration().autosave){
            new BukkitRunnable() {
                public void run() {
                    Bukkit.getLogger().info("[ItemUtility] Saving config...");
                    getConfiguration().save();
                    Bukkit.getLogger().info("[ItemUtility] Saved config.");
                }
            }.runTaskTimer(this, 0L, 18000);
        }
    }

    public static ItemUtility getInstance() {
        return INSTANCE;
    }

    public static String getPrefix() {
        return getInstance().PREFIX;
    }

    public static Config getConfiguration() { return getInstance().config; }

    @Override
    public void onDisable() {
        Util.disablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        getConfiguration().save();
    }
}
