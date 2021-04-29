package tk.tuxclient.mods;

import java.util.ArrayList;
import java.util.List;

public class Mods {
    public static List<Mod> listOfMods = new ArrayList<>();

    public Mods() {
        listOfMods.add(ModInstances.getModToggleSprint());
        listOfMods.add(ModInstances.getModZoom());
        listOfMods.add(ModInstances.getCPSMod());
        listOfMods.add(ModInstances.getModKeyStrokes());
        listOfMods.add(ModInstances.getModFPS());
        listOfMods.add(ModInstances.getModXYZ());
        listOfMods.add(ModInstances.getModArmorStatus());
    }
}
