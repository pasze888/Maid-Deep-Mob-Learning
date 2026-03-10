package dev.pasze888.maid_deep_mob_learning.event;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.HostileConfig;
import dev.shadowsoffire.hostilenetworks.HostileEvents;
import dev.shadowsoffire.hostilenetworks.item.DeepLearnerItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;


// 独立的事件监听类
public class MaidKillHandler {
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        // 配置检查
        if (!HostileConfig.killModelUpgrade) return;
        LivingEntity killed = event.getEntity();
        // 若击杀者是女仆
        if (killed.getKillCredit() instanceof EntityMaid maid) {

            //获取怪物类型
            EntityType<?> killedType = killed.getType();

            //只返回可用部分
            ItemStackHandler backpack = maid.getMaidInv();

            for (int i = 0; i < backpack.getSlots(); i++) {

                if (backpack.getStackInSlot(i).getItem() instanceof DeepLearnerItem){
                    HostileEvents.updateModels(backpack.getStackInSlot(i),killedType,0);
                }

            }

            /*
 若安装了curios
            if (ModList.get().isLoaded("curios")){
                ICuriosItemHandler inv = CuriosApi.getCuriosInventory(maid).orElse(null);
                if (inv!=null){
                    SlotResult res = inv.findFirstCurio(Hostile.Items.DEEP_LEARNER.value()).orElse(null);
                    if (res!=null){
                        ItemStack stack = res.stack();
                        if (stack.is(Hostile.Items.DEEP_LEARNER)){
                            HostileEvents.updateModels(stack,killedType,0);
                            inv.setEquippedCurio(res.slotContext().identifier(),res.slotContext().index(),stack);
                        }
                    }
                }
            }
*/

        }
    }

}