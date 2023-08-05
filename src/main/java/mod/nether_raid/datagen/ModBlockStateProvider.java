package mod.nether_raid.datagen;

import mod.nether_raid.Main;
import mod.nether_raid.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper exFileHelper) {
		super(packOutput, Main.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlockWithItem(ModBlocks.EXAMPLE.get(), cubeAll(ModBlocks.EXAMPLE.get()));
	}

}