package com.gmail.zahusek.tinyprotocolapi.tabapi;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

import com.gmail.zahusek.tinyprotocolapi.reflect.Info;
import com.gmail.zahusek.tinyprotocolapi.reflect.InfoAction;
import com.gmail.zahusek.tinyprotocolapi.reflect.InfoMode;
import com.gmail.zahusek.tinyprotocolapi.wrapper.Cells;
import com.gmail.zahusek.tinyprotocolapi.wrapper.ExpandPacket;
import com.gmail.zahusek.tinyprotocolapi.wrapper.HnF;

public class TabData {
	
	private static final String EMPTY = ChatColor.translateAlternateColorCodes('&', "&r");
	
	private Info[][] cell = new Info[4][20];
	private HnF hnf = new HnF("", "");
	
	public void setCell(int x, int y, String message, int ping) {
		try {
			cell[x][y].setDisplayname(message);
			cell[x][y].setPing(ping);
		}
		catch (Exception e) {
			new ArrayIndexOutOfBoundsException("maxSize; x0-3 y0-19)");
		}
	}
	
	public void setSkin(int x, int y, String skin) {
		try {
			cell[x][y].setSkin(skin);
		}
		catch (Exception e) {
			new ArrayIndexOutOfBoundsException("maxSize; x0-3 y0-19)");
		}
	}
	
	public void setHnF(String header, String footer) {
		hnf = new HnF(header, footer);
	}
	
	protected List<ExpandPacket> getDefault() {
		Cells add = new Cells(InfoAction.ADD);
		Cells displayname = new Cells(InfoAction.DISPLAYNAME);
		Cells ping = new Cells(InfoAction.PING);
		
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 20; y++) {
				if (cell[x][y] == null) {
					Info def = new Info(digit(x) + digit(y), EMPTY, 0, InfoMode.NOT_SET);
					def.setSkin("MHF_Question");
					add.add(cell[x][y] = def);
				}
				cell[x][y].setDisplayname(EMPTY);
				displayname.add(cell[x][y]);
				cell[x][y].setPing(0);
				ping.add(cell[x][y]);
			}
		}
		return Arrays.asList(add, displayname, hnf = new HnF("", ""));
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
			remove.add(cell[x][y]);
		
		return Arrays.asList(remove, new HnF("", ""));
	}
	
	private String digit(int i) {
		return i > 9 ? "000000" + i : "0000000" + i;
	}
}
