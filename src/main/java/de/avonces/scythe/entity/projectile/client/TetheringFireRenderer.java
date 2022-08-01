package de.avonces.scythe.entity.projectile.client;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.entity.projectile.custom.TetheringFireEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TetheringFireRenderer extends EntityRenderer<TetheringFireEntity> {
    public TetheringFireRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(TetheringFireEntity pEntity) {
        return new ResourceLocation(Scythe.MOD_ID, "textures/entity/tethering_fire/tethering_fire.png");
    }
}
