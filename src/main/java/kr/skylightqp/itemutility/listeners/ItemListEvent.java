package kr.skylightqp.itemutility.listeners;

import kr.skylightqp.itemutility.item.ItemHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemListEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        String name = "아이템 목록";
        Player p = (Player) e.getView().getPlayer();
        if(e.getInventory().getTitle().equals(name)){
            e.setCancelled(true);
            if(e.getCurrentItem() != null && (e.getCurrentItem().getType() != null || e.getCurrentItem().getType() != Material.AIR) && e.getCurrentItem().hasItemMeta()){
                ItemMeta im = e.getCurrentItem().getItemMeta();
                if(im.hasLore()){
                    int line = im.getLore().size() - 1;
                    String itemname = im.getLore().get(line).replace("§7아이템 이름: ","");
                    ItemStack result = new ItemHelper(itemname).toItemStack();
                    p.getInventory().addItem(result);
                }
            }
        }
    }
}
