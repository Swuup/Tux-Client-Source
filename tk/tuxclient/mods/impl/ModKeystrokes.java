package tk.tuxclient.mods.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;
import tk.tuxclient.mods.impl.keystrokes.Key;
import tk.tuxclient.mods.impl.keystrokes.KeystrokesMode;
import tk.tuxclient.settings.BooleanSetting;
import tk.tuxclient.settings.ColorSetting;
import tk.tuxclient.settings.NumberSetting;
import tk.tuxclient.util.render.JColor;

import java.awt.*;

public class ModKeystrokes extends ModDraggable {
	
	private KeystrokesMode mode = KeystrokesMode.WASD_SPRINT_MOUSE;

	public static NumberSetting scale = new NumberSetting("Mod Scale", 1, 0.1, 10, 0.1);
	public static ColorSetting textColor = new ColorSetting("Text Color", new JColor(255, 255, 255));
	public static ColorSetting keyPressedColor = new ColorSetting("Key Pressed Color", new JColor(255, 255, 255, 102));
	public static ColorSetting keyNotPressedColor = new ColorSetting("Key Not Pressed Color", new JColor(0, 0, 0, 102));

	public ModKeystrokes(String name, ResourceLocation icon) {
		super(name, icon);
	}
	public void setMode(KeystrokesMode mode) {
		this.mode = mode;
	}
	
	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {

		GL11.glPushMatrix();
		
		for(Key key : mode.getKeys()) {
			int textWidth = font.getStringWidth(key.getName());
			
			Gui.drawRect(
						pos.getAbsoluteX() + key.getX(),
						pos.getAbsoluteY() + key.getY(),
						pos.getAbsoluteX() + key.getX() + key.getWidth(),
						pos.getAbsoluteY() + key.getY() + key.getHeight(),
						key.isDown() ? keyPressedColor.getValue().getRGB(): keyNotPressedColor.getValue().getRGB()
					);
			
			font.drawString(key.getName(),
					pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
					pos.getAbsoluteY() + key.getY() + key.getHeight() /2 - 4,
					key.isDown() ? new Color(0, 0, 0, 102).getRGB(): new Color(255, 255, 255, 102).getRGB()
				);
			
		}
		
		GL11.glPopMatrix();
	}

}
