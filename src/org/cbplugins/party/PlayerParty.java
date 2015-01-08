package org.cbplugins.party;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerParty {

	private ProxiedPlayer leader;
	
	private List<ProxiedPlayer> players;
	
	private List<ProxiedPlayer> invitations;
	
	public PlayerParty(ProxiedPlayer leader) {
		this.leader = leader;
		this.players = new ArrayList<ProxiedPlayer>();
		this.invitations = new ArrayList<ProxiedPlayer>();
	}
	
	public boolean isLeader(ProxiedPlayer player) {
		return this.leader == player;
	}
	
	public List<ProxiedPlayer> getPlayers() {
		return players;
	}
	
	public ProxiedPlayer getLeader() {
		return leader;
	}
	
	public boolean isInParty(ProxiedPlayer player) {
		if(isLeader(player)) {
			return true;
		}else if(players.contains(player)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean addPlayer(ProxiedPlayer player) {
		if(!players.contains(player) && invitations.contains(player)) {
			players.add(player);
			invitations.remove(player);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removePlayer(ProxiedPlayer player) {
		if(players.contains(player)) {
			players.remove(player);
			return true;
		}else {
			return false;
		}
	}
	
	public ServerInfo getServer() {
		return leader.getServer().getInfo();
	}
	
	public void invite(final ProxiedPlayer p) {
		invitations.add(p);
		p.sendMessage(new TextComponent(Party.prefix + "�bYou are invited in �6"+leader.getName()+"'s �bparty."));
		p.sendMessage(new TextComponent(Party.prefix + "�bJoin �bthe �bparty �bwith �8/�5party �5join �6"+leader.getName()));
		BungeeCord.getInstance().getScheduler().schedule(Party.getInstance(), new Runnable() {
			public void run() {
				if(invitations.contains(p)) {
					invitations.remove(p);
					p.sendMessage(new TextComponent(Party.prefix + "�bYour invitations has been expired."));
					leader.sendMessage(new TextComponent(Party.prefix + "�bThe invitation to �6"+p.getName()+" �bhas been expired."));
				}
			}
		}, 30L, TimeUnit.SECONDS);
	}
}
