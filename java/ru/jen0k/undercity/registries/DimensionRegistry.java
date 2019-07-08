package ru.jen0k.undercity.registries;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import ru.jen0k.undercity.world.dimensions.UnderCityDimension;

public class DimensionRegistry
{
    public static final DimensionType UNDERCITY
            = DimensionType.register("Undercity", "_undercity", 2, UnderCityDimension.class, false);

    public static void registerDimensions()
    {
        DimensionManager.registerDimension(2, UNDERCITY);
    }
}
