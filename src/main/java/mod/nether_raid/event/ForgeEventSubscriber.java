package mod.nether_raid.event;

import mod.nether_raid.Main;
import mod.nether_raid.capability.RaidData;
import mod.nether_raid.init.ModStructures;
import mod.nether_raid.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent.CreateSpawnPosition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MODID, bus = Bus.FORGE)
public class ForgeEventSubscriber {

	@SubscribeEvent
	public static void netherRaid(EntityTravelToDimensionEvent event) {
		Entity entity = event.getEntity();
		Level level = entity.level();
		BlockPos pos = entity.blockPosition();
		if (level instanceof ServerLevel serverLevel){
			event.setCanceled(serverLevel.structureManager().getAllStructuresAt(pos).containsKey(level.registryAccess().registryOrThrow(Registries.STRUCTURE).get(ModStructures.NETHER_RAID)));
		}
	}

	@SubscribeEvent
	public static void onBlock(BlockEvent.PortalSpawnEvent event) {
		new Thread(() -> RaidData.get((Level) event.getLevel()).ifPresent(data -> data.Create(event.getPos()))).start();
	}

	@SubscribeEvent
	public static void tickRaid(LevelTickEvent event) {
		RaidData.get(event.level).ifPresent(RaidData::tick);
	}

	@SubscribeEvent
	public static void joinRaid(EntityJoinLevelEvent event) {
		RaidData.get(event.getLevel()).ifPresent(c -> c.joinRaid(event.getEntity()));
	}

	@SubscribeEvent
	public static void changeSpawn(CreateSpawnPosition event) {
		if (event.getLevel() instanceof ServerLevel level) {
			ServerLevelData settings = event.getSettings();
			BlockPos pos = level.findNearestMapStructure(ModTags.NETHER_RAID,
					new BlockPos(settings.getXSpawn(), settings.getYSpawn(), settings.getZSpawn()), 100, false);
			if (pos != null) {
				settings.setSpawn(new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ()), 0);
				event.setCanceled(true);
			}
		}
	}

}