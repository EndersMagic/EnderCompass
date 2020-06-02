package hohserg.endercompass;


import hohserg.endercompass.network.NetworkHandler;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static hohserg.endercompass.Main.ID;

@Mod(ID)
public class Main {
    public static final String ID = "endercompass";
    public static final String VERSION = "1.0";
    public static final String NAME = "Ender Compass";

    public static final Random random = new Random();

    public Main() {
        NetworkHandler.init();
    }
}