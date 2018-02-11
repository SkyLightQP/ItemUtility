package kr.skylightqp.itemutility.gui;

import kr.skylightqp.itemutility.ItemUtility;
import kr.skylightqp.itemutility.item.ItemHelper;
import kr.skylightqp.itemutility.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemListGui {
    private FileConfiguration config = ItemUtility.getConfiguration().items;
    private static int nowPage;
    private int maxPage;
    private int minPage;
    private List<String> list;

    public ItemListGui() {
        list = new ArrayList<>(config.getConfigurationSection("items").getKeys(false));
        this.maxPage = list.size() % 45 == 0 ? list.size() / 45 : (int)(Math.ceil(list.size() / 45) + 1);
        this.minPage = 1;
    }

    public void open(Player p, int nowPage) {
        this.nowPage = nowPage;
        Inventory inv = Bukkit.createInventory(null, 54, "아이템 목록");

        int slot = (nowPage - 1) * 45;
        int end = nowPage * 45;
        for (int i = slot; i < end; i++) {
            if(i >= list.size()){
                continue;
            }
            String name = list.get(i);
            ItemStack is = new ItemHelper(name).toItemStack();
            ItemMeta im = is.getItemMeta();
            List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<>();
            lore.add("§f");
            lore.add("§7아이템 이름: " + name);
            Util.setItem(im.getDisplayName(), is.getType(), is.getDurability(), is.getAmount(), lore, is.getEnchantments(), i - slot, inv);
        }

        Util.setItem("§f이전 페이지", Material.PAPER, 0, 1, new ArrayList<>(), 48, inv);
        Util.setItem("§f현재 페이지 §7(" + nowPage + "/" + maxPage + ")", Material.EMPTY_MAP, 0, 1, new ArrayList<>(), 49, inv);
        Util.setItem("§f다음 페이지", Material.PAPER, 0, 1, new ArrayList<>(), 50, inv);

        p.openInventory(inv);
    }

    public void toNextPage(Player p) {
        if (nowPage < maxPage) {
            nowPage++;
            open(p, nowPage);
        } else {
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().last_page);
        }
    }

    public void toPrevPage(Player p) {
        if (nowPage > minPage) {
            nowPage--;
            open(p, nowPage);
        } else {
            p.sendMessage(ItemUtility.getPrefix() + ItemUtility.getConfiguration().first_page);
        }
    }
}
