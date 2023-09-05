package com.example.example_mod;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ExampleArmorMaterial implements ArmorMaterial {
	private static final int[] durability = new int[]{100,160,100,100};
	private static final int[] protection = new int[]{6,2,6,6};
	@Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return durability[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return protection[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return 10;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ExampleMod.EXAMPLE_ITEM);
	}

	@Override
	public String getName() {
		return "example_item";
	}

	@Override
	public float getToughness() {
		return 2;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.2f;
	}
}
