package tk.tuxclient.mods.impl;

import net.minecraft.util.ResourceLocation;
import tk.tuxclient.mods.Mod;
import tk.tuxclient.settings.NumberSetting;

public class ModZoom extends Mod {

    public static NumberSetting zoomAmount = new NumberSetting("Zoom Amount", 4.0, 1.0, 10.0, 0.1);

    public ModZoom(String name, ResourceLocation icon) {
        super(name, icon);

        this.settings.add(zoomAmount);
    }
}
