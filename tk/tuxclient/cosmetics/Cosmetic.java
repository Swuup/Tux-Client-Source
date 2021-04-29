package tk.tuxclient.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class Cosmetic implements LayerRenderer<AbstractClientPlayer> {
	
	public RenderPlayer playerRenderer;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public String name;
	public boolean wearing, cape;
	
	public Cosmetic(String name, boolean cape, RenderPlayer playerRenderer) {
		this.name = name;
		this.cape = cape;
		this.playerRenderer = playerRenderer;
		this.wearing = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCape() {
		return cape;
	}

	public void setCape(boolean cape) {
		this.cape = cape;
	}

	public void setWearing(boolean newWearing) {
		wearing = newWearing;
	}
	
	public boolean isWearing() {
		return wearing;
	}
	
	public void toggleWearing() {
		wearing = !wearing;
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
		float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if(player.hasPlayerInfo() && !player.isInvisible()) {
			render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
		}
	}
	
	public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);
}
