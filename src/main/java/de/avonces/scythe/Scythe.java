package de.avonces.scythe;

import com.mojang.logging.LogUtils;
import de.avonces.scythe.block.ModBlocks;
import de.avonces.scythe.block.entity.ModBlockEntities;
import de.avonces.scythe.entity.ModEntityTypes;
import de.avonces.scythe.item.ModItems;
import de.avonces.scythe.recipe.ModRecipeSerializers;
import de.avonces.scythe.recipe.ModRecipeTypes;
import de.avonces.scythe.screen.ModMenuTypes;
import de.avonces.scythe.screen.ScytheTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Scythe.MOD_ID)
public class Scythe {
    public static final String MOD_ID = "scythe";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Scythe() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipeTypes.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        modEventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.SCYTHE_TABLE_MENU.get(), ScytheTableScreen::new);
    }
}
