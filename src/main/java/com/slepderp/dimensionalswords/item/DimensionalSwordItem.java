package com.slepderp.dimensionalswords.item;

import java.util.Random;

import com.slepderp.dimensionalswords.config.DimSwordConfig.Common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;

public class DimensionalSwordItem extends SwordItem{
//	private final RegistryKey<World> targetDimension;

	public DimensionalSwordItem(IItemTier tier, int spd, float dmg, Properties prop/*, RegistryKey<World> targetDimension*/) {
		super(tier, spd, dmg, prop); 
	//	this.targetDimension = targetDimension;
	}
	
/*	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(!world.isClientSide && stack.getItem() == DimSwordItems.DIMENSIONAL_SWORD.get()) {
			MinecraftServer minecraftServer = ((ServerWorld) player.level).getServer();
			ServerWorld targetWorld = minecraftServer.getLevel(player.level.dimension() == this.targetDimension ? World.OVERWORLD : this.targetDimension);
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (targetWorld != null)
                serverPlayer.changeDimension(targetWorld, new HeightMapConverter());
        }
		return super.use(world, player, hand);
	}*/
	
    public Random getRNG() {
        return Item.random;
    }
    
        @SuppressWarnings("removal")
		@Override
    	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    	if(!world.isClientSide && player.getHealth() <= 4.0F) {
    		LivingEntity playa = (LivingEntity) player;
    		
                Double rayLength = new Double(Common.dimensionalSwordAmountOfBlocksTeleportedOnUse.get());
                Vector3d playerRotation = playa.getViewVector(0);
                Vector3d rayPath = playerRotation.scale(rayLength);
                Vector3d from = player.getEyePosition(0);
                Vector3d to = from.add(rayPath);
                RayTraceContext rayCtx = new RayTraceContext(from, to, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, null);
                BlockRayTraceResult rayHit = world.clip(rayCtx);


                if (rayHit.getType() == RayTraceResult.Type.MISS){
                    
                }
                else { 
                	if(playa.isPassenger())
                       playa.stopRiding();
                    Vector3d hitLocation = rayHit.getLocation();
                    playa.moveTo(hitLocation);
                    if(!player.isCreative()) {
                       playa.getItemInHand(hand).hurtAndBreak(100, playa, (entity) -> entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                    }
                    SoundEvent soundevent = playa instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    playa.level.playSound(null, playa.getX(), playa.getY(), playa.getZ(), soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    playa.playSound(soundevent, 1.0F, 1.0F);
                   
                }
    	}	
    	
   	return super.use(world, player, hand);
}
	
    	@Override
		public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!attacker.level.isClientSide && target instanceof LivingEntity) {
            double d0 = target.getX();
            double d1 = target.getY();
            double d2 = target.getZ();

            for (int i = 0; i < 20; ++i) {
                double d3 = target.getX() + (getRNG().nextDouble() - 0.5D) * 8.0D;
                double d4 = MathHelper.clamp(target.getY() + (double) (getRNG().nextInt(20) - 8), 0.0D, (attacker.level.getHeight() - 1));
                double d5 = target.getZ() + (getRNG().nextDouble() - 0.5D) * 8.0D;
                if (target.isPassenger())
                    target.stopRiding();

                LivingEntity livingEntityIn = (LivingEntity) target;
                EntityTeleportEvent.ChorusFruit event = ForgeEventFactory.onChorusFruitTeleport(livingEntityIn, d3, d4, d5);
                if (livingEntityIn.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    SoundEvent soundevent = target instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                    attacker.level.playSound(null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    target.playSound(soundevent, 1.0F, 1.0F);
                    break;
                }
            }
        }
		
		@SuppressWarnings("unused")
		boolean DurabilityPenalty = false;
		if (!attacker.level.isClientSide && attacker.getHealth() <= 4.0F) {
			DurabilityPenalty = true;
			    double d0 = target.getX();
	            double d1 = target.getY();
	            double d2 = target.getZ();

	            for (int i = 0; i < 12; ++i) {
	                double d3 = attacker.getX() + (getRNG().nextDouble() - 0.5D) * 12.0D;
	                double d4 = MathHelper.clamp(target.getY() + (double) (getRNG().nextInt(20) - 8), 0.0D, (attacker.level.getHeight() - 1));
	                double d5 = attacker.getZ() + (getRNG().nextDouble() - 0.5D) * 12.0D;
	                if (attacker.isPassenger())
	                    attacker.stopRiding();

	                LivingEntity livingEntityIn = (LivingEntity) attacker;
	                EntityTeleportEvent.ChorusFruit event = ForgeEventFactory.onChorusFruitTeleport(livingEntityIn, d3, d4, d5);
	                if (livingEntityIn.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
	                    SoundEvent soundevent = attacker instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
	                    attacker.level.playSound(null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                    attacker.playSound(soundevent, 1.0F, 1.0F);
	                    break;
	                }
	            }
	            if (DurabilityPenalty = true) {
	            	attacker.getItemInHand(Hand.MAIN_HAND).hurtAndBreak(10, attacker, (entity) -> entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
	            }
	        }
    	return super.hurtEnemy(stack, target, attacker);
	}

}
