package tk.tuxclient.gui.mainmenu;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import tk.tuxclient.util.TuxDrawUtils;

public class TuxQuitButton extends GuiButton {

	protected boolean hovered;
	
    public TuxQuitButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
    	
        super(buttonId, x,  y, widthIn, heightIn, buttonText);
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

	public TuxQuitButton(int buttonId, int x, int y, String buttonText) { this(buttonId, x, y, 20, 20, buttonText); }
	
	/*
     * Draw Button
     */
    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
        if (this.enabled)
        {
        	 this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
        	
        	 TuxDrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width + 1, this.yPosition + this.height, 4.8, 0xFF232323);       	
    		
        	 if (!this.hovered) { GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); }
        	 else if (this.hovered) { GL11.glColor4f(0.670F, 0.266F, 0.247F, 1.0F); }
    		
        	 GL11.glPushMatrix();
        	 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        	 GL11.glEnable(GL11.GL_BLEND);
        	 Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("tux/icon/quit.png"));
        	 Gui.drawScaledCustomSizeModalRect(this.xPosition + 1, this.yPosition + 1, -0.5F, 0.0F, 18, 18, 18, 18, 18F, 18F);
        	 GL11.glColor4f(1f, 1f, 1f, 1f);
             GlStateManager.resetColor();
        	 GL11.glDisable(GL11.GL_BLEND);
        	 GL11.glPopMatrix();

        }
    }
    
    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() { return this.hovered; }
	
}
