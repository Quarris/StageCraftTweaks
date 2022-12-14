package com.bisectstudios.mods.stagecraft;

import com.bisectstudios.mods.stagecraft.compat.BloodMagicCompat;
import com.bisectstudios.mods.stagecraft.configs.ModConfigs;
import com.bisectstudios.mods.stagecraft.recipes.ingredients.WeakNBTIngredient;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModRef.ID)
public class ModRoot {

    public ModRoot() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.register(new ForgeConfigSpec.Builder()));

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(EventPriority.LOWEST, this::onLoadCompleted);
        modBus.register(this);

        ModContent.init();
    }

    private void onLoadCompleted(FMLLoadCompleteEvent event) {
        BloodMagicCompat.onLoadCompleted();
    }

    @SubscribeEvent //ModBus, can't use addListener due to nested genetics.
    public void registerRecipeSerialziers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(ModRef.res("weak_nbt"), WeakNBTIngredient.Serializer.INSTANCE);

    }
}
