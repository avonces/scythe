package de.avonces.scythe.event;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.entity.ModEntityTypes;
import de.avonces.scythe.entity.projectile.client.BloodStreamRenderer;
import de.avonces.scythe.entity.projectile.client.TetheringFireRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Scythe.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.BLOOD_STREAM_ENTITY.get(), BloodStreamRenderer::new);
        EntityRenderers.register(ModEntityTypes.TETHERING_FIRE_ENTITY.get(), TetheringFireRenderer::new);
    }
}
