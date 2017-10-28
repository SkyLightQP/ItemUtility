package kr.kgaons.itemutility.item;

import kr.kgaons.itemutility.ItemUtility;
import kr.kgaons.itemutility.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemHelper {

    private ItemHelper(){
        throw new UnsupportedOperationException("It's ItemHelper Class");
    }

    public static ItemStack toItemStack(String itemname) {
        return toItemStack(getTypeID(itemname),getData(itemname),getDisplay(itemname),getLore(itemname));
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

    public static int getTypeID(String itemname){
        return toTypeID(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public static int getData(String itemname){
        return toData(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public static String getDisplay(String itemname){
        return ItemUtility.getConfiguration().items.getString("items." + itemname + ".display");
    }

    public static List<String> getLore(String itemname){
        return ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
    }

    public static int toTypeID(String s){
         return Integer.parseInt(s.split(":")[0]);
    }

    public static int toData(String s){
        return Integer.parseInt(s.split(":")[1]);
    }

    public static boolean isNotNull(Object o){
        return o != null;
    }

    // itemname 객체가 이미 있는가?
    public static boolean isDuplicated(String itemname){
        return isNotNull(ItemUtility.getConfiguration().items.get("items." + itemname));
    }

    public static void createDefaultItem(String itemname){
        ItemUtility.getConfiguration().items.set("items." + itemname + ".item", "1:0");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", "&fHello, World");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore", Arrays.asList("&fHello","&fWorld"));
    }

    public static void setDisplay(String itemname, String display){
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", display);
    }

    public static void addLore(String itemname, String value){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.add(value);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }

    public static void setLore(String itemname, String value, int line){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.set(line,value);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }

    public static void delLore(String itemname, int line){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.remove(line);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }
}