package tk.tuxclient.mods.impl.togglesprintsneak;

import net.minecraft.util.ResourceLocation;
import tk.tuxclient.mods.ModDraggable;
import tk.tuxclient.mods.ScreenPosition;

public class ModToggleSprint extends ModDraggable {
  public boolean flyBoost = false;
  
  public float flyBoostFactor = 4.0F;
  
  public int keyHoldTicks = 7;
  
  private String textToRender = "";

  public ModToggleSprint(String name, ResourceLocation icon) {
    super(name, icon);
  }

  public int getWidth() {
    return this.font.getStringWidth(this.textToRender);
  }
  
  public int getHeight() {
    return this.font.FONT_HEIGHT;
  }
  
  public void render(ScreenPosition pos) {
    this.textToRender = this.mc.thePlayer.movementInput.getDisplayText();
    this.font.drawString(this.textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
  }
  
  public void renderDummy(ScreenPosition pos) {
    this.textToRender = "[Example (Toggled)]";
    this.font.drawString(this.textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
  }
}
