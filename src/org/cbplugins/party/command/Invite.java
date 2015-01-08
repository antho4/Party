package org.cbplugins.party.command;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Invite extends SubCommand {
	
	public Invite() {
		super("Invite a player in your party", "<Name>", "invite");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(new TextComponent(Party.prefix + "§cPlease §cgive §ca §cplayers §cname."));
			return;
		}
		
		if(PartyManager.getParty(p) == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §cnot §cin §cany §cparty."));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(p);
		
		if(!party.isLeader(p)) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §cnot §cthe §cparty §cleader."));
			return;
		}
		
		ProxiedPlayer pl = BungeeCord.getInstance().getPlayer(args[0]);
		
		if(pl == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cThis §cplayer §cisn't §conline."));
			return;
		}
		
		party.invite(pl);
		
		p.sendMessage(new TextComponent(Party.prefix + "§bYou §binvited"+pl.getName()+" §bto §byour §bparty."));
	}

}
