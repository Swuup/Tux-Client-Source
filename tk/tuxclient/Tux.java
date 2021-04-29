package tk.tuxclient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Mouse;
import tk.tuxclient.cosmetics.CosmeticController;
import tk.tuxclient.cosmetics.CosmeticManager;
import tk.tuxclient.cosmetics.impl.SnowmanCape;
import tk.tuxclient.events.EventManager;
import tk.tuxclient.events.EventTarget;
import tk.tuxclient.events.impl.ClientTickEvent;
import tk.tuxclient.gui.screens.GuiHWIDBanned;
import tk.tuxclient.gui.screens.GuiWhitelisted;
import tk.tuxclient.mods.HUDManager;
import tk.tuxclient.mods.ModInstances;

import tk.tuxclient.mods.impl.ModFPS;
import tk.tuxclient.util.font.TuxFontRenderer;
import tk.tuxclient.util.http.HTTPFunctions;
import tk.tuxclient.util.http.gson.ObjGlobalSettings;

import java.util.Random;

public class Tux {


	public static final Tux INSTANCE = new Tux();
	private static TuxFontRenderer font;
	public int mouse_wheel;
	private HUDManager hudManager;
	private double time = 0;
	private final Minecraft mc = Minecraft.getMinecraft();
	
	public static Tux getInstance() {
		return INSTANCE;
	}
	
	private final DiscordRP discordRP = new DiscordRP();

	private volatile boolean isBanned;
	public boolean isWhitelisted;
	private ObjGlobalSettings globalSettings;
	public CosmeticManager cosmeticManager;

	public void init() {

		if (!HTTPFunctions.isApiUp()) {
			mc.shutdownMinecraftApplet();
		}

		Files.init();
		discordRP.start();
		hudManager = HUDManager.getInstace();
		cosmeticManager = new CosmeticManager();
		EventManager.register(this);

		isBanned = HTTPFunctions.isBanned();
		isWhitelisted = HTTPFunctions.isWhitelisted();
		globalSettings = HTTPFunctions.downloadGlobalSettings();
		CosmeticController.downloadUserCosmetics();
		LogManager.getLogger().info("[Tux Client MySQL] Connected");
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	public void start() {
		font = new TuxFontRenderer(new ResourceLocation("tux/font/Quicksand-Medium.ttf"), 18.0F);

		ModInstances.register(hudManager);
		HTTPFunctions.sendHWIDMap();
	}
	
	public final TuxFontRenderer getFontRenderer() {
		return font;
	}

	@EventTarget
	public void tick(ClientTickEvent tick) {
		this.mouse_wheel = Mouse.getDWheel();
		if (time == 5) {
			time = 0;
		} else {
			time++;
		}

		if (mc.gameSettings.modKeyBind.isPressed()) {
			hudManager.openConfigScreen();
		}

		if (isBanned && !(Minecraft.getMinecraft().currentScreen instanceof GuiHWIDBanned)) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiHWIDBanned());
		}

	}

	public int getMouseX() {
	      int i = Mouse.getX();
	      if((double)getInstance().getRealScaleFactor() <= 2.0D) {
	         i = (int)((double)i / 2.0D);
	      } else {
	         i = i / getInstance().getRealScaleFactor();
	      }

	      return i;
	   }
	
	public int getMouseY() {
	      int i = Minecraft.getMinecraft().displayHeight - Mouse.getY();
	      if((double)getInstance().getRealScaleFactor() <= 2.0D) {
	         i = (int)((double)i / 2.0D);
	      } else {
	         i = i / getInstance().getRealScaleFactor();
	      }

	      return i;
	   }

	public int getRealScaleFactor() {
	      return Math.max(2, (new ScaledResolution(Minecraft.getMinecraft())).getScaleFactor());
	   }
}
