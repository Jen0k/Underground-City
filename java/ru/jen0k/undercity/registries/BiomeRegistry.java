package ru.jen0k.undercity.registries;

import ru.jen0k.undercity.world.biomes.BiomeBase;
import ru.jen0k.undercity.world.biomes.UnderCityBiome;

import java.util.ArrayList;
import java.util.List;

public class BiomeRegistry
{
    public static final List<BiomeBase> BIOMES = new ArrayList<BiomeBase>();

    public static final BiomeBase UNDERCITY_BIOME = new UnderCityBiome();
}
