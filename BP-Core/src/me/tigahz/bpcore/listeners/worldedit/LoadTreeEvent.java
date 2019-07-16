package me.tigahz.bpcore.listeners.worldedit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;

import me.tigahz.bpcore.Main;
import me.tigahz.bpcore.util.Convert;

public class LoadTreeEvent {
	
	public void loadTree(Player p, String type) throws WorldEditException {
		
		final File[] files = new File(Main.getInstance().getDataFolder() + File.separator + "schematics" + File.separator + "tree" + File.separator + type + File.separator).listFiles();
		Random random = new Random();
		File f = files[random.nextInt(files.length)];
		
		ClipboardFormat cf = ClipboardFormats.findByFile(f);
		try (ClipboardReader cr = cf.getReader(new FileInputStream(f))) {
			
			Clipboard c = cr.read();
			
			try (EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(p.getLocation().getWorld()), -1)) {

				org.bukkit.Location l = p.getLocation();

				AffineTransform af = new AffineTransform();
				double y = Double.valueOf(Convert.getCardinalDirection(p));
				af = af.rotateY(y * 90);
				
				ClipboardHolder holder = new ClipboardHolder(c);
				holder.setTransform(holder.getTransform().combine(af));
				Operation o = holder.createPaste(es).to(BlockVector3.at(l.getX(), l.getY(), l.getZ())).ignoreAirBlocks(true).build();
				
				Operations.complete(o);

				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}