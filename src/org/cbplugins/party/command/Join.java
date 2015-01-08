package org.cbplugins.party.command;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

public class Join extends SubCommand {
	
	public Join() {
		super("Join a party", "<Name>", "join");
	}
	
	public void onCommand(ProxiedPlayer p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(new TextComponent(Party.prefix + "§cPlease §cgive §ca §cplayers §cname."));
			return;
		}
		
		if(PartyManager.getParty(p) != null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §care §calready §cin §ca §cparty. §cUse §7/§5party §5leave §cto §cleave §cthe §cparty."));
			return;
		}
		
		ProxiedPlayer pl = BungeeCord.getInstance().getPlayer(args[0]);
		
		if(pl == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cThis §cplayer §cisn't §conline."));
			return;
		}
		
		if(PartyManager.getParty(pl) == null) {
			p.sendMessage(new TextComponent(Party.prefix + "§cThis §cplayer §cdoesn't §chave §ca §cparty."));
			return;
		}
		
		PlayerParty party = PartyManager.getParty(pl);
		
		if(party.addPlayer(p)) {
			for(ProxiedPlayer pp : party.getPlayers()) {
				pp.sendMessage(new TextComponent(Party.prefix + "§bThe §bplayer §6"+p.getName()+" §bjoined §bthe §bparty."));
			}
			party.getLeader().sendMessage(new TextComponent(Party.prefix + "§bThe §bplayer §6"+p.getName()+" §bjoined §bthe §bparty."));
		}else {
			p.sendMessage(new TextComponent(Party.prefix + "§cYou §ccouldn't §cjoin §cthe §cparty."));
			return;
		}
 	}

}
