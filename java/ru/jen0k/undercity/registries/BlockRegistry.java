package ru.jen0k.undercity.registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.jen0k.undercity.blocks.CommonBlock;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block USELESSBLOCK = new CommonBlock("uselessblock", Material.CLAY);
}
