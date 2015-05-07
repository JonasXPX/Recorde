package me.jonasxpx.recorde;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Recorde extends JavaPlugin implements Listener{
	
	private File file = Paths.get("record.dat").toFile();
	private FileConfiguration s = YamlConfiguration.loadConfiguration(file);
	
	private int total;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		s.options().copyDefaults(true);
		this.total = s.getInt("recorde");
	}
	@Override
	public void onDisable() {
		s.set("recorde", total);
		try {
			s.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@EventHandler
	public void join(PlayerJoinEvent e){
		if(Bukkit.getOnlinePlayers().length > total){
			this.total = Bukkit.getOnlinePlayers().length;
			System.out.println("NOVO RECORD DE JOGADORES ONLINE: "+total);
		}
		e.getPlayer().sendMessage("§7Recode de jogadores online é de "+total);
	}
	
}
