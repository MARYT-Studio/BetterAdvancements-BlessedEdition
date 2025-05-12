package betteradvancements.proxy;

import betteradvancements.BetterAdvancements;
import betteradvancements.advancements.CriterionDesc;
import betteradvancements.config.ConfigHandler;
import betteradvancements.handler.GuiOpenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Objects;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new GuiOpenHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        CriterionDesc.loadJson();
    }
}
