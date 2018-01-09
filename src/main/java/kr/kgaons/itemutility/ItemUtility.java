package kr.kgaons.itemutility;

import kr.kgaons.itemutility.commands.MainCommand;
import kr.kgaons.itemutility.gui.ItemListEvent;
import kr.kgaons.itemutility.gui.ItemListGui;
import kr.kgaons.itemutility.utils.Util;
import kr.kgaons.itemutility.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemUtility extends JavaPlugin {
    final String VERSION = getDescription().getVersion();
    final String PREFIX = "§6§l[ItemUtility] §f";
    private static ItemUtility INSTANCE;
    private static Config config;

    @Override
    public void onEnable() {
        Util.enablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion();
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new ItemListEvent(), this);

        config = new Config();
        getLogger().info("...Load Config");

        if(getConfiguration().autosave){
            new BukkitRunnable() {
                public void run() {
                    getLogger().info("Saving data...");
                    getConfiguration().save();
                    getLogger().info("Saved data.");
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

    public static Config getConfiguration() { return config; }

    @Override
    public void onDisable() {
        Util.disablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        getConfiguration().save();
    }
}
