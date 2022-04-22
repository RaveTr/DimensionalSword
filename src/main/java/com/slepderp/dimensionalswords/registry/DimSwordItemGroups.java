package com.slepderp.dimensionalswords.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DimSwordItemGroups {
	
public static ItemGroup dimSwordsGroup = new ItemGroup("dim_swords_group") {
		
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(DimSwordItems.DIMENSIONAL_SWORD.get());
        }
		
	};
	
	

}
