package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tk.tuxclient.Tux;
import tk.tuxclient.gui.mainmenu.*;
import tk.tuxclient.gui.screens.GuiAccountSwitcher;
import tk.tuxclient.util.TuxDrawUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback
{

    /** Counts the number of screen updates. */
    private float updateCounter;

    /**
     * The Object object utilized as a thread lock when performing non thread-safe operations
     */
    private final Object threadLock = new Object();



    /** An array of all the paths to the panorama pictures. */
    public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;

    /** Minecraft Realms button. */
    private GuiButton realmsButton;
    private boolean L;
    private GuiScreen M;

    public GuiMainMenu()
    {
        this.L = false;
        BufferedReader bufferedreader = null;
    }

    private boolean a()
    {
        return Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && this.M != null;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        if (this.a())
        {
            this.M.updateScreen();
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Tux.getInstance().getDiscordRP().update("Idle", "Main Menu");
        this.addButtons(this.height / 2 + (48 - 70), 24);
    }

    /**
     * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have bought the game.
     */
    private void addButtons(int p_73969_1_, int p_73969_2_)
    {
        this.buttonList.add(new TuxMainMenuButton(1, this.width / 2 - 50, p_73969_1_ + 45, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new TuxMainMenuButton(2, this.width / 2 - 50, p_73969_1_ + p_73969_2_ * 1 + 44, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new TuxSettingsButton(0, 4, this.height / (this.height * 2) + 4, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new TuxQuitButton(3, this.width - 25, this.height / (this.height * 2) + 4, I18n.format("", new Object[0])));
        this.buttonList.add(new TuxStoreButton(1000, 30, this.height / (this.height * 2) + 4, 20, 20, ""));
        this.buttonList.add(new TuxAccountButton(1001, this.width - 50, this.height / (this.height * 2) + 4, 20, 20, ""));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0) { this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings)); }
        if (button.id == 1) { this.mc.displayGuiScreen(new GuiSelectWorld(this)); }
        if (button.id == 2) { this.mc.displayGuiScreen(new GuiMultiplayer(this)); }
        if (button.id == 3) { this.mc.shutdown(); }
        if (button.id == 1000) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://tuxclient.tk/store"));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        if (button.id == 1001) {
            this.mc.displayGuiScreen(new GuiAccountSwitcher(this));
        }
    }


    private static final ResourceLocation background = new ResourceLocation("tux/background.png");
    private float x, y, targetX, targetY;

    /*
     * Draws the menus background image
     */
    private void drawBackground(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc.getMinecraft());

        GlStateManager.pushMatrix();

        float xDiff = ((mouseX - sr.getScaledWidth() / 2) - x ) / sr.getScaleFactor();
        float yDiff = ((mouseY - sr.getScaledHeight() / 2) - y) / sr.getScaleFactor();

        x += xDiff * 0.3;
        y += yDiff * 0.3;

        GlStateManager.translate(x / 64, y / 64, 0);
        mc.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(-10, -10, 0, 0, sr.getScaledWidth() + 20, sr.getScaledHeight() + 20, sr.getScaledWidth() + 20, sr.getScaledHeight() + 20);
        GlStateManager.bindTexture(0);

        GlStateManager.translate(-x / 100, -y / 100, 0);
        GlStateManager.translate(x / 50, y / 50, 0);

        GlStateManager.popMatrix();
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

        // Draws background and Gradient
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();

        drawBackground(mouseX, mouseY);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        //this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        TuxDrawUtils.drawRoundedRect(this.width / 2 - (70), this.height / 2 - (10 + 70), this.width / 2 + (70 + 1), this.height / 2 + (160 - 70), 6.4, 0x64ffffff);

        this.mc.getTextureManager().bindTexture(new ResourceLocation("tux/icon/uptext.png"));

        GlStateManager.clearColor(1.0F, 1.0F, 1.0F, 1.0F);

        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTexParameteri(3553, 10240, 9729);
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - (int) (60), this.height / 2 - (20 + 70), 0.0F, 0.0F, 120, 120, 120, 120);
        GL11.glDisable(3042);
        GL11.glPopMatrix();

        String s = "Tux Client 1.8.9";
        Tux.getInstance().getFontRenderer().drawString(s, 2, this.height - 10, -1);

        String s1 = "Copyright Tux Client.";
        Tux.getInstance().getFontRenderer().drawString(s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        if (this.M != null)
        {
            this.M.onGuiClosed();
        }
    }
}
