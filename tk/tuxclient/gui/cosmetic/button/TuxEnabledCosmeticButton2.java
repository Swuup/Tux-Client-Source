package tk.tuxclient.gui.cosmetic.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import tk.tuxclient.Tux;
import tk.tuxclient.cosmetics.Cosmetic;
import tk.tuxclient.cosmetics.CosmeticModel;
import tk.tuxclient.mods.Mod;
import tk.tuxclient.util.TuxDrawUtils;

import java.awt.*;

public class TuxEnabledCosmeticButton2 extends GuiButton {

    protected boolean hovered;
    public CosmeticModel currentMod;

    public TuxEnabledCosmeticButton2(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, CosmeticModel mod) {

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
        this.currentMod = mod;
    }

    public TuxEnabledCosmeticButton2(int buttonId, int x, int y, String buttonText) { this(buttonId, x, y, 100, 20, buttonText, null); }

    /*
     * Draw Button
     */


    public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {

        this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
        int color = Color.green.getRGB();

        this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
        if (this.hovered) {
            color += 70;
        }

        if (currentMod.isWearing()) {
            TuxDrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width + 1, this.yPosition + this.height, 4.8, color);
        } else {
            TuxDrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width + 1, this.yPosition + this.height, 4.8, Color.red.getRGB());
        }
        Tux.getInstance().getFontRenderer().drawCenteredStringWithShadow(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 4) / 2, -1);
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver() { return this.hovered; }
}
