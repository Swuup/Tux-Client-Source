package tk.tuxclient.gui.screens;

import net.minecraft.client.gui.GuiScreen;
import tk.tuxclient.Tux;
import tk.tuxclient.util.TuxDrawUtils;
import tk.tuxclient.util.font.TuxFontRenderer;

import java.awt.*;

public class GuiHWIDBanned extends GuiScreen {

    TuxFontRenderer font;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if (font == null) {
            font = Tux.getInstance().getFontRenderer();
        }

        TuxDrawUtils.drawBorderedRoundedRect(300, 100, this.width - 300, this.height - 100, 10, 1, -1, Color.gray.getRGB());
        font.drawString("Dear " + mc.getSession().getProfile().getName() + ",", 310, 110, -1);
        font.drawString("You have been banned.", 310, 120, -1);
        font.drawString("Sorry, From the Tux Client Devs", 310, 120, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
