package ru.jen0k.undercity.world.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.registries.BiomeRegistry;
import ru.jen0k.undercity.registries.DimensionRegistry;
import ru.jen0k.undercity.world.chunkgenerators.UnderCityCaveGenerator;

public class UnderCityDimension extends WorldProvider
{
    @Override
    protected void init()
    {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderSingle(BiomeRegistry.UNDERCITY_BIOME);
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
        return new UnderCityCaveGenerator(this.world);
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
