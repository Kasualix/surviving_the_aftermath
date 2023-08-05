package mod.nether_raid.datagen;

import mod.nether_raid.Main;
import mod.nether_raid.init.ModSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class ModSoundProvider extends SoundDefinitionsProvider {

	protected ModSoundProvider(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, Main.MODID, helper);
	}

	@Override
	public void registerSounds() {
		addWithSameName(ModSoundEvents.ORCHELIAS_VOX.get(), true);
	}

	private void addWithSameName(SoundEvent sound, boolean stream) {
		add(sound, definition().with(sound(sound.getLocation()).stream(stream)));
	}
}