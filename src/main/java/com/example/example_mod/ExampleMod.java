package com.example.example_mod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");

	// tutorial: items/first-item
	public static final Item EXAMPLE_ITEM = new Item(new QuiltItemSettings());

	// tutorial: items/food
	public static final Item EXAMPLE_FOOD = new Item(new QuiltItemSettings().food(
		new FoodComponent.Builder()
			.hunger(2)
			.saturationModifier(1)
			.snack()
			.alwaysEdible()
			.meat()
			.statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 10), 0.8f)
			.build()
	));

	// tutorial: items/armor
	public static final ArmorMaterial EXAMPLE_ARMOR_MATERIAL = new ExampleArmorMaterial();
	public static final Item EXAMPLE_HELMET = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings());
	public static final Item EXAMPLE_CHESTPLATE = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings());
	public static final Item EXAMPLE_LEGGINGS = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings());
	public static final Item EXAMPLE_BOOTS = new ArmorItem(EXAMPLE_ARMOR_MATERIAL, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings());

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		// tutorial: items/first-item
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_item"), EXAMPLE_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addItem(EXAMPLE_ITEM);
		});


		// tutorial: items/food
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_food"), EXAMPLE_FOOD);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> {
			entries.addItem(EXAMPLE_FOOD);
		});


		// tutorial: items/armor
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_item_helmet"), EXAMPLE_HELMET);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_item_chestplate"), EXAMPLE_CHESTPLATE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_item_leggings"), EXAMPLE_LEGGINGS);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "example_item_boots"), EXAMPLE_BOOTS);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(Items.NETHERITE_BOOTS, EXAMPLE_HELMET, EXAMPLE_CHESTPLATE, EXAMPLE_LEGGINGS, EXAMPLE_BOOTS);
		});
	}
}
