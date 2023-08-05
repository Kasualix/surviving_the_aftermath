package mod.nether_raid.init;

import mod.nether_raid.Main;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMobEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MODID);

    //懦弱
    public static final RegistryObject<MobEffect> COWARDICE = MOB_EFFECTS.register("cowardice", () -> new MobEffect(MobEffectCategory.HARMFUL, 0xFFFFFF));

}