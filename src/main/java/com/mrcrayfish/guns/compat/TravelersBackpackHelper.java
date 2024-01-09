package com.mrcrayfish.guns.compat;

import com.mrcrayfish.guns.common.AmmoContext;
import com.mrcrayfish.guns.common.Gun;
import com.tiviacz.travelersbackpack.capability.CapabilityUtils;
import com.tiviacz.travelersbackpack.inventory.TravelersBackpackContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.concurrent.atomic.AtomicReference;

public class TravelersBackpackHelper {
    public static AmmoContext findAmmo(Player player, ResourceLocation id)
    {
        final AtomicReference<AmmoContext> ctx = new AtomicReference<>(AmmoContext.NONE);
        CapabilityUtils.getCapability(player).ifPresent(t -> {
            final TravelersBackpackContainer container = t.getContainer();
            final IItemHandlerModifiable handler = container.getCombinedHandler();
            final int size = handler.getSlots();
            for (int i = 0; i < size; i++)
            {
                final ItemStack stack = handler.getStackInSlot(i);
                if(!Gun.isAmmo(stack, id)) continue;
                final SimpleContainer con = new SimpleContainer();
                con.addListener(c -> container.setDataChanged((byte) 2));
                ctx.set(new AmmoContext(stack, con));
                return;
            }
        });
        return ctx.get();
    }
}
