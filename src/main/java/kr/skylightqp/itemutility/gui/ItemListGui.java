package kr.skylightqp.itemutility.gui;

import kr.skylightqp.itemutility.ItemUtility;
import kr.skylightqp.itemutility.item.ItemHelper;
import kr.skylightqp.itemutility.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemListGui {

    private FileConfiguration config = ItemUtility.getConfiguration().items;

    public void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54,"아이템 목록");

        Set<String> list = config.getConfigurationSection("items").getKeys(false);

        int slot = 0;
        for(String name : list){
            ItemStack is = new ItemHelper(name).toItemStack();
            ItemMeta im = is.getItemMeta();
            List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<>();
            lore.add("§f");
            lore.add("§7아이템 이름: " + name);
            Util.setItem(im.getDisplayName(),is.getType(),is.getDurability(),is.getAmount(),lore,is.getEnchantments(),slot,inv);
            slot++;
        }

        p.openInventory(inv);
    }
}
