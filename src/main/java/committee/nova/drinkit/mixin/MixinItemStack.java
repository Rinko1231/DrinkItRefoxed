package committee.nova.drinkit.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static committee.nova.drinkit.config.drinkConfig.isDrinkable;
import static committee.nova.drinkit.config.drinkConfig.isThick;

@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @Inject(method = "getUseAnimation", at = @At("RETURN"), cancellable = true)
    public void inject$getUseAnimation(CallbackInfoReturnable<UseAnim> cir) {
        if (isDrinkable((ItemStack) (Object) this)) cir.setReturnValue(UseAnim.DRINK);
    }

    @Inject(method = "getDrinkingSound", at = @At("RETURN"), cancellable = true)
    public void inject$getDrinkingSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (isThick((ItemStack) (Object) this)) cir.setReturnValue(SoundEvents.HONEY_DRINK);
    }

}
