package kr.kgaons.itemutility;

import kr.kgaons.itemutility.commands.MainCommand;
import kr.kgaons.itemutility.utils.Util;
import kr.kgaons.itemutility.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ItemUtility extends JavaPlugin {
    final String VERSION = getDescription().getVersion();
    final String PREFIX = "§6§l[ItemUtility] §f";
    public static ItemUtility INSTANCE = null;

    public static Config config = null;

    @Override
    public void onEnable() {
        Util.enablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion();
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());

        config = new Config();
        Bukkit.getLogger().info("[ItemUtility] Config has been load.");
    }

    public static ItemUtility getInstance() {
        return INSTANCE;
    }

    public static String getPrefix() {
        return getInstance().PREFIX;
    }

    @Override
    public void onDisable() {
        Util.disablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
    }
}
