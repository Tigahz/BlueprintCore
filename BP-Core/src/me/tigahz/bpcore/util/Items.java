package me.tigahz.bpcore.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class Items {

	@SuppressWarnings("deprecation")
	static ItemStack createLoredItem(Material material, String name, ArrayList<String> arrayList) {
	
        ItemStack itemStack = new Wool(material).toItemStack(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        
        ArrayList<String> lorelist = new ArrayList<>();
        lorelist.addAll(arrayList);
        itemMeta.setLore(lorelist);
        
        itemMeta.setDisplayName(Ref.format(name));
        itemStack.setItemMeta(itemMeta);

        return itemStack;

    }
	
	@SuppressWarnings("deprecation")
	static ItemStack createItem(Material material, String name) {
	
        ItemStack itemStack = new Wool(material).toItemStack(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
  
        itemMeta.setDisplayName(Ref.format(name));
        itemStack.setItemMeta(itemMeta);

        return itemStack;

    }

    public static boolean getClickedItem(InventoryClickEvent e, String name) {
        return e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Ref.format(name));
    }
    
    public static void createItem(Inventory i, ItemStack itemStack, Material material, int integer, String name) {
        itemStack = createItem(material, name);
        i.setItem(integer, itemStack);
    }
    
    public static void createLoredItem(Inventory i, ItemStack itemStack, Material material, int integer, String name, ArrayList<String> lore) {
        itemStack = createLoredItem(material, name, lore);
        i.setItem(integer, itemStack);
    }
	
	
}
