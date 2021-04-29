package tk.tuxclient.mods.impl.keystrokes;

import java.util.Objects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class Key {
	
	public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
		this.name = name;
	  	this.keyBind = keyBind;
	  	this.x = x;
	    this.y = y;
	    this.width = width;
	    this.height = height;
	}

	final static Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
	  
	final static Key A = new Key("A", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18);
	  
	final static Key S = new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18);
	  
	final static Key D = new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18);
	  
	final static Key LMB = new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18);
	  
	final static Key RMB = new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18);
	  
	private final String name;
	  
	private final KeyBinding keyBind;
	  
	private final int x;
	  
	private final int y;
	  
	private final int width;
	  
	private final int height;
	  
	public String getName;
	  
	public boolean isDown() {
	    return this.keyBind.isKeyDown();
	}
	  
	public int getHeight() {
	  return this.height;
	}
	  
	public int getWidth() {
	  return this.width;
	}
	  
	public int getX() {
		return this.x;
	}
  
	public int getY() {
		return this.y;
	}
  
	public String getName() {
		return this.name;
	}
}
