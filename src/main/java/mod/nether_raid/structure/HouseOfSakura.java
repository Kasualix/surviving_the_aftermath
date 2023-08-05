package mod.nether_raid.structure;

import mod.nether_raid.Main;
import mod.nether_raid.init.ModStructurePieceTypes;
import mod.nether_raid.init.ModStructureTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class HouseOfSakura extends AbstractStructure {

	public HouseOfSakura(StructureSettings settings) {
		super(settings);
	}

	@Override
	public StructureType<?> type() {
		return ModStructureTypes.HOUSE_OF_SAKURA.get();
	}

	@Override
	public StructurePieceType pieceType() {
		return ModStructurePieceTypes.HOUSE_OF_SAKURA.get();
	}

	@Override
	public ResourceLocation location() {
		return Main.asResource("house_of_sakura");
	}

	public static class Piece extends AbstractStructure.Piece {

		public Piece(StructurePieceSerializationContext context, CompoundTag tag) {
			super(ModStructurePieceTypes.HOUSE_OF_SAKURA.get(), context.structureTemplateManager(), tag);
		}

	}

}