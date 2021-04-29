package tk.tuxclient.gui.mods.settings;

import net.minecraft.client.gui.GuiScreen;
import tk.tuxclient.mods.Mod;

public class GuiSettings extends GuiScreen {

    private Mod mod;

    public GuiSettings(Mod mod) {
        this.mod = mod;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
