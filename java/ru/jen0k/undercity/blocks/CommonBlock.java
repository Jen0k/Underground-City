package ru.jen0k.undercity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.jen0k.undercity.UnderCity;
import ru.jen0k.undercity.helpers.IHasModel;
import ru.jen0k.undercity.registries.BlockRegistry;
import ru.jen0k.undercity.registries.ItemRegistry;

public class CommonBlock extends Block implements IHasModel {
    public CommonBlock(String name, Material material)
    {
        super(material);

        setUnlocalizedName(name);
        setRegistryName(name);

        BlockRegistry.BLOCKS.add(this);
        ItemRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }

    @Override
    public void reristerModels() {
        UnderCity.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "Inventory");
    }
}
