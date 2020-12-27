package hohserg.endercompass;


import hohserg.endercompass.network.NetworkHandler;
import hohserg.endercompass.worldgen.OreGeneration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;

import static hohserg.endercompass.Main.ID;

@Mod(ID)
public class Main {
    public static final String ID = "endercompass";
    public static final String NAME = "Ender Compass";

    public static final Random random = new Random();

    public Main() {
        NetworkHandler.init();
    }
}