package com.gmail.zahusek.tinyprotocol;

public enum Packet {
	
	//PacketLoginInEncryptionBegin,
	//PacketLoginInStart,
	
	PacketLoginOutDisconnect,
	PacketLoginOutEncryptionBegin,
	PacketLoginOutSetCompression,
	PacketLoginOutSuccess,
	
	PacketPlayInAbilities,
	PacketPlayInArmAnimation,
	PacketPlayInBlockDig,
	PacketPlayInBlockPlace,
	PacketPlayInChat,
	PacketPlayInClientCommand,
	PacketPlayInCloseWindow,
	PacketPlayInCustomPayload,
	PacketPlayInEnchantItem,
	PacketPlayInEntityAction,
	PacketPlayInFlying,
	PacketPlayInHeldItemSlot,
	PacketPlayInKeepAlive,
	PacketPlayInResourcePackStatus,
	PacketPlayInSetCreativeSlot,
	PacketPlayInSettings,
	PacketPlayInSpectate,
	PacketPlayInSteerVehicle,
	PacketPlayInTabComplete,
	PacketPlayInTransaction,
	PacketPlayInUpdateSign,
	PacketPlayInUseEntity,
	PacketPlayInWindowClick,
	
	PacketPlayOutAbilities,
	PacketPlayOutAnimation,
	PacketPlayOutAttachEntity,
	PacketPlayOutBed,
	PacketPlayOutBlockAction,
	PacketPlayOutBlockBreakAnimation,
	PacketPlayOutBlockChange,
	PacketPlayOutCamera,
	PacketPlayOutChat,
	PacketPlayOutCloseWindow,
	PacketPlayOutCollect,
	PacketPlayOutCombatEvent,
	PacketPlayOutCustomPayload,
	PacketPlayOutEntity,
	PacketPlayOutEntityDestroy,
	PacketPlayOutEntityEffect,
	PacketPlayOutEntityEquipment,
	PacketPlayOutEntityHeadRotation,
	PacketPlayOutEntityMetadata,
	PacketPlayOutEntityStatus,
	PacketPlayOutEntityTeleport,
	PacketPlayOutEntityVelocity,
	PacketPlayOutExperience,
	PacketPlayOutExplosion,
	PacketPlayOutGameStateChange,
	PacketPlayOutHeldItemSlot,
	PacketPlayOutKeepAlive,
	PacketPlayOutKickDisconnect,
	PacketPlayOutLogin,
	PacketPlayOutMap,
	PacketPlayOutMapChunk,
	PacketPlayOutMapChunkBulk,
	PacketPlayOutMultiBlockChange,
	PacketPlayOutNamedEntitySpawn,
	PacketPlayOutNamedSoundEffect,
	PacketPlayOutOpenSignEditor,
	PacketPlayOutOpenWindow,
	PacketPlayOutPlayerInfo,
	PacketPlayOutPlayerListHeaderFooter,
	PacketPlayOutPosition,
	PacketPlayOutRemoveEntityEffect,
	PacketPlayOutResourcePackSend,
	PacketPlayOutRespawn,
	PacketPlayOutScoreboardDisplayObjective,
	PacketPlayOutScoreboardObjective,
	PacketPlayOutScoreboardScore,
	PacketPlayOutScoreboardTeam,
	PacketPlayOutServerDifficulty,
	PacketPlayOutSetCompression,
	PacketPlayOutSetSlot,
	PacketPlayOutSpawnEntity,
	PacketPlayOutSpawnEntityExperienceOrb,
	PacketPlayOutSpawnEntityLiving,
	PacketPlayOutSpawnEntityPainting,
	PacketPlayOutSpawnEntityWeather,
	PacketPlayOutSpawnPosition,
	PacketPlayOutStatistic,
	PacketPlayOutTabComplete,
	PacketPlayOutTileEntityData,
	PacketPlayOutTitle,
	PacketPlayOutTransaction,
	PacketPlayOutUpdateAttributes,
	PacketPlayOutUpdateEntityNBT,
	PacketPlayOutUpdateHealth,
	PacketPlayOutUpdateSign,
	PacketPlayOutUpdateTime,
	PacketPlayOutWindowData,
	PacketPlayOutWindowItems,
	PacketPlayOutWorldBorder,
	PacketPlayOutWorldEvent,
	PacketPlayOutWorldParticles,
	
	PacketStatusInPing,
	PacketStatusInStart,
	
	PacketStatusOutPong,
	PacketStatusOutServerInfo;
	
	public boolean equalsClient() {
		return name().startsWith("PacketPlayIn") ||
				name().startsWith("PacketStatusIn");
	}

	public boolean equalsServer() {
		return name().startsWith("PacketPlayOut") ||
				name().startsWith("PacketStatusOut");
	}
	public Sender getSender(){
		return equalsClient() ? Sender.CLIENT : Sender.SERVER ;
	}
	
	public enum Sender {
		CLIENT, SERVER;
	}
}
