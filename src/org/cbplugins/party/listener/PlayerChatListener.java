package org.cbplugins.party.listener;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import org.cbplugins.party.Party;
import org.cbplugins.party.PartyManager;
import org.cbplugins.party.PlayerParty;

public class PlayerChatListener implements Listener {
	
	@EventHandler
	public void onPlayerChat(ChatEvent e) {
		if(!(e.getSender() instanceof ProxiedPlayer)) {
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) e.getSender();
		if(PartyManager.getParty(player) != null) {
			PlayerParty party = PartyManager.getParty(player);
			if(e.getMessage().startsWith("@party")) {
				e.setCancelled(true);
				for(ProxiedPlayer p : party.getPlayers()) {
					if(p != player) {
						p.sendMessage(new TextComponent(Party.prefix + "§8[§aCHAT§8] §8"+player.getName()+"§7: §b" + e.getMessage().replace("@party", "")));
					}
				}
				if(party.getLeader() != player) {
					party.getLeader().sendMessage(new TextComponent(Party.prefix + "§8[§aCHAT§8] §8"+player.getName()+"§7: §b" + e.getMessage().replace("@party", "")));
				}
				player.sendMessage(new TextComponent(Party.prefix + "§8[§aCHAT§8] §8"+player.getName()+"§7: §b" + e.getMessage().replace("@party", "")));
			}
		}
	}

}
