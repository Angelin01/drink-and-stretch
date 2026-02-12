package com.angelin01.drinkandstretch.fabric.mixins;

import com.angelin01.drinkandstretch.DrinkAndStretch;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
	@Inject(method = "hurt", at = @At("HEAD"))
	private void onHurt(DamageSource damageSource, float _amount, CallbackInfoReturnable<Boolean> _cir) {
		final LocalPlayer player = (LocalPlayer) (Object) this;
		DrinkAndStretch.onPlayerReceiveDamage(player, damageSource);
	}
}
