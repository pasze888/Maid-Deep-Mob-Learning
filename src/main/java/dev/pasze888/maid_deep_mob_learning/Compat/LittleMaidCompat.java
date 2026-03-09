package dev.pasze888.maid_deep_mob_learning.Compat;

import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.github.tartaricacid.touhoulittlemaid.api.LittleMaidExtension;
import com.github.tartaricacid.touhoulittlemaid.client.overlay.MaidTipsOverlay;
import dev.shadowsoffire.hostilenetworks.item.DeepLearnerItem;


@LittleMaidExtension
public class LittleMaidCompat implements ILittleMaid {
    //添加深度学习器提示
    @Override
    public void addMaidTips(MaidTipsOverlay maidTipsOverlay){
        maidTipsOverlay.addTips(
                "将深度学习器放在女仆物品栏",
                DeepLearnerItem.byId(0)
        );
    }
}