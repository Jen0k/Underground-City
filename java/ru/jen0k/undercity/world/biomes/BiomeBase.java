package ru.jen0k.undercity.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.registries.BiomeRegistry;

public class BiomeBase extends Biome {
    public BiomeBase(BiomeProperties biomeProps, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types)
    {
        super(biomeProps);

        this.biomeType = biomeType;
        this.types = types;

        setRegistryName(UnderCity.UNDERCITY_BIOME_NAME);
        BiomeRegistry.BIOMES.add(this);
    }

    public final BiomeManager.BiomeType biomeType;
    public final BiomeDictionary.Type[] types;
}
