package hohserg.endercompass;

import hohserg.endercompass.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static hohserg.endercompass.Main.*;

@Mod(modid = ID, name = NAME, version = VERSION, dependencies = "required-after:codechickenlib")
public class Main {
    public static final String ID = "endercompass";
    public static final String VERSION = "1.5.1";
    public static final String NAME = "Ender Compass";
    public static final String SERVER = "hohserg.endercompass.proxy.CommonProxy";
    public static final String CLIENT = "hohserg.endercompass.proxy.ClientProxy";

    @Instance(ID)
    public static Main instance;

    @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}