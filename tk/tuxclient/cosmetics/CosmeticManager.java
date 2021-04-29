package tk.tuxclient.cosmetics;

import java.util.ArrayList;

import tk.tuxclient.cosmetics.impl.*;

public class CosmeticManager {
	
	public ArrayList<Cosmetic> cosmetics = new ArrayList<>();
	public ArrayList<CosmeticModel> cosmeticModels = new ArrayList<>();
	
	public static SnowmanCape snowmanCape;
	public static CosmeticWings cosmeticWings;
	
	public CosmeticManager() {
		cosmetics.add(snowmanCape = new SnowmanCape(SnowmanCape.playerRenderer));
		cosmeticModels.add(cosmeticWings = new CosmeticWings(CosmeticWings.playerRenderer));
	}
	
	public static SnowmanCape getSnowmanCape() {
		return snowmanCape;
	}
	
	public static CosmeticWings getCosmeticWings() {
		return cosmeticWings;
	}

}
