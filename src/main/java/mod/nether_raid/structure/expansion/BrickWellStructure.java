package mod.nether_raid.structure.expansion;

import mod.nether_raid.Main;
import mod.nether_raid.init.ModStructurePieceTypes;
import mod.nether_raid.init.ModStructureTypes;
import mod.nether_raid.structure.AbstractStructure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class BrickWellStructure extends AbstractStructure {

    public BrickWellStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.BRICK_WELL.get();
    }

    @Override
    public StructurePieceType pieceType() {
        return ModStructurePieceTypes.BRICK_WELL.get();
    }

    @Override
    public ResourceLocation location() {
        return Main.asResource("brick_well");
    }

    public static class Piece extends AbstractStructure.Piece {

        public Piece(StructurePieceSerializationContext context, CompoundTag tag) {
            super(ModStructurePieceTypes.BRICK_WELL.get(), context, tag);
        }

    }

}