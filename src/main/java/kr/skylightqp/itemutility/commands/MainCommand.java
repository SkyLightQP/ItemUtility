package kr.skylightqp.itemutility.commands;

import kr.skylightqp.itemutility.ItemUtility;
import kr.skylightqp.itemutility.gui.ItemListGui;
import kr.skylightqp.itemutility.item.CreateHelper;
import kr.skylightqp.itemutility.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        ItemListGui itemlistgui = new ItemListGui();
        if (cs instanceof Player) {
            Player p = (Player) cs;
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("list")) {
                    itemlistgui.open(p,1);
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    ItemUtility.getConfiguration().save();
                    p.sendMessage(ItemUtility.getPrefix() + "리로드 완료.");
                    return true;
                }
                if (args.length > 1) {
                    if (args[0].equalsIgnoreCase("delete")) {
                        new CreateHelper(args[1]).deleteItem(p);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("hand")) {
                        new CreateHelper(args[1]).makeHandItem(p);
                        return true;
                    }
                } else {
                    p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                    return true;
                }
                if (args.length > 2) {
                    if (args[0].equalsIgnoreCase("display")) {
                        String name = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        new CreateHelper(args[1]).setItemDisplay(p, name);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("loreadd")) {
                        String name = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        new CreateHelper(args[1]).addItemLore(p, name);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("loredel")) {
                        if (Util.isInteger(args[2], true)) {
                            try {
                                new CreateHelper(args[1]).delItemLore(p, Integer.parseInt(args[2]));
                            } catch (IndexOutOfBoundsException e) {
                                p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                            }
                        } else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                        return true;
                    }
                } else {
                    p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                    return true;
                }
                if (args.length > 3) {
                    if (args[0].equalsIgnoreCase("create")) {
                        new CreateHelper(args[1]).createItem(p, args[2], args[3]);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("item")) {
                        new CreateHelper(args[1]).editItemCode(p, args[2], args[3]);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("loreset")) {
                        if (Util.isInteger(args[2], true)) {
                            try {
                                String name = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                                new CreateHelper(args[1]).setItemLore(p, name, Integer.parseInt(args[2]));
                            } catch (IndexOutOfBoundsException e) {
                                p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                            }
                        } else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("enchant")) {
                        if(Enchantment.getByName(args[2]) != null) {
                            if (Util.isInteger(args[3], true)) {
                                new CreateHelper(args[1]).addItemEnchant(p, args[2], args[3]);
                            } else
                                p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().invaild_value);
                        }
                        else p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().fail_enchant);
                        return true;
                    }
                } else {
                    p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().error_use_command);
                    return true;
                }
            } else {
                sendHelpMessage(p);
                return true;
            }
        }
        return false;
    }

    private void sendHelpMessage(Player p) {
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu create <itemname> <code> <data>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu delete <itemname>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu item <itemname> <code> <data>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu display <itemname> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu loreadd <itemname> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu loredel <itemname> <line>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu loreset <itemname> <line> <text>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu enchant <itemname> <enchant> <power>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu hand <itemname>");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu list");
        p.sendMessage(ItemUtility.getPrefix() + "§7/iu reload");
    }
}
