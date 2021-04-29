package tk.tuxclient.gui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tk.tuxclient.util.TuxDrawUtils;

public class TuxStoreButton extends GuiButton {

    protected boolean hovered;

    public TuxStoreButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {

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

    public TuxStoreButton(int buttonId, int x, int y, String buttonText) { this(buttonId, x, y, 20, 20, buttonText); }

    /*
     * Draw Button
     */
    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
        if (this.enabled)
        {
            this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;

            TuxDrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width + 1, this.yPosition + this.height, 4.8, 0xFF232323);

            if (!this.hovered) { GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); }
            else if (this.hovered) { GL11.glColor4f(1.0F, 1.0F, 0.63F, 1.0F); }

            GL11.glPushMatrix();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("tux/icon/cart.png"));
            Gui.drawScaledCustomSizeModalRect(this.xPosition + 1, this.yPosition + 1, -0.5F, 0.0F, 18, 18, 18, 18, 18F, 18F);
            GL11.glPopMatrix();

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() { return this.hovered; }

}
