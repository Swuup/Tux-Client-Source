package tk.tuxclient.mods;

import net.minecraft.util.ResourceLocation;
import tk.tuxclient.mods.impl.*;
import tk.tuxclient.mods.impl.togglesprintsneak.ModToggleSprint;

public class ModInstances {
	
	private static ModArmorStatus modArmorStatus;
	
	private static ModFPS modFPS;
	
	private static ModXYZ modXYZ;
	
	private static ModKeystrokes modKeyStrokes;
	
	private static ModToggleSprint modToggleSprint;
	
	private static ModCPS modCPS;

	private static ModZoom modZoom = new ModZoom("Extra Zoom", new ResourceLocation("tux/icon/mods/zoom.png"));

	public static void register(HUDManager api) {
		
		modArmorStatus = new ModArmorStatus("Armor Status", new ResourceLocation("tux/icon/mods/fps.png"));
		api.register(modArmorStatus);
		
		modFPS = new ModFPS("FPS Count", new ResourceLocation("tux/icon/mods/fps.png"));
		api.register(modFPS);
		
		modXYZ = new ModXYZ("Coordinates", new ResourceLocation("tux/icon/mods/xyz.png"));
		api.register(modXYZ);
	
		modKeyStrokes = new ModKeystrokes("Keystrokes", new ResourceLocation("tux/icon/mods/keystrokes.png"));
		api.register(modKeyStrokes);
		
		modToggleSprint = new ModToggleSprint("Toggle Sprint", new ResourceLocation("tux/icon/mods/run.png"));
		api.register(modToggleSprint);
		
		modCPS = new ModCPS("CPS Counter", new ResourceLocation("tux/icon/mods/cps.png"));
		api.register(modCPS);


	}
	
	public static ModToggleSprint getModToggleSprint() {
		return modToggleSprint;
	}

	public static ModZoom getModZoom() {
		return modZoom;
	}

	public static ModCPS getCPSMod() {
		return modCPS;
	}

	public static ModArmorStatus getModArmorStatus() {
		return modArmorStatus;
	}

	public static ModKeystrokes getModKeyStrokes() {
		return modKeyStrokes;
	}

	public static ModFPS getModFPS() {
		return modFPS;
	}

	public static ModXYZ getModXYZ() {
		return modXYZ;
	}

}