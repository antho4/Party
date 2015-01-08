package org.cbplugins.party.command;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

public class Kick extends SubCommand {
	
	public Kick() {
		super("Kick a player out of your party", "<Name>", "kick");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(new TextComponent(Party.prefix + "§cPlease §cgive §ca §cplayers §cname."));
			return;
		}
		
		if(PartyManager.getParty(p) == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §cnot §in §any §party."));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(p);
		
		if(!party.isLeader(p)) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §not §the §cparty §leader."));
			return;
		}
		
		ProxiedPlayer pl = BungeeCord.getInstance().getPlayer(args[0]);
		
		if(pl == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cThis §cplayer §isn't §conline."));
			return;
		}
		
		if(party.removePlayer(pl)) {
			p.sendMessage(new TextComponent(Party.prefix + "§bYou §bkicked §bthe §bplayer §6"+pl.getName()+" §bout §bof §byour §bparty."));
			for(ProxiedPlayer pp : party.getPlayers()) {
				pp.sendMessage(new TextComponent(Party.prefix + "§bThe §bplayer §6"+pl.getName()+ " §bwas §bkicked §bout §bof §byour §bparty."));
			}
			pl.sendMessage(new TextComponent(Party.prefix + "§bYou §bwere §bkicked §bout §bof §byour §bparty."));
			return;
		}else {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §ccouldn't §ckick §cthe §cplayer §cout §cof §cyour §cparty."));
			return;
		}
	}

}
