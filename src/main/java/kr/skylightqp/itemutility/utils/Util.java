package kr.skylightqp.itemutility.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Util {

	// Made by SkyLightQP //
	// http://blog.skylightqp.kr //

	public static final String UTIL_VERSION = "2.6";

	private Util(){
		throw new UnsupportedOperationException("This class is SkyLightQP Util class.");
	}

	public static void setItem(String display, Material id, int data, int amount, List<String> lore, int loc, Inventory inventory) {
		ItemStack icon = new ItemStack(id, amount, (short) data);
		ItemMeta iconmeta = icon.getItemMeta();
		iconmeta.setDisplayName(display);
		iconmeta.setLore(lore);
		icon.setItemMeta(iconmeta);
		inventory.setItem(loc, icon);
	}

	public static void setItem(String display, int id, int data, int amount, List<String> lore, int loc, Inventory inventory) {
		ItemStack icon = new MaterialData(id, (byte) data).toItemStack(amount);
		ItemMeta iconmeta = icon.getItemMeta();
		iconmeta.setDisplayName(display);
		iconmeta.setLore(lore);
		icon.setItemMeta(iconmeta);
		inventory.setItem(loc, icon);
	}

	public static void checkOnline() {
		if (!Bukkit.getOnlineMode()) {
			Bukkit.getConsoleSender().sendMessage("§6[!] §f이 서버는 오프라인 서버입니다.");
			Bukkit.getConsoleSender().sendMessage("§6[!] §f온라인으로 바꾸는 것을 권장드립니다.");
		}
	}

	public static void sendConsoleMessage(String s) throws NullPointerException{
		Bukkit.getConsoleSender().sendMessage("§6[!] §f" + s);
	}

	// positive : 양수만 받을 것인가?
	public static boolean isInteger(String s, boolean positive) {
		char[] chars = s.toCharArray();
		if (chars.length == 1) { return Character.isDigit(chars[0]); }
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if ((positive && c == '-') || (!Character.isDigit(c) && i != 0 && c != '+')) {
				return false;
			}
		}
		return true;
	}

	public static List<String> chatColor(List<String> list){
		List<String> strings = new ArrayList<>();
		for (String string : list) {
			strings.add(ChatColor.translateAlternateColorCodes('&', string));
		}
		return strings;
	}


	public static void setItem(String display, Material id, int data, int amount, List<String> lore, Map<Enchantment,Integer> enchants, int loc, Inventory inventory) {
		ItemStack icon = new ItemStack(id, amount, (short) data);
		ItemMeta iconmeta = icon.getItemMeta();
		iconmeta.setDisplayName(display);
		iconmeta.setLore(lore);
		for(Object enchantitem : enchants.keySet()){
			for(int level : enchants.values()) {
				Enchantment enchant = (Enchantment) enchantitem;
				iconmeta.addEnchant(enchant,level,false);
			}
		}
		icon.setItemMeta(iconmeta);
		inventory.setItem(loc, icon);
	}
}