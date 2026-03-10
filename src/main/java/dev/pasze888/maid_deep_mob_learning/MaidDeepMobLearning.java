package dev.pasze888.maid_deep_mob_learning;
import dev.pasze888.maid_deep_mob_learning.event.MaidKillHandler;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(MaidDeepMobLearning.MODID)
public class MaidDeepMobLearning {

    public static final String MODID = "maid_deep_mob_learning";

    public MaidDeepMobLearning() {
        NeoForge.EVENT_BUS.register(new MaidKillHandler());
    }
}