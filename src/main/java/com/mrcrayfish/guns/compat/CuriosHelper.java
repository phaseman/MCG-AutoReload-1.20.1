package com.mrcrayfish.guns.compat;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.NonNullConsumer;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

public class CuriosHelper {
    public static void runOnCurios(Player player, NonNullConsumer<? super ICuriosItemHandler> consumer) {
        CuriosApi.getCuriosInventory(player).ifPresent(consumer);
    }
}
