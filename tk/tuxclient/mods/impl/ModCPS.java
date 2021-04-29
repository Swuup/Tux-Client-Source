package tk.tuxclient.mods.impl;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;
import tk.tuxclient.settings.BooleanSetting;
import tk.tuxclient.settings.ColorSetting;
import tk.tuxclient.settings.ModeSetting;
import tk.tuxclient.settings.NumberSetting;
import tk.tuxclient.util.render.JColor;

public class ModCPS extends ModDraggable {
	
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;

	public static BooleanSetting bgSetting = new BooleanSetting("Background", true);
	public static NumberSetting scale = new NumberSetting("Mod Scale", 1, 0.1, 10, 0.1);
	public static ColorSetting textColor = new ColorSetting("Text Color", new JColor(255, 255, 255));
	public static ColorSetting backgroundColor = new ColorSetting("Background Color", new JColor(255, 255, 255));
	public static ModeSetting mouseButton = new ModeSetting("Keys", "leftClick", "leftClick", "rightClick", "both");

	public ModCPS(String name, ResourceLocation icon) {
		super(name, icon);
	}

	@Override
	public int getWidth() {
		return font.getStringWidth("CPS: 00"); 
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {

		if (bgSetting.isEnabled()) {
			Gui.drawRect(pos.getAbsoluteX() * (int) scale.getValue(), pos.getAbsoluteY() * (int) scale.getValue(), (pos.getAbsoluteX() + 1) * (int) scale.getValue(), (pos.getAbsoluteY() + 3) * (int) scale.getValue(), backgroundColor.getColor().getRGB());
		}
		if (mouseButton.is("leftClick")) {
			font.drawString(String.valueOf(getLMBCPS()), (double) pos.getAbsoluteX() * scale.getValue(), (double) pos.getAbsoluteY() * scale.getValue(), textColor.getColor().getRGB());
		} if (mouseButton.is("rightClick")) {
			font.drawString(String.valueOf(getRMBCPS()), pos.getAbsoluteX() * scale.getValue(), pos.getAbsoluteY() * scale.getValue(), textColor.getColor().getRGB());
		} else {
			font.drawString(getLMBCPS() + " | " + getRMBCPS(), pos.getAbsoluteX() * scale.getValue(), pos.getAbsoluteY() * scale.getValue(), textColor.getColor().getRGB());
		}
	}
	
	private int getLMBCPS() {
		final boolean pressed = Mouse.isButtonDown(0);
		if(pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if(pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();
		
	}

	private int getRMBCPS() {
		final boolean pressed = Mouse.isButtonDown(1);
		if(pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if(pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000 < time);
		return this.clicks.size();

	}

}
