package com.gmail.zahusek.tinyprotocolapi.apis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;

import com.gmail.zahusek.tinyprotocolapi.cover.Info;
import com.gmail.zahusek.tinyprotocolapi.cover.InfoAction;
import com.gmail.zahusek.tinyprotocolapi.cover.InfoMode;
import com.gmail.zahusek.tinyprotocolapi.wrapper.Cells;
import com.gmail.zahusek.tinyprotocolapi.wrapper.ExpandPacket;
import com.gmail.zahusek.tinyprotocolapi.wrapper.HnF;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TabData {
	
	private static final String EMPTY = ChatColor.translateAlternateColorCodes('&', "&r");
	
	private Info[][] cell = new Info[4][20];
	private HnF hnf = new HnF("", "");
	
	public Info[][] getInfo() {
		return cell;
	}
	
	public HnF getHnF() {
		return hnf;
	}
	
	public void setCell(int x, int y, String message, int ping) {
		cell[x][y].setDisplayname(message);
		cell[x][y].setPing(ping);
	}
	
	public void setSkin(int x, int y, String skin) {
		cell[x][y].setSkin(skin);
	}
	
	public void setHeader(String header) {
		hnf.setHeader(header);
	}
	
	public void setHeader(List<String> header) {
		if(header == null) header = Lists.newArrayList("");
		String head = header.iterator().next() + header.stream().skip(1).map((msg) -> "\n" + msg).collect(Collectors.joining());
		setHeader(head);
	}
	
	public void setFooter(String footer) {
		hnf.setFooter(footer);
	}
	
	public void setFooter(List<String> footer) {
		if(footer == null) footer = Lists.newArrayList("");
		String foot = footer.iterator().next() + footer.stream().skip(1).map((msg) -> "\n" + msg).collect(Collectors.joining());
		setFooter(foot);
	}
	
	public void setHnF(String header, String footer) {
		hnf.setHeader(header);
		hnf.setFooter(footer);
	}
	
	public void setHnf(List<String> header, List<String> footer) {
		if(header == null) header = Lists.newArrayList("");
		if(footer == null) footer = Lists.newArrayList("");
		
		String head = header.iterator().next() + header.stream().skip(1).map((msg) -> "\n" + msg).collect(Collectors.joining());
		String foot = footer.iterator().next() + footer.stream().skip(1).map((msg) -> "\n" + msg).collect(Collectors.joining());
		
		setHnF(head, foot);
	}
	
	protected Iterable<ExpandPacket> getDefault() {
		Cells add = new Cells(InfoAction.ADD);
		Cells displayname = new Cells(InfoAction.DISPLAYNAME);
		Cells ping = new Cells(InfoAction.PING);
		
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 20; y++) {
				if (cell[x][y] == null) {
					Info refresh = new Info(digit(x) + digit(y), EMPTY, 0, InfoMode.NOT_SET);
					refresh.setSkin("MHF_Question");
					add.add(cell[x][y] = refresh);
				}
				cell[x][y].setDisplayname(EMPTY);
				displayname.add(cell[x][y]);
				cell[x][y].setPing(0);
				ping.add(cell[x][y]);
			}
		}
		return Iterables.concat(getRemove(), Arrays.asList(add, displayname, hnf = new HnF("", "")));
	}
	
	protected List<ExpandPacket> getRefresh() {
		Cells skin = new Cells(InfoAction.ADD);
		Cells displayname = new Cells(InfoAction.DISPLAYNAME);
		Cells ping = new Cells(InfoAction.PING);
		
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 20; y++) {
				if(!cell[x][y].getSkin()[0].contains(cell[x][y].getSkin()[1])) {
					skin.add(cell[x][y]);
				}
				displayname.add(cell[x][y]);
				ping.add(cell[x][y]);
			}
		}
		return Arrays.asList(skin, ping, displayname, hnf);
	}
	
	protected List<ExpandPacket> getRemove() {
		Cells remove = new Cells(InfoAction.REMOVE);
		
		for(int x = 0; x < 4; x++) 
			for(int y = 0; y < 20; y++)
				if(cell[x][y] != null)
					remove.add(cell[x][y]);
		
		return Arrays.asList(remove, new HnF("", ""));
	}
	
	private String digit(int i) {
		return i > 9 ? "000000" + i : "0000000" + i;
	}
}
