package kr.kgaons.itemutility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public File configfile;
    public FileConfiguration config;
    public boolean autosave;

    public FileConfiguration items;
    public File itemsfile;

    public File messagesfile;
    public FileConfiguration messages;
    public String done_set_name;
    public String done_add_lore;
    public String done_del_lore;
    public String done_set_lore;
    public String not_handing_item;
    public String invaild_value;
    public String duplicated_item;

    public Config() {
        if (config == null) {
            configfile = new File(ItemUtility.getInstance().getDataFolder(), "config.yml");
        }
        if (messages == null) {
            messagesfile = new File(ItemUtility.getInstance().getDataFolder(), "messages.yml");
        }
        if (items == null) {
            itemsfile = new File(ItemUtility.getInstance().getDataFolder(), "items.yml");
        }
        if (!configfile.exists()) {
            ItemUtility.getInstance().saveResource("config.yml", false);
            config = YamlConfiguration.loadConfiguration(configfile);
            autosave  = config.getBoolean("autosave");
        }
        if (!messagesfile.exists()) {
            ItemUtility.getInstance().saveResource("messages.yml", false);
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
            ItemUtility.getInstance().saveResource("items.yml", false);
            items = YamlConfiguration.loadConfiguration(itemsfile);
        }
    }

    public void save(){
        try {
            config.save(configfile);
            messages.save(messagesfile);
            items.save(itemsfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig(){
        return config;
    }
    public FileConfiguration getItemConfig(){
        return items;
    }
    public FileConfiguration getMessageConfig(){
        return messages;
    }
}
