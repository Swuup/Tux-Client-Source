package tk.tuxclient.mods.impl.togglesprintsneak;

import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import tk.tuxclient.mods.ModInstances;

public class Movement extends MovementInput {
  private boolean sprint = false;
  
  private GameSettings gameSettings;
  
  private int sneakWasPressed = 0;
  
  private int sprintWasPressed = 0;
  
  private EntityPlayerSP player;
  
  private float originalFlySpeed = -1.0F;
  
  private float boostedFlySpeed = 1.0F;
  
  private Minecraft mc;
  
  public Movement(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
    this.mc = Minecraft.getMinecraft();
  }
  
  public void updatePlayerMoveState() {
    this.player = this.mc.thePlayer;
    this.moveStrafe = 0.0F;
    this.moveForward = 0.0F;
    if (this.gameSettings.keyBindForward.isKeyDown())
      this.moveForward++; 
    if (this.gameSettings.keyBindBack.isKeyDown())
      this.moveForward--; 
    if (this.gameSettings.keyBindLeft.isKeyDown())
      this.moveStrafe++; 
    if (this.gameSettings.keyBindRight.isKeyDown())
      this.moveStrafe--; 
    this.jump = this.gameSettings.keyBindJump.isKeyDown();
    this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
    if (this.sneak) {
      this.moveStrafe *= 0.3F;
      this.moveForward *= 0.3F;
    } 
    if (ModInstances.getModToggleSprint().isEnabled()) {
      if (this.gameSettings.keyBindSprint.isKeyDown()) {
        if (this.sprintWasPressed == 0) {
          if (this.sprint) {
            this.sprintWasPressed = -1;
          } else if (this.player.capabilities.isFlying) {
            this.sprintWasPressed = (ModInstances.getModToggleSprint()).keyHoldTicks + 1;
          } else {
            this.sprintWasPressed = 1;
          } 
          this.sprint = !this.sprint;
        } else if (this.sprintWasPressed > 0) {
          this.sprintWasPressed++;
        } 
      } else {
        if ((ModInstances.getModToggleSprint()).keyHoldTicks > 0 && this.sprintWasPressed > (ModInstances.getModToggleSprint()).keyHoldTicks)
          this.sprint = false; 
        this.sprintWasPressed = 0;
      } 
    } else {
      this.sprint = false;
    } 
    if (this.sprint && this.moveForward == 1.0F && this.player.onGround && !this.player.isUsingItem() && !this.player.isPotionActive(Potion.blindness))
      this.player.setSprinting(true); 
    if ((ModInstances.getModToggleSprint()).flyBoost && this.player.capabilities.isCreativeMode && this.player.capabilities.isFlying && this.mc.getRenderViewEntity() == this.player && this.sprint) {
      if (this.originalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != this.boostedFlySpeed)
        this.originalFlySpeed = this.player.capabilities.getFlySpeed(); 
      this.boostedFlySpeed = this.originalFlySpeed * (ModInstances.getModToggleSprint()).flyBoostFactor;
      this.player.capabilities.setFlySpeed(this.boostedFlySpeed);
      if (this.sneak)
        this.player.motionY -= 0.15D * ((ModInstances.getModToggleSprint()).flyBoostFactor - 1.0F); 
      if (this.jump)
        this.player.motionY += 0.15D * ((ModInstances.getModToggleSprint()).flyBoostFactor - 1.0F); 
    } else {
      if (this.player.capabilities.getFlySpeed() == this.boostedFlySpeed)
        this.player.capabilities.setFlySpeed(this.originalFlySpeed); 
      this.originalFlySpeed = -1.0F;
    } 
  }
  
  private static final DecimalFormat df = new DecimalFormat("#.0");
  
  public String getDisplayText() {
    String displayText = "";
    boolean isFlying = this.mc.thePlayer.capabilities.isFlying;
    boolean isRiding = this.mc.thePlayer.isRiding();
    boolean isHoldingSneak = this.gameSettings.keyBindSneak.isKeyDown();
    boolean isHoldingSprint = this.gameSettings.keyBindSprint.isKeyDown();
    if (isFlying)
      if (this.originalFlySpeed > 0.0F) {
        displayText = String.valueOf(displayText) + "[Flying (" + df.format((this.boostedFlySpeed / this.originalFlySpeed)) + "x Boost)]  ";
      } else {
        displayText = String.valueOf(displayText) + "[Flying]  ";
      }  
    if (isRiding) {
      displayText = String.valueOf(displayText) + "[Riding]  ";
    } else if (this.sprint && !isFlying && !isRiding) {
      if (isHoldingSprint) {
        displayText = String.valueOf(displayText) + "[Sprinting (Key Held)]  ";
      } else {
        displayText = String.valueOf(displayText) + "[Sprinting (Key Toggled)]  ";
      } 
    } 
    return displayText.trim();
  }
}
