package kr.skylightqp.itemutility;

import kr.skylightqp.itemutility.commands.MainCommand;
import kr.skylightqp.itemutility.listeners.ItemListEvent;
import kr.skylightqp.itemutility.utils.Util;
import kr.skylightqp.itemutility.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemUtility extends JavaPlugin {
    final String PREFIX = "§6§l[ItemUtility] §f";
    private static ItemUtility INSTANCE;
    private static Config config;

    @Override
    public void onEnable() {
        Util.checkOnline();
        WebHook.checkUtilVersion();
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new ItemListEvent(), this);

        config = new Config();
        getLogger().info(">> Load Config");

        if(getConfiguration().autosave){
            Bukkit.getScheduler().runTaskTimer(this,() -> {
                getLogger().info("Saving data...");
                getConfiguration().save();
                getLogger().info("Saved data.");
            },0L, 18000L);
        }

        getLogger().info("Enable Plugin! v" + getDescription().getVersion());
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
        Util.checkOnline();
        getConfiguration().save();
        getLogger().info(">> Saved Config.");
        getLogger().info("Disable Plugin! v" + getDescription().getVersion());
    }
}
