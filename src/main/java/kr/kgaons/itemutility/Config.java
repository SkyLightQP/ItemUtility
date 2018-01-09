package kr.kgaons.itemutility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private File configfile = new File(ItemUtility.getInstance().getDataFolder(), "config.yml");
    private FileConfiguration config;
    public boolean autosave = true;

    public FileConfiguration items;
    private File itemsfile = new File(ItemUtility.getInstance().getDataFolder(), "items.yml");

    private File messagesfile = new File(ItemUtility.getInstance().getDataFolder(), "messages.yml");

    public FileConfiguration messages;
    public String done_set_name;
    public String done_add_lore;
    public String done_del_lore;
    public String done_set_lore;
    public String not_handing_item;
    public String invaild_value;
    public String duplicated_item;
    public String not_duplicated_item;
    public String done_create_item;
    public String done_delete_item;
    public String edit_itemcode;
    public String error_use_command;

    public Config() {
        config = YamlConfiguration.loadConfiguration(configfile);
        messages = YamlConfiguration.loadConfiguration(messagesfile);
        items = YamlConfiguration.loadConfiguration(itemsfile);

        ItemUtility.getInstance().saveResource("config.yml", false);
        load(config, configfile);
        ItemUtility.getInstance().saveResource("messages.yml", false);
        load(messages, messagesfile);
        ItemUtility.getInstance().saveResource("items.yml", false);
        load(items, itemsfile);

        autosave = config.getBoolean("autosave");
        done_set_name = messages.getString("messages.done_set_name");
        done_add_lore = messages.getString("messages.done_add_lore");
        done_set_lore = messages.getString("messages.done_set_lore");
        done_del_lore = messages.getString("messages.done_del_lore");
        invaild_value = messages.getString("messages.invaild_value");
        not_handing_item = messages.getString("messages.not_handing_item");
        duplicated_item = messages.getString("messages.duplicated_item");
        not_duplicated_item = messages.getString("messages.not_duplicated_item");
        done_create_item = messages.getString("messages.done_create_item");
        done_delete_item = messages.getString("messages.done_delete_item");
        edit_itemcode = messages.getString("messages.edit_itemcode");
        error_use_command = messages.getString("messages.error_use_command");
    }

    public void saveItemConfig(){
        save(this.items,this.itemsfile);
    }

    public void save(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().throwing("Config","save",e);
        }
    }

    public void save() {
        try {
            config.save(configfile);
            messages.save(messagesfile);
            items.save(itemsfile);
        } catch (IOException e) {
            Bukkit.getLogger().throwing("Config","save",e);
        }
    }

    public void load(FileConfiguration config, File file) {
        try {
            config.load(file);
        } catch (IOException e) {
            Bukkit.getLogger().throwing("Config","load",e);
        } catch (InvalidConfigurationException e) {
            Bukkit.getLogger().throwing("Config","load",e);
        }
    }
}
