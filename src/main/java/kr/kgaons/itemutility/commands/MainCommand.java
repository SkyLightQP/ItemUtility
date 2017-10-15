package kr.kgaons.itemutility.commands;

import com.google.gson.Gson;
import kr.kgaons.itemutility.ItemUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        if(cs instanceof Player){
            Player p = (Player) cs;
            if(args.length > 0){

            }
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
}
