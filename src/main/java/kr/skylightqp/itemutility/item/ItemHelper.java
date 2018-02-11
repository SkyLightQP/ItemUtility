package kr.skylightqp.itemutility.item;

import kr.skylightqp.itemutility.ItemUtility;
import kr.skylightqp.itemutility.PluginUtil;
import kr.skylightqp.itemutility.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemHelper {
    private String itemname;

    public ItemHelper(String itemname){
        this.itemname = itemname;
    }

    public ItemStack toItemStack(int code, int data, String display, List<String> lores, List<String> enchants) {
        ItemStack is = new ItemStack(Material.getMaterial(code));
        is.setDurability((short) data);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',display));
        im.setLore(Util.chatColor(lores));
        for(String enchant : enchants) {
            im.addEnchant(itemConfigToEnchantment(enchant),itemConfigToEnchantPower(enchant),false);
        }
        is.setItemMeta(im);
        return is;
    }

    public ItemStack toItemStack() {
        return toItemStack(getTypeIDByItemName(),getDataByItemName(),getDisplayByItemName(),getLoreByItemName(),getEnchantsByItemName());
    }

    public int getTypeIDByItemName(){
        return PluginUtil.toTypeID(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public int getDataByItemName(){
        return PluginUtil.toData(ItemUtility.getConfiguration().items.getString("items." + itemname + ".item"));
    }

    public String getDisplayByItemName(){
        return ItemUtility.getConfiguration().items.getString("items." + itemname + ".display");
    }

    public List<String> getLoreByItemName(){
        return ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
    }

    public List<String> getEnchantsByItemName(){
        return ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".enchants");
    }

    public List<String> enchantmentToItemConfig(Map<Enchantment,Integer> enchantments){
        List<String> enchants = new ArrayList<>();
        for(Enchantment enchantment : enchantments.keySet()){
            enchants.add(enchantment.getName() + ":" + enchantments.get(enchantment));
        }
        return enchants;
    }

    public Enchantment itemConfigToEnchantment(String enchantment){
        String realname = enchantment.split(":")[0];
        return Enchantment.getByName(realname);
    }

    public int itemConfigToEnchantPower(String enchantment){
        int realpower = Integer.parseInt(enchantment.split(":")[1]);
        return realpower;
    }
    public void addItemEnchant(String enchant, String power){
        List list = ItemUtility.getConfiguration().items.getList("items." + itemname + ".enchants");
        list.add(enchant.toUpperCase() + ":" + power);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants", list);
    }

    public void setDisplay(String display){
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", "&f" + display);
    }

    public void addLore(String value){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.add("&f" + value);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }

    public void setLore(String value, int line){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.set(line,"&f" + value);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }

    public void delLore(int line){
        List<String> lores = ItemUtility.getConfiguration().items.getStringList("items." + itemname + ".lore");
        lores.remove(line);
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore",lores);
    }
}