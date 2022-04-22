package com.slepderp.dimensionalswords.item;

import com.slepderp.dimensionalswords.api.HeightMapConverter;
import com.slepderp.dimensionalswords.config.DimSwordConfig.Common;
import com.slepderp.dimensionalswords.registry.DimSwordItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.item.ItemStack;

public class DimensionalNetherSwordItem extends SwordItem{
	private final RegistryKey<World> targetDimension;

	public DimensionalNetherSwordItem(IItemTier tier, int spd, float dmg, Properties prop, RegistryKey<World> targetDimension) {
		super(tier, spd, dmg, prop); 
		this.targetDimension = targetDimension;
	}
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(!world.isClientSide && stack.getItem() == DimSwordItems.DIMENSIONAL_NETHER_SWORD.get()) {
			MinecraftServer minecraftServer = ((ServerWorld) player.level).getServer();
			ServerWorld targetWorld = minecraftServer.getLevel(player.level.dimension() == this.targetDimension ? World.OVERWORLD : this.targetDimension);
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (targetWorld != null)
                serverPlayer.changeDimension(targetWorld, new HeightMapConverter());
                serverPlayer.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200, 1));
            if(!player.isCreative()) {
            	player.getItemInHand(hand).hurtAndBreak(50, player, (entity) -> entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
            }
        }
		return super.use(world, player, hand);
	}
	
	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(target instanceof LivingEntity) {
			target.setSecondsOnFire(Common.dimensionalNetherSwordBurningSeconds.get());
			if(target.fireImmune()) {
				attacker.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 60, 1));
			}
		}
		return super.hurtEnemy(stack, target, attacker);
	}

}
