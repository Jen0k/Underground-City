package ru.jen0k.undercity.world.chunkgenerators;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class UnderCityGenerator implements IChunkGenerator
{
    public UnderCityGenerator(World world)
    {
        this.world = world;
        this.rand = new Random(world.getSeed());
    }

    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();

    private final World world;
    private final Random rand;


    @Override
    public Chunk generateChunk(int chunk_x, int chunk_z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        for (int x = 0; x < 16; x++)
        {
            for (int z = 0; z < 16; z++)
            {
                chunkprimer.setBlockState(x, 0, z, BEDROCK);
                chunkprimer.setBlockState(x, 1, z, GRASS);
            }
        }

        Chunk newChunk = new Chunk(this.world, chunkprimer, chunk_x, chunk_z);
        Biome[] biomesForNewChunk = this.world.getBiomeProvider().getBiomes((Biome[])null, chunk_x * 16, chunk_z * 16, 16, 16);
        byte[] newChunkBiomeBytes = newChunk.getBiomeArray();

        for (int b = 0; b < newChunkBiomeBytes.length; b++)
        {
            newChunkBiomeBytes[b] = (byte)Biome.getIdForBiome(biomesForNewChunk[b]);
        }

        newChunk.generateSkylightMap();

        return newChunk;
    }

    @Override
    public void populate(int chunk_x, int chunk_z)
    {
        int block_x = chunk_x * 16;
        int block_z = chunk_z * 16;

        BlockPos blockPosition = new BlockPos(block_x, 0, block_z);

        Biome chunkBiome = this.world.getBiome(blockPosition);

        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)chunk_x * k + (long)chunk_z * l ^ this.world.getSeed());

        chunkBiome.decorate(this.world, this.rand, blockPosition);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
