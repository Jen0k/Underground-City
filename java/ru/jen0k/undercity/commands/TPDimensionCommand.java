package ru.jen0k.undercity.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TPDimensionCommand extends CommandBase
{
    public TPDimensionCommand()
    {
        super();

        aliases.add("tpdim");
    }

    private static final List<String> aliases = new ArrayList<String>();

    @Override
    public String getName()
    {
        return "tpdim";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }


    @Override
    public String getUsage(ICommandSender sender)
    {
        return "commands.tpdim.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1 || (args.length == 3) || args.length > 7)
        {
            throw new WrongUsageException("commands.tpdim.usage");
        }

        EntityPlayerMP teleportationEntity;
        int targetDimmensionID;
        double X;
        double Y;
        double Z;
        float Yaw;
        float Pitch;

        int argPos = 0;
        if (args.length == 1 || args.length == 4 || args.length == 6)
        {
            teleportationEntity = getCommandSenderAsPlayer(sender);
        }
        else
        {
            teleportationEntity = getEntity(server, sender, args[argPos++], EntityPlayerMP.class);
        }

        targetDimmensionID = parseInt(args[argPos++]);

        if (args.length < 4)
        {
            X = 0.0d;
            Y = 0.0d;
            Z = 0.0d;
        }
        else
        {
            X = parseCoordinate(teleportationEntity.posX, args[argPos++], true).getResult();
            Y = parseCoordinate(teleportationEntity.posY, args[argPos++], -4096, 4096, false).getResult();
            Z = parseCoordinate(teleportationEntity.posZ, args[argPos++], true).getResult();
        }

        if (args.length < 6)
        {
            Yaw = teleportationEntity.rotationYaw;
            Pitch = teleportationEntity.prevRotationPitch;
        }
        else
        {
            Yaw = (float)parseCoordinate(teleportationEntity.rotationYaw, args[argPos++], true).getResult();
            Pitch = (float)parseCoordinate(teleportationEntity.prevRotationPitch, args[argPos], true).getResult();
        }

        teleportationEntity.dismountRidingEntity();
        teleportationEntity.changeDimension(targetDimmensionID);
        teleportationEntity.connection.setPlayerLocation(X, Y, Z, Yaw, Pitch);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        return Collections.emptyList();
    }
}
