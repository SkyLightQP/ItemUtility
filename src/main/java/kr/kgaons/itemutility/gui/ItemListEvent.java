package kr.kgaons.itemutility.gui;

import kr.kgaons.itemutility.item.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

public class ItemListEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        String name = ItemListGui.getItemListGui().getInventoryName();
        Player p = (Player) e.getView().getPlayer();
        if(e.getInventory().getTitle().equals(name)){
            if(e.getCurrentItem().getType() != Material.AIR || e.getCurrentItem().getType() != null){
                e.setCancelled(true);

                ItemMeta im = e.getCurrentItem().getItemMeta();
                if(im.hasLore()){
                    int line = im.getLore().size() - 1;
                    String itemname = im.getLore().get(line).replace("§7아이템 이름: ","");
                    ItemStack result = ItemHelper.toItemStack(itemname);
                    p.getInventory().addItem(result);
                }
            }
        }
    }
}
