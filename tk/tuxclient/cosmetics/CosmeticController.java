package tk.tuxclient.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import tk.tuxclient.Tux;
import tk.tuxclient.util.http.HTTPFunctions;
import tk.tuxclient.util.http.gson.ObjUserCosmetics;

public class CosmeticController {

    private static ObjUserCosmetics[] userCosmetics;

    public static boolean shouldRenderCape(AbstractClientPlayer player) {
        ObjUserCosmetics uc = getCosmetics(player);
        if (uc == null) {
            return false;
        }
        return uc.hasCape() && Tux.INSTANCE.cosmeticManager.snowmanCape.isWearing();
    }

    public static ObjUserCosmetics getCosmetics(AbstractClientPlayer player) {
        for (ObjUserCosmetics uc : userCosmetics) {
            if (player.getGameProfile().getId().equals(uc.getUuid())) {
                return uc;
            }
        }
        return null;
    }

    public static void downloadUserCosmetics() {
        userCosmetics = HTTPFunctions.downloadUserCosmetics();
    }

}
