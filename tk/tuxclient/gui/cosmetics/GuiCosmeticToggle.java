package tk.tuxclient.gui.cosmetics;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tk.tuxclient.gui.mods.TuxHudSwitcherButton;
import tk.tuxclient.gui.screens.NewModToggle;
import tk.tuxclient.util.TuxDrawUtils;
import tk.tuxclient.util.http.HTTPFunctions;
import tk.tuxclient.util.http.gson.ObjUserCosmetics;

import java.awt.*;
import java.io.IOException;

public class GuiCosmeticToggle extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.add(new TuxHudSwitcherButton(1017, 165, 30, 160, 60, "Mods"));
        this.buttonList.add(new TuxHudSwitcherButton(1018, 330, 30, 160, 60, "Cosmetics"));
        this.buttonList.add(new TuxHudSwitcherButton(1019, 495, 30, 160, 60, "Settings"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        ObjUserCosmetics[] userCosmetics = HTTPFunctions.downloadUserCosmetics();

        // double x, double y, double x1, double y1, double radius, int color
        TuxDrawUtils.drawRoundedRect(150, 25, this.width - 150,this.height - 25, 10, new Color(27, 30, 26, 230).getRGB());
        TuxDrawUtils.drawRoundedRect(160, 100, this.width - 300,this.height - 35, 10, new Color(115,120,140, 70).getRGB());

        for (ObjUserCosmetics cosmetic : userCosmetics) {
            // TuxDrawUtils.drawRoundedRect(200, 150, );
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1017) {
            this.mc.displayGuiScreen(new NewModToggle());
        }
    }
}
