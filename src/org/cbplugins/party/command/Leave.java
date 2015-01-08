package org.cbplugins.party.command;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

public class Leave extends SubCommand {
	
	public Leave() {
		super("Leave your party", "", "leave");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(PartyManager.getParty(p) == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §cnot §cin §cany §cparty."));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(p);
		
		if(party.isLeader(p)) {
			for(ProxiedPlayer pp : party.getPlayers()) {
				pp.sendMessage(new TextComponent(Party.prefix + "§bThe §bLeader §bhas §bdissolved §bthe §bparty."));
			}
			p.sendMessage(new TextComponent(Party.prefix + "§bYou §bdissolved §byour §bparty."));
			PartyManager.deleteParty(party);
			return;
		}else {
			if(party.removePlayer(p)) {
				p.sendMessage(new TextComponent(Party.prefix + "§bYou §bleft §bthe §bparty."));
				for(ProxiedPlayer pp : party.getPlayers()) {
					pp.sendMessage(new TextComponent(Party.prefix + "§bThe §bplayer §6"+p.getName()+" §bhas §bleft §bthe §bparty."));
				}
				party.getLeader().sendMessage(new TextComponent(Party.prefix + "§bThe §bplayer §6"+p.getName()+" §bhas §bleft §bthe §bparty."));
				return;
			}else {
				p.sendMessage(new TextComponent(Party.prefix + "§bYou §bcouldn't §bleave §bthe §bparty."));
				return;
			}
		}
	}

}
