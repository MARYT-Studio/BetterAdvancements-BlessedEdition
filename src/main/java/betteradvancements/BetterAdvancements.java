package betteradvancements;

import betteradvancements.proxy.CommonProxy;
import betteradvancements.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.ID, name = Reference.NAME, guiFactory = Reference.MOD_GUI_FACTORY, version = Reference.VERSION_FULL, clientSideOnly = true)
public class BetterAdvancements {
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;
    public static Logger LOGGER = LogManager.getLogger(Reference.NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) { proxy.init(event); }
}
