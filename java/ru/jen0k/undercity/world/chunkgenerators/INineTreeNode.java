package ru.jen0k.undercity.world.chunkgenerators;

import net.minecraft.block.Block;

public interface INineTreeNode
{
    Block[][][] GenerateRegion(int chunk_x, int chunk_z, int random_seed);
}
