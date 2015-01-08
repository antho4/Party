package org.cbplugins.party.command;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;

public class Create extends SubCommand {
	
	public Create() {
		super("Create a party", "", "create");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(PartyManager.getParty(p) != null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §calready §cin §ca §cparty."));
			return;
		}
		
		PartyManager.createParty(p);
		
		p.sendMessage(new TextComponent(Party.prefix + "§bYou §bcreated §ba §bnew §bparty."));
		return;
	}

}
