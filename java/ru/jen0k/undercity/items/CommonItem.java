package ru.jen0k.undercity.items;

import net.minecraft.item.Item;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.helpers.IHasModel;
import ru.jen0k.undercity.registries.ItemRegistry;

public class CommonItem extends Item implements IHasModel
{
    public CommonItem(String name)
    {
        super();

        setUnlocalizedName(name);
        setRegistryName(name);

        ItemRegistry.ITEMS.add(this);
    }

    @Override
    public void reristerModels()
    {
        UnderCity.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
