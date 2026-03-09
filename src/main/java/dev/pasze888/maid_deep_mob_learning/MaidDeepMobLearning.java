package dev.pasze888.maid_deep_mob_learning;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.HostileEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.items.ItemStackHandler;


@Mod("maid_deep_mob_learning")
public class MaidDeepMobLearning {

    @SubscribeEvent
    public static void onMaidKill(LivingDeathEvent event) {
        // 只在服务端处理
        if (event.getEntity().level().isClientSide) return;

        // 获取击杀者，必须是女仆
        if (!(event.getSource().getEntity() instanceof EntityMaid maid)) return;

        LivingEntity killed = event.getEntity();
        EntityType<?> killedType = killed.getType();

        // 1. 更新主背包中的深度学习器
        ItemStackHandler mainInv = maid.getMaidInv();
        for (int i = 0; i < mainInv.getSlots(); i++) {
            ItemStack stack = mainInv.getStackInSlot(i);
            if (stack.is(Hostile.Items.DEEP_LEARNER)) {
                HostileEvents.updateModels(stack, killedType, 0);
            }
        }

        // 2. 更新副手中的深度学习器
        ItemStack offhand = maid.getOffhandItem();
        if (offhand.is(Hostile.Items.DEEP_LEARNER)) {
            HostileEvents.updateModels(offhand, killedType, 0);
        }

    }
}