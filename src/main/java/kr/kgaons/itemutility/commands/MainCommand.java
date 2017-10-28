package kr.kgaons.itemutility.commands;

import kr.kgaons.itemutility.item.ItemHelper;
import kr.kgaons.itemutility.ItemUtility;
import kr.kgaons.itemutility.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        if(cs instanceof Player){
            Player p = (Player) cs;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("create")){
                    if(args.length > 1){
                        this.createItem(p,args[1]);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("delete")){
                    if(args.length > 1){
                        this.deleteItem(p,args[1]);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("item")){
                    if(args.length > 2){
                        this.editItemCode(p,args[1],args[2]);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("display")){
                    if(args.length > 2){
                        this.setItemDisplay(p,args[1],args[2]);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("loreadd")){
                    if(args.length > 2){
                        this.addItemLore(p,args[1],args[2]);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("loredel")){
                    if(args.length > 2){
                        if(Util.isInteger(args[2],true)) {
                            try {
                                this.delItemLore(p, args[1], Integer.parseInt(args[2]));
                            }
                            catch (IndexOutOfBoundsException e){
                                p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                            }
                        }
                        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("loreset")){
                    if(args.length > 3){
                        if(Util.isInteger(args[2],true)) {
                            try {
                                this.setItemLore(p, args[1], args[3], Integer.parseInt(args[2]));
                            }
                            catch (IndexOutOfBoundsException e){
                                p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                            }
                        } else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                    }
                    else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                }
                if(args[0].equalsIgnoreCase("reload")){
                    ItemUtility.getConfiguration().save();
                }
            }
            else sendHelpMessage(p);
    }
        return false;
    }

    private void sendHelpMessage(Player p){
        p.sendMessage(ItemUtility.getPrefix() + "/iu create <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu delete <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu item <ItemName> <code:data>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu display <ItemName> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loreadd <ItemName> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loredel <ItemName> <line>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loreset <ItemName> <line> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu hand <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu list");
        p.sendMessage(ItemUtility.getPrefix() + "/iu reload");
    }

    private void createItem(Player p, String itemname){
        if(!ItemHelper.isDuplicated(itemname)){
            ItemHelper.createDefaultItem(itemname);
            ItemUtility.getConfiguration().saveItemConfig();
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_create_item);
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().duplicated_item);
    }

    private void deleteItem(Player p, String itemname){
        if(ItemHelper.isDuplicated(itemname)){
            ItemUtility.getConfiguration().items.set("items." + itemname,null);
            ItemUtility.getConfiguration().saveItemConfig();
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_delete_item);
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    private void editItemCode(Player p, String itemname, String value){
        if(ItemHelper.isDuplicated(itemname)){
            if(value.contains(":")) // 데이터 값이 있을 경우 (5:1) (5:0)
                ItemUtility.getConfiguration().items.set("items." + itemname + ".item", value);
            else { // 데이터 값이 없을 경우 (5)
                ItemUtility.getConfiguration().items.set("items." + itemname + ".item", value + ":0");
            }
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().edit_itemcode);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    private void setItemDisplay(Player p, String itemname, String value){
        if(ItemHelper.isDuplicated(itemname)){
            ItemHelper.setDisplay(itemname,value);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_set_name);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    private void addItemLore(Player p, String itemname, String value){
        if(ItemHelper.isDuplicated(itemname)){
            ItemHelper.addLore(itemname,value);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_add_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    private void setItemLore(Player p, String itemname, String value, int line){
        if(ItemHelper.isDuplicated(itemname)){
            ItemHelper.setLore(itemname,value,line);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_set_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }

    private void delItemLore(Player p, String itemname, int line){
        if(ItemHelper.isDuplicated(itemname)){
            ItemHelper.delLore(itemname,line);
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().done_del_lore);

            ItemUtility.getConfiguration().saveItemConfig();
        }
        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().not_duplicated_item);
    }
}
