package tk.tuxclient.util.http;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;

public class HWID {

    static String os = System.getProperty("user.name");

    public static String get() {
        String hwid = os + "," + Minecraft.getMinecraft().getSession().getProfile().getId();
    	// String hwid = os + "," + "asertyui";
        LogManager.getLogger().info(hwid);
        return hwid;
    }

}
