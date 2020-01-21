package ru.jen0k.undercity.world.chunkgenerators;

public class UnderCityGenerationPattern implements INineTreeNode {
    public UnderCityGenerationPattern(int treeLevel)
    {
        this.treeLevel = treeLevel;
    }

    private INineTreeNode[][] childs = new INineTreeNode[3][3];
    private boolean[][] connections = new boolean[3][3];
    int treeLevel;

    @Override
    public void GenerateRegion(int x, int z) {

    }
}
