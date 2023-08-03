package mod.surviving_the_aftermath.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DevouredEnchantment extends BaseEnchantment {

    public DevouredEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof TieredItem || item instanceof TridentItem;
    }

}