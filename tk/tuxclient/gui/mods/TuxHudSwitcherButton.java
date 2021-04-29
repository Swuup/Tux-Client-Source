package tk.tuxclient.gui.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tk.tuxclient.util.TuxDrawUtils;
import tk.tuxclient.util.font.TuxFontRenderer;

import java.awt.*;

public class TuxHudSwitcherButton extends GuiButton {

    protected boolean hovered;

    public TuxHudSwitcherButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {

        super(buttonId, x,  y, widthIn, heightIn, buttonText);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    public TuxHudSwitcherButton(int buttonId, int x, int y, String buttonText) { this(buttonId, x, y, 100, 20, buttonText); }

    /*
     * Draw Button
     */

    TuxFontRenderer font = new TuxFontRenderer(new ResourceLocation("tux/font/Quicksand-Medium.ttf"), 30.0F);

    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {

        if (this.visible)
        {

            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);

            font.drawString(this.displayString, this.xPosition + this.width / 2 - (font.getStringWidth(this.displayString) / 2 - 1), this.yPosition + this.height - 35,  -1);
            TuxDrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width + 1, this.yPosition + this.height, 15, new Color(115,120,140, 70).getRGB());
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() { return this.hovered; }
}
