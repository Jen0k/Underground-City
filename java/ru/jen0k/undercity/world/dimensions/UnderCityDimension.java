package ru.jen0k.undercity.world.dimensions;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.registries.DimensionRegistry;
import ru.jen0k.undercity.world.chunkgenerators.UnderCityGenerator;

public class UnderCityDimension extends WorldProvider
{
    public UnderCityDimension()
    {
        this.biomeProvider = new BiomeProviderSingle(Biomes.PLAINS);
    }

    @Override
    public DimensionType getDimensionType()
    {
        if (DimensionManager.isDimensionRegistered(UnderCity.UNDERCITY_DIMMENSION_ID))
        {
            return DimensionRegistry.UNDERCITY;
        }
        return null;
    }

    @Override
    public IChunkGenerator createChunkGenerator()
    {
        return new UnderCityGenerator(this.world, this.world.getSeed());
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
