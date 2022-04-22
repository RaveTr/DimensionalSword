package com.slepderp.dimensionalswords.item;

import net.minecraft.item.ItemStack;

public class ChargedModuleItem extends ModuleItem{

	public ChargedModuleItem(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return stack.isDamaged();
	}

}
