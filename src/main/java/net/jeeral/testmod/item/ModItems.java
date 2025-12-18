package net.jeeral.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jeeral.testmod.TestMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SCHIZO_BUCKS = registerItem("schizo_bucks", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TestMod.MOD_ID, "schizo_bucks")))));


    private static Item registerItem(String name, Item item) {
       return Registry.register(Registries.ITEM, Identifier.of(TestMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TestMod.LOGGER.info("Registering mod items for " + TestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(SCHIZO_BUCKS);
        });
    }
}
