package tk.tuxclient.mods.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;
import tk.tuxclient.settings.BooleanSetting;
import tk.tuxclient.settings.ColorSetting;
import tk.tuxclient.settings.NumberSetting;
import tk.tuxclient.util.render.JColor;

public class ModFPS extends ModDraggable {

	public static BooleanSetting bgSetting = new BooleanSetting("Background", true);
	public static NumberSetting scale = new NumberSetting("Mod Scale", 1, 0.1, 10, 0.1);
	public static ColorSetting textColor = new ColorSetting("Text Color", new JColor(255, 255, 255));
	public static ColorSetting backgroundColor = new ColorSetting("Background Color", new JColor(255, 255, 255));

	public ModFPS(String name, ResourceLocation icon) {
		super(name, icon);
	}

	@Override
	public int getWidth() {
		return 50;
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

		font.drawString("FPS: " + Minecraft.getDebugFPS(), (pos.getAbsoluteX() + 1) * scale.getValue(), (pos.getAbsoluteY() + 3) * scale.getValue(), textColor.getColor().getRGB());
	}

}
