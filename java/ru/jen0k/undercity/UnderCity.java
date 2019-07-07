package ru.jen0k.undercity;

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
}
