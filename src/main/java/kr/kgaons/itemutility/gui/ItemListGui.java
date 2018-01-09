package kr.kgaons.itemutility.gui;

import kr.kgaons.itemutility.ItemUtility;
import kr.kgaons.itemutility.item.ItemHelper;
import kr.kgaons.itemutility.utils.Util;
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

    public String getInventoryName() {
        return "아이템 목록";
    }

    public void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, getInventoryName());

        Set<String> list = config.getConfigurationSection("items").getKeys(false);

        int slot = 0;
        for(String name : list){
            ItemStack is = ItemHelper.toItemStack(name);
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
