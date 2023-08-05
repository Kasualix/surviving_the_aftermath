package mod.nether_raid.datagen;

import mod.nether_raid.Main;
import mod.nether_raid.init.ModBiomes;
import mod.nether_raid.init.ModStructureSets;
import mod.nether_raid.init.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);

    public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of(Main.MODID));
    }

}