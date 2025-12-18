package net.jeeral.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jeeral.testmod.TestMod;
import net.jeeral.testmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup STUPID_MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TestMod.MOD_ID, "stupid_mod_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.SCHIZO_BUCKS))
                    .displayName(Text.translatable("itemgroup.testmod.stupid_mod_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SCHIZO_BUCKS);
                        entries.add(ModItems.RECALL_BELL);
                        entries.add(ModBlocks.THE_BRO_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        TestMod.LOGGER.info("Registering item goups for " + TestMod.MOD_ID);
    }
}
