package committee.nova.mods.avaritia.common.block.collector;

import committee.nova.mods.avaritia.common.tile.collector.AbsNeutronCollectorTile;
import committee.nova.mods.avaritia.common.tile.collector.DefaultNeutronCollectorTile;
import committee.nova.mods.avaritia.init.registry.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @Project: Avaritia-forge
 * @Author: cnlimiter
 * @CreateTime: 2024/1/7 20:51
 * @Description:
 */
public class DefaultNeutronCollectorBlock extends AbsNeutronCollectorBlock {
    public DefaultNeutronCollectorBlock() {
        super();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new DefaultNeutronCollectorTile(pos, state);
    }

    @Override
    protected <T extends BlockEntity> BlockEntityTicker<T> getServerTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTicker(type, ModTileEntities.neutron_collector_tile.get(), AbsNeutronCollectorTile::tick);
    }

    @Override
    protected <T extends BlockEntity> BlockEntityTicker<T> getClientTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTicker(type, ModTileEntities.neutron_collector_tile.get(), AbsNeutronCollectorTile::tick);
    }
}
