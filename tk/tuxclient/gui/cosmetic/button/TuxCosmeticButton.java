package tk.tuxclient.gui.cosmetic.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import tk.tuxclient.Tux;
import tk.tuxclient.util.TuxDrawUtils;

public class TuxCosmeticButton extends GuiButton {

    protected boolean hovered;

    public TuxCosmeticButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {

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

    public TuxCosmeticButton(int buttonId, int x, int y, String buttonText) { this(buttonId, x, y, 100, 20, buttonText); }

    /*
     * Draw Button
     */

    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {

        this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;

        int j = 14737632;

        if (!this.enabled)
        {
            j = 10526880;
        }
        else if (this.hovered)
        {
            j = 16777120;
        }

        this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);

        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.resetColor();
        TuxDrawUtils.drawHollowRect(this.xPosition, this.yPosition, this.width + 1, this.height, -1);
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Tux.getInstance().getFontRenderer().drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -1);
        GL11.glColor4f(1f, 1f, 1f, 1f);
        GL11.glPopMatrix();

    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() { return this.hovered; }
}
