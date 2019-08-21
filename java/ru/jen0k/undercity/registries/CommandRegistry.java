package ru.jen0k.undercity.registries;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import ru.jen0k.undercity.commands.TPDimensionCommand;

public class CommandRegistry
{
    public static final TPDimensionCommand TPDIMM = new TPDimensionCommand();

    public static void registerCommands(FMLServerStartingEvent event)
    {
        event.registerServerCommand(TPDIMM);
    }
}
