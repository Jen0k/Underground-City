package ru.jen0k.undercity.proxy;

import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import ru.jen0k.undercity.registries.CommandRegistry;
import ru.jen0k.undercity.registries.DimensionRegistry;
import ru.jen0k.undercity.world.worldtypes.UnderCityWorldType;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        DimensionRegistry.registerDimensions();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {
        WorldType test = new UnderCityWorldType();
    }

    public void serverInit(FMLServerStartingEvent event)
    {
        CommandRegistry.registerCommands(event);
    }

    public void registerItemRenderer(Item item, int meta, String id) {}
}
