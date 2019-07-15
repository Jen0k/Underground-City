package ru.jen0k.undercity.world;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

public class NullTeleporter implements ITeleporter
{
    @Override
    public void placeEntity(World world, Entity entity, float yaw) {

    }
}
