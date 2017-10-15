package kr.kgaons.itemutility;

import kr.kgaons.itemutility.commands.MainCommand;
import kr.kgaons.itemutility.utils.Util;
import kr.kgaons.itemutility.utils.WebHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ItemUtility extends JavaPlugin {
    private final String VERSION = getDescription().getVersion();
    private final String PREFIX = "§6§l[ItemUtility] §f";
    public static ItemUtility INSTANCE;
    private FileConfiguration messages;
    private File messagesfile;
    public static String done_set_name;
    public static String done_add_lore;
    public static String done_del_lore;
    public static String done_set_lore;
    public static String not_handing_item;
    public static String invaild_value;

    @Override
    public void onEnable() {
        Util.enablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion(Util.VERSION);
        INSTANCE = this;
        getCommand("iu").setExecutor(new MainCommand());
        loadDefaultConfig();
    }

    public static ItemUtility getInstance() {
        return INSTANCE;
    }

    public static String getPrefix() {
        return getInstance().PREFIX;
    }

    private void loadDefaultConfig() {
        if (messages == null) {
            messagesfile = new File(getDataFolder(), "messages.yml");
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
        }
    }

    @Override
    public void onDisable() {
        Util.disablePlugin("ItemUtility", VERSION);
        Util.checkOnline();
    }
}
