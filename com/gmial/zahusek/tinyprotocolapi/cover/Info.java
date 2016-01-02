package com.gmail.zahusek.tinyprotocolapi.reflect;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.gmail.zahusek.tinyprotocol.Reflection;
import com.gmail.zahusek.tinyprotocol.Reflection.FieldAccessor;
import com.gmail.zahusek.tinyprotocol.Reflection.MethodInvoker;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;

public class Info extends ExpandReflect {
	
	private static final MethodInvoker handle = Reflection.getMethod("{obc}.entity.CraftPlayer", "getHandle");
	private static final FieldAccessor<GameProfile> gameprofile = Reflection.getField("{nms}.EntityPlayer", GameProfile.class, 0);
	private static final Object mcServer = Reflection.getField(Bukkit.getServer().getClass(), Reflection.getUntypedClass("{nms}.MinecraftServer"), 0).get(Bukkit.getServer());
	private static final MinecraftSessionService sessionService = Reflection.getField(mcServer.getClass(), MinecraftSessionService.class, 0).get(mcServer);
	private static final FieldAccessor<Object> usercache = Reflection.getField(mcServer.getClass(), Reflection.getUntypedClass("{nms}.UserCache"), 0);
	
	private String[] skin;
	public Info(String name, String displayname, int ping, InfoMode mode) {
		super(Type.NMS, "PacketPlayOutPlayerInfo$PlayerInfoData" , 0, null,
				new GameProfile(UUID.randomUUID(), name), ping, mode.getMode(), ChatSerializer.toIChat(displayname));
		this.skin = new String[]{"", ""};
	}
	public void setSkin(String name) {
		this.skin[1] = this.skin[0];
		this.skin[0] = name;
		((GameProfile)getParameter(2)).getProperties().replaceValues("textures", getSkinProperties(name));
	}
	public String[] getSkin() {
		return skin;
	}
	
	public void setPing(int ping) {
		setParameter(0, ping);
	}
	
	public void setDisplayname(String displayname) {
		setParameter(3, ChatSerializer.toIChat(displayname));
	}
	
	public Collection<Property> getSkinProperties(String playerName) {
        Player online = Bukkit.getPlayerExact(playerName);
        GameProfile profile;
        
        if (online != null) profile = gameprofile.get(handle.invoke(online));
        else {
			@SuppressWarnings("deprecation")
			OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
    		profile = Reflection.getField(player.getClass(), GameProfile.class, 0).get(player);
        }
        return sessionService(profile).getProperties().get("textures");
    }
	
    private GameProfile sessionService(GameProfile profile) {
    	Object cache = usercache.get(mcServer);
    	GameProfile getProfile = (GameProfile) Reflection.getMethod(cache.getClass(), "getProfile", String.class).invoke(cache, profile.getName());
    	
    	if (getProfile != null) {
            Property property = (Property) Iterables.getFirst(profile.getProperties().get("textures"), null);
            if (property == null) return sessionService.fillProfileProperties(profile, true);
    	}
		return getProfile;
    }
}
