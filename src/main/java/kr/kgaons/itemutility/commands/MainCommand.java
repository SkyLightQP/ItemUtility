package kr.kgaons.itemutility.commands;

import kr.kgaons.itemutility.item.ItemHelper;
import kr.kgaons.itemutility.ItemUtility;
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
                p.sendMessage(ItemUtility.getConfiguration().duplicated_item);
            }
            else sendHelpMessage(p);
        }
        return false;
    }

    private void sendHelpMessage(Player p){
        p.sendMessage(ItemUtility.getPrefix() + "/iu create <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu delete <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu item <code:data>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu display <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loreadd <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loredel <line>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu loreset <line> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu addhand <ItemName>");
        p.sendMessage(ItemUtility.getPrefix() + "/iu list");
    }

    private void createItem(Player p, String itemname){
        if(!ItemHelper.isDuplicated(itemname)){
            ItemHelper.createItem(itemname);
        }
        else p.sendMessage(ItemUtility.getConfiguration().duplicated_item);
    }
}
