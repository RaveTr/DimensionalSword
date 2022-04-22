package com.slepderp.dimensionalswords.client;

import com.slepderp.dimensionalswords.config.DimSwordConfig;
import com.slepderp.dimensionalswords.registry.DimSwordTags;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ToolTips {
	
    public static void onToolTipEvent(ItemTooltipEvent event) {
        if (DimSwordConfig.Common.toolTipsEnabled.get()) {
            final Item item = event.getItemStack().getItem();
            if (item.is(DimSwordTags.TOOLTIP)) {
                if (Screen.hasShiftDown() || Screen.hasControlDown())
                    event.getToolTip().add(new TranslationTextComponent("tooltip.dimensionalswords." + item.getRegistryName().toString().replaceAll("dimensionalswords:", "")).withStyle(TextFormatting.AQUA));
                else
                    event.getToolTip().add(new TranslationTextComponent("tooltip.dimensionalswords.default").withStyle(TextFormatting.DARK_GREEN));
            }
        }
    }

}
