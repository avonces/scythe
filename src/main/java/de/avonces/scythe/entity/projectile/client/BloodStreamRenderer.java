package de.avonces.scythe.entity.projectile.client;

import de.avonces.scythe.Scythe;
import de.avonces.scythe.entity.projectile.custom.BloodStreamEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BloodStreamRenderer extends EntityRenderer<BloodStreamEntity> {
    public BloodStreamRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(BloodStreamEntity pEntity) {
        return new ResourceLocation(Scythe.MOD_ID, "textures/entity/blood_stream/blood_stream.png");
    }
}
