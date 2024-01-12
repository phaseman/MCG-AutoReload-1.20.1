package com.mrcrayfish.guns.client;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.locating.IModFile;

import java.nio.file.Path;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PackHandler {
    @SubscribeEvent
    public static void onAddPackFindersEvent(AddPackFindersEvent event) {
        if (!event.getPackType().equals(PackType.CLIENT_RESOURCES)) return;
        final IModFile modFile = ModList.get().getModFileById("cgm").getFile();
        final Path resourcePath = modFile.findResource("packs/cgm_pbr");
        final PathPackResources pack = new PathPackResources(
                modFile.getFileName() + ":" + resourcePath,
                resourcePath,
                false
        );
        final Pack.ResourcesSupplier sup = v -> pack;
        final String name = "feature/cgm_pbr_textures";
        event.addRepositorySource(c -> {
            c.accept(Pack.create(
                    name,
                    Component.translatable("pack.cgm.pbr.title"),
                    false,
                    sup,
                    Pack.readPackInfo("feature/cgm_pbr_textures", sup),
                    PackType.CLIENT_RESOURCES,
                    Pack.Position.TOP,
                    false,
                    PackSource.FEATURE
            ));
        });
        pack.close();
    }
}
