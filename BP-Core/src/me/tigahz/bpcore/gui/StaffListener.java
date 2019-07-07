package me.tigahz.bpcore.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.tigahz.bpcore.util.Items;
import me.tigahz.bpcore.util.Ref;

public class StaffListener implements Listener {  
	
	String menuName = Ref.format("&9&lStaff Menu");
	
	@EventHandler
	public void onStaffMenu(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getName().equalsIgnoreCase(menuName)) {
			
			if (e.getClickedInventory() == null) {
				e.setCancelled(true);
			} else if (!e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else if (e.getCurrentItem() == null) {
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lGo Back")) {
				MainMenu.createMenu(p);
				e.setCancelled(true);
			} else if (Items.getClickedItem(e, "&c&lExit Menu")) {
				e.setCancelled(true);
				p.closeInventory();
			} else {
				e.setCancelled(true);
			}
			
		} else {
			return;
		}
		
	}

}
