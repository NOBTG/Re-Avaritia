package committee.nova.mods.avaritia.common.item.singularity;

import committee.nova.mods.avaritia.common.entity.ImmortalItemEntity;
import committee.nova.mods.avaritia.init.registry.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * EternalSingularityItem
 * <p>
 * Author cnlimiter
 * Version 1.0
 * Description
 * Date 2024/6/13 下午7:52
 */
public final class EternalSingularityItem extends Item {
    public EternalSingularityItem(Function<Properties, Properties> properties) {
        super(properties.apply(new Properties().stacksTo(16).rarity(Rarity.EPIC)));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(Level level, Entity location, ItemStack stack) {
        return ImmortalItemEntity.create(ModEntities.IMMORTAL.get(), level, location.getX(), location.getY(), location.getZ(), stack);
    }
}
