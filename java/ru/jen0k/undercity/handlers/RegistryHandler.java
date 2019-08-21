package ru.jen0k.undercity.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.jen0k.undercity.helpers.IHasModel;
import ru.jen0k.undercity.registries.BiomeRegistry;
import ru.jen0k.undercity.registries.BlockRegistry;
import ru.jen0k.undercity.registries.ItemRegistry;
import ru.jen0k.undercity.world.biomes.BiomeBase;

@Mod.EventBusSubscriber
public class RegistryHandler
{
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemRegistry.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockRegistry.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public  static void onBiomeRegister(RegistryEvent.Register<Biome> event)
    {
        event.getRegistry().registerAll(BiomeRegistry.BIOMES.toArray(new Biome[0]));

        for (BiomeBase biome : BiomeRegistry.BIOMES)
        {
            event.getRegistry().register(biome);
            BiomeDictionary.addTypes(biome, biome.types);
            BiomeManager.addBiome(biome.biomeType, new BiomeManager.BiomeEntry(biome, 10));
            BiomeManager.addSpawnBiome(biome);
        }
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for (Item item : ItemRegistry.ITEMS)
        {
            if (item instanceof IHasModel)
            {
                ((IHasModel) item).reristerModels();
            }
        }

        for (Block block : BlockRegistry.BLOCKS)
        {
            if (block instanceof IHasModel)
            {
                ((IHasModel) block).reristerModels();
            }
        }
    }
}
