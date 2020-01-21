package ru.jen0k.undercity.world.worldtypes;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import ru.jen0k.undercity.registries.BiomeRegistry;
import ru.jen0k.undercity.world.chunkgenerators.UnderCityCaveGenerator;

public class UnderCityWorldType extends WorldType
{
    public UnderCityWorldType()
    {
        super("test");
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderSingle(BiomeRegistry.UNDERCITY_BIOME);
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new UnderCityCaveGenerator(world);
    }
}
