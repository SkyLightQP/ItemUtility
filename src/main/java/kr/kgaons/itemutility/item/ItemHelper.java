package kr.kgaons.itemutility.item;

import kr.kgaons.itemutility.ItemUtility;
import kr.kgaons.itemutility.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemHelper {

    private ItemHelper(){
        throw new UnsupportedOperationException("It's ItemHelper Class");
    }

    public static ItemStack toItemStack(String itemname) {
        return toItemStack(getTypeIDByItemName(itemname),getDataByItemName(itemname),getDisplayByItemName(itemname),getLoreByItemName(itemname),getEnchantsByItemName(itemname));
    }

    public static ItemStack toItemStack(int code, int data, String display, List<String> lores, List<String> enchants) {
        ItemStack is = new ItemStack(Material.getMaterial(code));
        is.setDurability((short) data);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',display));
        im.setLore(Util.translatingcolorcodes(lores));
        for(String enchant : enchants) {
            im.addEnchant(itemConfigToEnchantment(enchant),itemConfigToEnchantPower(enchant),false);
        }
        is.setItemMeta(im);
        return is;
    }

    public static void saveItemStackToConfig(ItemStack is, String itemname){
        ItemMeta im = is.getItemMeta();
        ItemUtility.getConfiguration().items.set("items." + itemname + ".item", is.getTypeId() + ":" + is.getDurability());
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", im.hasDisplayName() ? im.getDisplayName() : "");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore", im.hasLore() ? im.getLore() : new ArrayList<>());
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants", im.hasEnchants() ? enchantmentToItemConfig(im.getEnchants()) : new ArrayList<>());
        ItemUtility.getConfiguration().saveItemConfig();
    }

    public static int getTypeIDByItemName(String itemname){
        return toTypeID(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public static int getDataByItemName(String itemname){
        return toData(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public static String getDisplayByItemName(String itemname){
        return ItemUtility.getConfiguration().items.getString("items." + itemname + ".display");
    }

    public static List<String> getLoreByItemName(String itemname){
        return ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
    }

    public static List<String> getEnchantsByItemName(String itemname){
        return ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".enchants");
    }

    public static int toTypeID(String s){
         return Integer.parseInt(s.split(":")[0]);
    }

    public static int toData(String s){
        return Integer.parseInt(s.split(":")[1]);
    }

    public static List<String> enchantmentToItemConfig(Map<Enchantment,Integer> enchantments){
        List<String> enchants = new ArrayList<>();
        for(Enchantment enchantment : enchantments.keySet()){
            enchants.add(enchantment.getName() + ":" + enchantments.get(enchantment));
        }
        return enchants;
    }

    public static Enchantment itemConfigToEnchantment(String enchantment){
        String realname = enchantment.split(":")[0];
        return Enchantment.getByName(realname);
    }
    public static int itemConfigToEnchantPower(String enchantment){
        int realpower = Integer.parseInt(enchantment.split(":")[1]);
        return realpower;
    }
    public static void addItemEnchant(String itemname, String enchant, String power){
        List list = ItemUtility.getConfiguration().items.getList("items." + itemname + ".enchants");
        list.add(enchant + ":" + power);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants", list);
    }

    // 객체가 이미 있는가? (중복인가?)
    public static boolean isDuplicated(String itemname){
        return (ItemUtility.getConfiguration().items.get("items." + itemname) != null);
    }

    public static void createDefaultItem(String itemname){
        ItemUtility.getConfiguration().items.set("items." + itemname + ".item", "1:0");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", "&fHello, World");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore", Arrays.asList("&fHello","&fWorld"));
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants",new ArrayList<>());
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