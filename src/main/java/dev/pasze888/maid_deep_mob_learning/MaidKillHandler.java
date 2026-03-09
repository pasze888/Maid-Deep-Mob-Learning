package dev.pasze888.maid_deep_mob_learning;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import dev.shadowsoffire.hostilenetworks.HostileConfig;
import dev.shadowsoffire.hostilenetworks.HostileEvents;
import dev.shadowsoffire.hostilenetworks.item.DeepLearnerItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.items.ItemStackHandler;

// 独立的事件监听类
public class MaidKillHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        // 配置检查
        if (!HostileConfig.killModelUpgrade) return;

        // 击杀者必须是女仆
        if (!(event.getSource().getEntity() instanceof EntityMaid maid)) return;

        var killedType = event.getEntity().getType();

        // 主手、副手
        checkAndUpdate(maid.getMainHandItem(), killedType);
        checkAndUpdate(maid.getOffhandItem(), killedType);

        // 背包遍历（假设 getMaidInv() 返回 ItemStackHandler）
        ItemStackHandler backpack = maid.getMaidInv();
        if (backpack != null) {
            for (int i = 0; i < backpack.getSlots(); i++) {
                checkAndUpdate(backpack.getStackInSlot(i), killedType);
            }
        }

        // 若有饰品栏，类似处理...
    }

    private void checkAndUpdate(ItemStack stack, EntityType<?> killedType) {
        if (stack.getItem() instanceof DeepLearnerItem) {
            HostileEvents.updateModels(stack, killedType, 0);
        }
    }
}