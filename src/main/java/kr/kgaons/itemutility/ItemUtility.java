package kr.kgaons.itemutility;

import kr.kgaons.itemutility.commands.MainCommand;
import kr.kgaons.itemutility.utils.Util;
import kr.kgaons.itemutility.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ItemUtility extends JavaPlugin {
    final String VERSION = getDescription().getVersion();
    final String PREFIX = "§6§l[ItemUtility] §f";
    public static ItemUtility INSTANCE;
    public FileConfiguration items;
    public File itemsfile;
    public FileConfiguration messages;
    public File messagesfile;
    public String done_set_name;
    public String done_add_lore;
    public String done_del_lore;
    public String done_set_lore;
    public String not_handing_item;
    public String invaild_value;
    public String duplicated_item;

    @Override
    public void onEnable() {
        Util.enablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion();
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());

        if (messages == null) {
            messagesfile = new File(getDataFolder(), "messages.yml");
        }
        if (items == null) {
            itemsfile = new File(getDataFolder(), "items.yml");
        }
        if (!messagesfile.exists()) {
            saveResource("messages.yml", false);
            messages = YamlConfiguration.loadConfiguration(messagesfile);
            done_set_name = messages.getString("messages.done_set_name");
            done_add_lore = messages.getString("messages.done_add_lore");
            done_set_lore = messages.getString("messages.done_set_lore");
            done_del_lore = messages.getString("messages.done_del_lore");
            invaild_value = messages.getString("messages.invaild_value");
            not_handing_item = messages.getString("messages.not_handing_item");
            duplicated_item = messages.getString("messages.duplicated_item");
        }
        if (!itemsfile.exists()) {
            saveResource("items.yml", false);
            items = YamlConfiguration.loadConfiguration(itemsfile);
        }

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
