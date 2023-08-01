package mod.surviving_the_aftermath.capability;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;

import mod.surviving_the_aftermath.init.ModCapability;
import mod.surviving_the_aftermath.init.ModStructures;
import mod.surviving_the_aftermath.raid.NetherRaid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class RaidData implements INBTSerializable<CompoundTag> {
	public static final Codec<List<NetherRaid>> RAIDS_CODEC = Codec.list(NetherRaid.CODEC);

	private ServerLevel level;
	private List<NetherRaid> raids = new ArrayList<>();

	public RaidData(ServerLevel level) {
		this.level = level;
	}

	public void Create(BlockPos pos) {
		if (level.structureManager().getAllStructuresAt(pos).containsKey(level.registryAccess().registryOrThrow(Registries.STRUCTURE).get(ModStructures.NETHER_RAID)) && noRaidAt(pos)) {
			raids.add(new NetherRaid(pos, level));
		}
	}

	private boolean noRaidAt(BlockPos pos) {
		for (var raid : raids) {
			for (var p : raid.getSpawn()) {
				if (p.distSqr(pos) < 25)
					return false;
			}
		}
		return true;
	}

	public void tick() {
		for (int i = raids.size() - 1; i >= 0; i--) {
			raids.get(i).tick(level);
			if (raids.get(i).isDone()) {
				raids.remove(i);
			}
		}
	}

	public void joinRaid(Entity entity) {
		for (var raid : raids)
			raid.join(entity);
	}

	@Override
	public CompoundTag serializeNBT() {
		var tag = new CompoundTag();
		tag.put("raids", RAIDS_CODEC.encodeStart(NbtOps.INSTANCE, raids).getOrThrow(false, s -> {
		}));
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if (nbt.contains("raids"))
			RAIDS_CODEC.parse(NbtOps.INSTANCE, nbt.get("raids")).result()
					.ifPresentOrElse(r -> raids = new ArrayList<NetherRaid>(r), () -> {
					});
	}

	public static LazyOptional<RaidData> get(Level level) {
		return level.getCapability(ModCapability.RAID_DATA);
	}

	public static class Provider implements ICapabilitySerializable<CompoundTag> {

		private LazyOptional<RaidData> instance;

		public Provider(ServerLevel level) {
			instance = LazyOptional.of(() -> new RaidData(level));
		}

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return ModCapability.RAID_DATA.orEmpty(cap, instance);
		}

		@Override
		public CompoundTag serializeNBT() {
			return instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"))
					.serializeNBT();
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!"))
					.deserializeNBT(nbt);
		}

	}
}
