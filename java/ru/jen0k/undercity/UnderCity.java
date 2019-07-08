package ru.jen0k.undercity;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.jen0k.undercity.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = UnderCity.MODID, name = UnderCity.MODNAME, version = UnderCity.MODVERSION)
public class UnderCity
{
    static final String MODID = "undercity";
    static final String MODNAME = "Underground City";
    static final String MODVERSION = "1.0.0";

    @SidedProxy(
            clientSide = "ru.jen0k.undercity.proxy.ClientProxy",
            serverSide = "ru.jen0k.undercity.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
