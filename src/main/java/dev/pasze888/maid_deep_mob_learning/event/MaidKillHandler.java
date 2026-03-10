package dev.pasze888.maid_deep_mob_learning.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.HostileConfig;
import dev.shadowsoffire.hostilenetworks.HostileEvents;
import dev.shadowsoffire.hostilenetworks.item.DeepLearnerItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

// 独立的事件监听类
public class MaidKillHandler {
    /*
    女仆杀死怪物时增加对应模型进度
    */
    @SubscribeEvent
    public void kill(LivingDeathEvent event) {
        // 配置检查
        if (!HostileConfig.killModelUpgrade) return;

        // 击杀者必须是女仆
        if (!(event.getSource().getEntity() instanceof EntityMaid maid)) return;

        var killedType = event.getEntity().getType();

        // 主手、副手
        // 由于主手需要持有武器，这里排除主手
        // checkAndUpdate(maid.getMainHandItem(), killedType);
        checkAndUpdate(maid.getOffhandItem(), killedType);

        // 背包遍历
         ItemStackHandler backpack = maid.getMaidInv();
//        IItemHandler backpack = maid.getAvailableInv(false);

            for (int i = 0; i < backpack.getSlots(); i++) {
                checkAndUpdate(backpack.getStackInSlot(i), killedType);
            }


        // 若有饰品栏，类似处理...
        if (ModList.get().isLoaded("curios")){
            ICuriosItemHandler inv = CuriosApi.getCuriosInventory(maid).orElse(null);
            if (inv != null) {
                SlotResult result = inv.findFirstCurio(Hostile.Items.DEEP_LEARNER.value()).orElse(null);
                if (result != null) {
                    ItemStack stack = result.stack();
                    if (stack.getItem() instanceof DeepLearnerItem) {
                        HostileEvents.updateModels(stack, killedType, 0);
                        inv.setEquippedCurio(result.slotContext().identifier(), result.slotContext().index(), stack);
                    }
                }
            }
        }
    }

    private void checkAndUpdate(ItemStack stack, EntityType<?> killedType) {
        if (stack.getItem() instanceof DeepLearnerItem) {
            HostileEvents.updateModels(stack, killedType, 0);
        }
    }
}