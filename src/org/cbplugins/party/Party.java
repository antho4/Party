package org.cbplugins.party;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

import org.cbplugins.party.command.PartyCommand;
import org.cbplugins.party.listener.PlayerChatListener;
import org.cbplugins.party.listener.PlayerDisconnectListener;
import org.cbplugins.party.listener.ServerSwitchListener;

public class Party extends Plugin {

	public static String prefix = "§8[§5§lPARTY§8] §r";
	
	private static Party instance;
	
	@Override
	public void onEnable() {
		instance = this;
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new PartyCommand());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new PlayerChatListener());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new PlayerDisconnectListener());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ServerSwitchListener());
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Party getInstance() {
		return instance;
	}
	
}
