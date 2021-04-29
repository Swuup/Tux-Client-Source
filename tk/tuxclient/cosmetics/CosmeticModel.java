package tk.tuxclient.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class CosmeticModel extends ModelBase implements LayerRenderer<AbstractClientPlayer> {
	public static RenderPlayer playerRenderer;
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public String name;
	public boolean wearing;
	
	public CosmeticModel(String name, RenderPlayer playerRenderer) {
		this.name = name;
		this.playerRenderer = playerRenderer;
		
		this.wearing = true;
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
