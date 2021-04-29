package tk.tuxclient.mods.impl;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;
import tk.tuxclient.settings.BooleanSetting;
import tk.tuxclient.settings.ColorSetting;
import tk.tuxclient.settings.NumberSetting;
import tk.tuxclient.util.render.JColor;

public class ModArmorStatus extends ModDraggable {

	public static BooleanSetting bgSetting = new BooleanSetting("Background", true);;
	public static NumberSetting scale = new NumberSetting("Mod Scale", 1, 0.1, 10, 0.1);;
	public static ColorSetting textColor = new ColorSetting("Text Color", new JColor(255, 255, 255));;
	public static ColorSetting backgroundColor = new ColorSetting("Background Color", new JColor(255, 255, 255));;

	public ModArmorStatus(String name, ResourceLocation icon) {
		super(name, icon);
	}

	@Override
	public int getWidth() {
		return 64;
	}

	@Override
	public int getHeight() {
		return 64;
	}

	@Override
	public void render(ScreenPosition pos) {

		if (bgSetting.isEnabled()) {
			Gui.drawRect(pos.getAbsoluteX() * (int) scale.getValue(), pos.getAbsoluteY() * (int) scale.getValue(), (pos.getAbsoluteX() + 1) * (int) scale.getValue(), (pos.getAbsoluteY() + 3) * (int) scale.getValue(), backgroundColor.getColor().getRGB());
		}

		for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItemStack(pos, i, itemStack);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
		
		if(is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		int yAdd = (-16 * i) + 48;
		
		if(is.getItem().isDamageable()) {
			double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
			font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
		GL11.glPopMatrix();
		
	}

}
