package dev.pasze888.maid_deep_mob_learning.Compat;

import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.github.tartaricacid.touhoulittlemaid.api.LittleMaidExtension;
import com.github.tartaricacid.touhoulittlemaid.client.overlay.MaidTipsOverlay;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@LittleMaidExtension
public class LittleMaidCompat implements ILittleMaid {
    //添加深度学习器提示
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addMaidTips(MaidTipsOverlay maidTipsOverlay) {
        ResourceLocation deepLearnerId = ResourceLocation.fromNamespaceAndPath("hostilenetworks", "deep_learner");
        Item deepLearnerItem = BuiltInRegistries.ITEM.get(deepLearnerId);
        if (deepLearnerItem == BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace("air"))) {
            // 可选：记录日志或使用默认物品作为后备
            System.out.println("警告：找不到物品 hostilenetworks:deep_learner，请确保 Hostile Neural Networks 模组已安装。");
            return; // 或者添加其他提示
        }
        maidTipsOverlay.addTips(
                "将深度学习器放在女仆物品栏",
                deepLearnerItem
        );
    }
}