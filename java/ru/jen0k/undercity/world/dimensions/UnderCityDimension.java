package ru.jen0k.undercity.world.dimensions;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import ru.jen0k.undercity.registries.DimensionRegistry;

public class UnderCityDimension extends WorldProvider
{
    public UnderCityDimension()
    {
        this.biomeProvider = new BiomeProviderSingle(Biomes.PLAINS);
    }

    @Override
    public DimensionType getDimensionType()
    {
        return DimensionRegistry.UNDERCITY;
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new ChunkGeneratorHell(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
}
