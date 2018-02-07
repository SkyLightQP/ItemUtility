package kr.skylightqp.itemutility.item;

import kr.skylightqp.itemutility.ItemUtility;
import kr.skylightqp.itemutility.PluginUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateHelper {
    private String itemname;
    private ItemHelper itemHelper;

    public CreateHelper(String itemname){
        this.itemname = itemname;
        this.itemHelper = new ItemHelper(itemname);
    }

    public void createDefaultItem(){
        ItemUtility.getConfiguration().items.set("items." + itemname + ".item", "1:0");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", "&fHello, World");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore", new ArrayList<>());
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants",new ArrayList<>());
    }

    public void saveItemStackToConfig(ItemStack is){
        ItemHelper itemHelper = new ItemHelper(this.itemname);
        ItemMeta im = is.getItemMeta();
        ItemUtility.getConfiguration().items.set("items." + itemname + ".item", is.getTypeId() + ":" + is.getDurability());
        ItemUtility.getConfiguration().items.set("items." + itemname + ".display", im.hasDisplayName() ? im.getDisplayName() : "");
        ItemUtility.getConfiguration().items.set("items." + itemname + ".lore", im.hasLore() ? im.getLore() : new ArrayList<>());
        ItemUtility.getConfiguration().items.set("items." + itemname + ".enchants", im.hasEnchants() ? itemHelper.enchantmentToItemConfig(im.getEnchants()) : new ArrayList<>());
        ItemUtility.getConfiguration().saveItemConfig();
    }

    public void createItem(Player p){
        if(!PluginUtil.isDuplicated(itemname)){
            createDefaultItem();
            ItemUtility.getConfiguration().saveItemConfig();
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_create_item);
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().duplicated_item);
    }

    public void deleteItem(Player p){
        if(PluginUtil.isDuplicated(itemname)){
            ItemUtility.getConfiguration().items.set("items." + itemname,null);
            ItemUtility.getConfiguration().saveItemConfig();
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_delete_item);
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void editItemCode(Player p, String value){
        if(PluginUtil.isDuplicated(itemname)){
            if(value.contains(":")) // 데이터 값이 있을 경우 (5:1) (5:0)
                ItemUtility.getConfiguration().items.set("items." + itemname + ".item", value);
            else  // 데이터 값이 없을 경우 (5)
                ItemUtility.getConfiguration().items.set("items." + itemname + ".item", value + ":0");

            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().edit_itemcode);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void setItemDisplay(Player p, String value){
        if(PluginUtil.isDuplicated(itemname)){
            itemHelper.setDisplay(value);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_set_name);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void addItemLore(Player p, String value){
        if(PluginUtil.isDuplicated(itemname)){
            itemHelper.addLore(value);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_add_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void setItemLore(Player p, String value, int line){
        if(PluginUtil.isDuplicated(itemname)){
            itemHelper.setLore(value,line);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_set_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void delItemLore(Player p, int line){
        if(PluginUtil.isDuplicated(itemname)){
            itemHelper.delLore(line);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_del_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    public void makeHandItem(Player p){
        if(p.getInventory().getItemInMainHand() != null || p.getInventory().getItemInMainHand().getType() != Material.AIR){
            saveItemStackToConfig(p.getInventory().getItemInMainHand());
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_create_item);
        } else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_handing_item);
    }

    public void addItemEnchant(Player p, String enchant, String power){
        if(PluginUtil.isDuplicated(itemname)){
            itemHelper.addItemEnchant(enchant,power);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_enchant);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

}
