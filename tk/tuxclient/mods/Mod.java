package tk.tuxclient.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import tk.tuxclient.Tux;
import tk.tuxclient.events.EventManager;
import tk.tuxclient.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public class Mod {
	
	private boolean isEnabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Tux client;

	public String modName;
	public ResourceLocation icon;
	public List<Setting> settings = new ArrayList<>();
	
	public Mod(String name, ResourceLocation icon) {
		this.mc = Minecraft.getMinecraft();
		this.font = mc.fontRendererObj;
		this.client = Tux.getInstance();

		this.modName = name;
		this.icon = icon;
		
		setEnabled(isEnabled);
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		
		if(isEnabled) {
			EventManager.register(this);
		}
		else {
			EventManager.unregister(this);
		}
	}

	public boolean isEnabled() {
		return isEnabled;
	}
	
}
