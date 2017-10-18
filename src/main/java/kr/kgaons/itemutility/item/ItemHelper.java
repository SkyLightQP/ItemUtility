package kr.kgaons.itemutility.item;

import kr.kgaons.itemutility.ItemUtility;
import kr.kgaons.itemutility.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemHelper {

    private ItemHelper(){
        throw new UnsupportedOperationException("It's ItemHelper Class");
    }

    public static ItemStack toItemStack(int code, int data, String display, List<String> lores) {
        ItemStack is = new ItemStack(Material.getMaterial(code));
        is.setDurability((short) data);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',display));
        im.setLore(Util.translatingcolorcodes(lores));
        is.setItemMeta(im);
        return is;
    }

    public static int toTypeID(String s){
         return Integer.parseInt(s.split(":")[0]);
    }

    public static int toData(String s){
        return Integer.parseInt(s.split(":")[1]);
    }

    // itemname 객체가 이미 있는가?
    public static boolean isDuplicated(String itemname){
        return ItemUtility.getInstance().items.get("items." + itemname) != null;
    }

    public static void createItem(String itemname){
        ItemUtility.getInstance().items.set("items." + itemname + ".item", "1:0");
        ItemUtility.getInstance().items.set("items." + itemname + ".display", "&fHello, World");
        ItemUtility.getInstance().items.set("items." + itemname + ".lore", Arrays.asList("&fHello","&fWorld"));
    }
}