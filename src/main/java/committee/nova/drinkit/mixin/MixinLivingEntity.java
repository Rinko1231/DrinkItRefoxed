package committee.nova.drinkit.mixin;

import committee.nova.drinkit.config.drinkConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    public MixinLivingEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    public void eat2(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        if(!drinkConfig.isSilent(food)) {
            level.playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatingSound(food), SoundSource.NEUTRAL, 1.0F, 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
        }
        this.addEatEffect(foodProperties);
        food.consume(1, (LivingEntity)(Object)this);
        this.gameEvent(GameEvent.EAT);
        cir.setReturnValue(food);
    }

    @Shadow protected abstract void addEatEffect(FoodProperties foodProperties);

    @Shadow
    public abstract SoundEvent getEatingSound(ItemStack food);


}
