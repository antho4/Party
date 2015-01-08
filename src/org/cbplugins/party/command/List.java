package org.cbplugins.party.command;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

public class List extends SubCommand {
	
	public List() {
		super("List all players in your party", "", "list");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(PartyManager.getParty(p) == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §cnot §cin §cany §cparty."));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(p);
		
		String leader = "§3Leader§7: §5"+party.getLeader().getName();
		String players = "§8Players§7: §b";
		
		if(!party.getPlayers().isEmpty()) {
			for(ProxiedPlayer pp : party.getPlayers()) {
				players += pp.getName() + "§7, §b";
			}
			players = players.substring(0, players.lastIndexOf("§7, §b"));
		}else {
			players += "Empty";
		}
		
		
		p.sendMessage(new TextComponent("§8§m----------[§5§lPARTY§8]§m----------"));
		
		p.sendMessage(new TextComponent(Party.prefix + leader));
		p.sendMessage(new TextComponent(Party.prefix + players));
		
		p.sendMessage(new TextComponent("§8§m-----------------------------------"));
		return;
	}

}
