package ru.jen0k.undercity.world.biomes;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import ru.jen0k.undercity.UnderCity;

public class UnderCityBiome extends BiomeBase
{
    public UnderCityBiome()
    {
        super(new BiomeProperties(UnderCity.UNDERCITY_BIOME_NAME),
                BiomeManager.BiomeType.COOL,
                BiomeDictionary.Type.DEAD );
    }
}
