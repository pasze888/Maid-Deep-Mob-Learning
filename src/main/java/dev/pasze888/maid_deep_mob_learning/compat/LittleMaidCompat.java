package dev.pasze888.maid_deep_mob_learning.compat;

import dev.pasze888.maid_deep_mob_learning.MaidDeepMobLearning;
import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
import com.github.tartaricacid.touhoulittlemaid.api.LittleMaidExtension;
//import com.github.tartaricacid.touhoulittlemaid.api.animation.IMagicCastingAnimationProvider
import com.github.tartaricacid.touhoulittlemaid.client.overlay.MaidTipsOverlay;

import dev.shadowsoffire.hostilenetworks.Hostile;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 这个类是实现女仆模型相关功能的入口类。<br>
 * 这个需要实现 ILittleMaid 接口，并使用 @LittleMaidExtension 注解，女仆模组会在合适的时间自动实例化这个类，并进行相关注册。<br>
 * 注解的好处就在于，如果没有安装女仆模组时，该类不会被加载，保证了模组运行的稳定性。<br>
 * <br>
 * This class is the entry point for implementing maid model related features.<br>
 * It needs to implement the ILittleMaid interface and use the @LittleMaidExtension annotation.<br>
 * The maid mod will automatically instantiate this class at the appropriate time and perform related registrations.<br>
 * The benefit of the annotation is that if the maid mod is not installed, this class will not be loaded, ensuring the stability of the mod's operation.<br>
 */
@LittleMaidExtension
public class LittleMaidCompat implements ILittleMaid {
    // 默认构造函数，女仆模组会在合适的时间调用这个构造函数。可以在这里注册女仆专属的事件
    // Default constructor, the maid mod will call this constructor at the appropriate time. You can register maid-specific events here.
//    public LittleMaidCompat(){
//        System.out.println("LittleMaidCompat loaded");
//    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addMaidTips(MaidTipsOverlay overlay) {
//        var id= BuiltInRegistries.ITEM.get(new ResourceLocation("hostilenetworks","deep_learner"));
        // 前面的是语言文件的 key，后面的是物品
        // The first part is the key in the language file, and the second part is the item
        overlay.addTips("overlay.maid_deep_mob_learning.deep_learner.tips", Hostile.Items.DEEP_LEARNER.value());
    }

    }
