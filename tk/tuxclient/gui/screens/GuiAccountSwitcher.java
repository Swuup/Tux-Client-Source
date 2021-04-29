package tk.tuxclient.gui.screens;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import tk.tuxclient.Tux;
import tk.tuxclient.gui.account.TuxPassword;
import tk.tuxclient.gui.account.TuxTextField;
import tk.tuxclient.gui.mainmenu.TuxMainMenuButton;
import tk.tuxclient.gui.mainmenu.TuxQuitButton;
import tk.tuxclient.util.TuxDrawUtils;

import java.awt.*;
import java.io.IOException;
import java.net.Proxy;

public class GuiAccountSwitcher extends GuiScreen {

    private boolean wasRun;
    private TuxTextField email;
    private TuxPassword password;
    private String currentPassword;
    private GuiScreen parent;

    public GuiAccountSwitcher(GuiScreen mainMenu) {
        parent = mainMenu;
    }

    private void addButtons() {
        this.buttonList.add(new TuxQuitButton(1004, this.width / 2 + (60), this.height / 2 - (10 + 80), I18n.format("", new Object[0])));
        this.buttonList.add(new TuxMainMenuButton(1005, this.width / 2 - 50, this.height / 2 + (160 - 100), "Login"));
    }

    @Override
    public void initGui() {
        wasRun = false;
        addButtons();
        email = new TuxTextField(1002, Tux.getInstance().getFontRenderer(), this.width / 2 - 70, this.height / 2 - 10, 140, Tux.getInstance().getFontRenderer().getHeight() + 15);
        email.setFocused(true);
        email.setText("");
        password = new TuxPassword(1003, Tux.getInstance().getFontRenderer(), this.width / 2 - 70, this.height / 2 + 20, 140, Tux.getInstance().getFontRenderer().getHeight() + 15);
        password.setFocused(false);
        password.setText("");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if (!wasRun) {
            TuxDrawUtils.drawRoundedRect(this.width / 2 - (80), this.height / 2 - (10 + 80), this.width / 2 + (80 + 1), this.height / 2 + (160 - 60), 6.4, new Color(30, 30, 30).getRGB());
            Tux.getInstance().getFontRenderer().drawCenteredString("Account Switcher", this.width / 2, this.height / 2 - 50, -1);
            wasRun = true;
        }
        email.drawTextBox();
        password.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (email.isFocused()) {
            email.textboxKeyTyped(typedChar, keyCode);
        } else if (password.isFocused()) {
                password.textboxKeyTyped(typedChar, keyCode);
        }
        if (keyCode == Keyboard.KEY_RETURN) {
            if (email.isFocused()) {
                email.setFocused(false);
                password.setFocused(true);
            } else if (password.isFocused()) {
                currentPassword = password.getPassword();
                if (email.getText() != null && !email.getText().isEmpty()) {
                    try {
                        final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
                        authentication.setPassword(currentPassword);
                        authentication.setUsername(email.getText());
                        authentication.logIn();
                        mc.session = new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang");
                        this.mc.displayGuiScreen(parent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (keyCode == Keyboard.KEY_BACK) {
            if (email.isFocused()) {
                email.deleteFromCursor(1);
            } else {
                password.deleteFromCursor(1);
            }
        }
        super.keyTyped(typedChar, keyCode);
    }


    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1004) {
            this.mc.displayGuiScreen(parent);
        }
        if (button.id == 1005) {
            if (email.getText() != null && !email.getText().isEmpty()) {
                try {

                    final YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
                    authentication.setPassword(currentPassword);
                    authentication.setUsername(email.getText());
                    authentication.logIn();
                    mc.session = new Session(authentication.getSelectedProfile().getName(), authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(), "mojang");
                    this.mc.displayGuiScreen(parent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateScreen() {
        email.updateCursorCounter();
    }
}
