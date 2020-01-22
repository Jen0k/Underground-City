package ru.jen0k.undercity.world.chunkgenerators;

import net.minecraft.block.Block;

import java.util.Arrays;
import java.util.Random;

public class UnderCityGenerationPattern2D implements INineTreeNode {
    public UnderCityGenerationPattern2D(int greed_x, int greed_y, int treeLevel)
    {
        this.greed_x = greed_x;
        this.greed_z = greed_y;

        this.treeLevel = treeLevel;
        this.childs = new INineTreeNode[GRID_BASE][GRID_BASE];
        this.connections = new boolean[GRID_BASE*4];
    }

    public static final int GRID_BASE = 3;

    public final int greed_x;
    public final int greed_z;

    private INineTreeNode[][] childs;
    private boolean[] connections;
    private int treeLevel;

    private INineTreeNode CreateTreeNode(int new_greed_x, int new_greed_z,
                                         int treeLevel, boolean[] new_connections,  int levelSeed)
    {
        Random random = new Random(levelSeed);

        random.setSeed(random.nextLong() + treeLevel);
        random.setSeed(random.nextLong() + new_greed_x);
        random.setSeed(random.nextLong() + new_greed_z);


        return null;
    }

    private boolean[] CalculateConnectionsForChild(int childPos_x, int childPos_z)
    {
        boolean[] new_connections = new boolean[GRID_BASE*4];

        Arrays.fill(new_connections, false);

        if (childPos_x == 0)
        {
            new_connections[GRID_BASE * 3 + GRID_BASE / 2] = connections[GRID_BASE * 3 + childPos_z];
        }
        else if (childPos_x == GRID_BASE - 1)
        {
            new_connections[GRID_BASE + GRID_BASE / 2] = connections[GRID_BASE + childPos_z];;
        }

        if (childPos_z == 0)
        {
            new_connections[GRID_BASE / 2] = connections[childPos_x];
        }
        else if (childPos_z == GRID_BASE - 1)
        {
            new_connections[GRID_BASE * 2 + GRID_BASE / 2] = connections[GRID_BASE * 2 + childPos_x];
        }

        if (childPos_x > 0 && childs[childPos_x - 1][childPos_z] != null)
        {

        }

        if (childPos_x < GRID_BASE - 1 && childs[childPos_x + 1][childPos_z] != null)
        {

        }

        if (childPos_z > 0 && childs[childPos_x][childPos_z - 1] != null)
        {

        }

        if (childPos_z < GRID_BASE - 1 && childs[childPos_x][childPos_z + 1] != null)
        {

        }


        return new_connections;
    }

    @Override
    public Block[][][] GenerateRegion(int chunk_x, int chunk_z, int random_seed) {
        int currentScope = (int)Math.pow(GRID_BASE, treeLevel - 1);

        if (currentScope == 1)
        {
            throw new RuntimeException("Found pattern on bottom layer!");
        }

        int childPos_x = chunk_x / currentScope;
        int childPos_z = chunk_z / currentScope;

        if (childs[childPos_x][childPos_z] == null)
        {
            int new_greed_x = greed_x * GRID_BASE + childPos_x;
            int new_greed_z = greed_z * GRID_BASE + childPos_z;

            boolean[] new_connections = CalculateConnectionsForChild(childPos_x, childPos_z);

            childs[childPos_x][childPos_z]
                    = CreateTreeNode(new_greed_x, new_greed_z, treeLevel, new_connections, random_seed);
        }

        return childs[childPos_x][childPos_z]
                .GenerateRegion(chunk_x % currentScope, chunk_z % currentScope, random_seed);
    }
}
