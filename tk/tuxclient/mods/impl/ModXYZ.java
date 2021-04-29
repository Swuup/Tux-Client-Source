package tk.tuxclient.mods.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;
import tk.tuxclient.settings.BooleanSetting;
import tk.tuxclient.settings.ColorSetting;
import tk.tuxclient.settings.ModeSetting;
import tk.tuxclient.settings.NumberSetting;
import tk.tuxclient.util.render.JColor;

public class ModXYZ extends ModDraggable {

	public static BooleanSetting bgSetting = new BooleanSetting("Background", true);
	public static NumberSetting scale = new NumberSetting("Mod Scale", 1, 0.1, 10, 0.1);
	public static ColorSetting textColor = new ColorSetting("Text Color", new JColor(255, 255, 255));
	public static ColorSetting backgroundColor = new ColorSetting("Background Color", new JColor(255, 255, 255));
	public static ModeSetting mode = new ModeSetting("Line count", "Multi line", "1 line", "Multi line");
	public static BooleanSetting showBiome = new BooleanSetting("Show Biome", true);

	public ModXYZ(String name, ResourceLocation icon) {
		super(name, icon);

		this.settings.add(bgSetting);
		this.settings.add(scale);
		this.settings.add(textColor);
		this.settings.add(backgroundColor);
		this.settings.add(mode);
		this.settings.add(showBiome);
	}

	@Override
	public int getWidth() {
		return font.getStringWidth(getXYZString());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {

		double x = mc.getRenderViewEntity().posX;
		double y = mc.getRenderViewEntity().getEntityBoundingBox().minY;
		double z = mc.getRenderViewEntity().posZ;
		BlockPos blockPos = new BlockPos(x, y, z);

		if (bgSetting.isEnabled()) {
			Gui.drawRect(pos.getAbsoluteX() * (int) scale.getValue(), pos.getAbsoluteY() * (int) scale.getValue(), (pos.getAbsoluteX() + 1) * (int) scale.getValue(), (pos.getAbsoluteY() + 3) * (int) scale.getValue(), backgroundColor.getColor().getRGB());
		}
		if (mode.getMode().equals("1 line")) {
			font.drawString(getXYZString(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1, textColor.getColor().getRGB());
		} else {
			font.drawString(String.valueOf(mc.getRenderViewEntity().posX), pos.getAbsoluteX() + 2, pos.getAbsoluteY(), textColor.getColor().getRGB());
			font.drawString(String.valueOf(mc.getRenderViewEntity().getEntityBoundingBox().minY), pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, textColor.getColor().getRGB());
			font.drawString(String.valueOf(mc.getRenderViewEntity().posZ), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + (2*font.FONT_HEIGHT), textColor.getColor().getRGB());
			if (showBiome.isEnabled()) {
				font.drawString(String.valueOf(mc.getRenderViewEntity().getEntityWorld().getBiomeGenForCoords(blockPos).biomeName), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + (3*font.FONT_HEIGHT), textColor.getColor().getRGB());
			}
		}

	}

	private String getXYZString() {
		return String.format(
					"XYZ: %.0f / %.0f / %.0f", 
					mc.getRenderViewEntity().posX, 
					mc.getRenderViewEntity().getEntityBoundingBox().minY, 
					mc.getRenderViewEntity().posZ
				);
	}

}
