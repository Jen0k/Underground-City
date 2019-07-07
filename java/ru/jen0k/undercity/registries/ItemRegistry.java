package ru.jen0k.undercity.registries;

import net.minecraft.item.Item;
import ru.jen0k.undercity.items.CommonItem;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry
{
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item USELESSITEM = new CommonItem("uselessitem");
}
