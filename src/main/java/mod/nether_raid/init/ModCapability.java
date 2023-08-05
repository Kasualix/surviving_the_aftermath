package mod.nether_raid.init;

import mod.nether_raid.Main;
import mod.nether_raid.capability.RaidData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModCapability {

    public static final Capability<RaidData> RAID_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(RaidData.class);
    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Level> event) {
        if (event.getObject().dimension() == Level.OVERWORLD && event.getObject() instanceof ServerLevel level) {
            event.addCapability(Main.asResource("raiddata"), new RaidData.Provider(level));
        }
    }

}