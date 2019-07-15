package ru.jen0k.undercity.registries;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.world.dimensions.UnderCityDimension;

public class DimensionRegistry
{
    public static DimensionType UNDERCITY;


    public static void registerDimensions()
    {
        if (DimensionManager.isDimensionRegistered(UnderCity.UNDERCITY_DIMMENSION_ID))
        {
            throw new IllegalArgumentException("Dimension ID " + UnderCity.UNDERCITY_DIMMENSION_ID + " already used!");
        }

        UNDERCITY = DimensionType.register("Undercity", "_undercity", UnderCity.UNDERCITY_DIMMENSION_ID, UnderCityDimension.class, false);

        DimensionManager.registerDimension(UnderCity.UNDERCITY_DIMMENSION_ID, UNDERCITY);
    }
}
