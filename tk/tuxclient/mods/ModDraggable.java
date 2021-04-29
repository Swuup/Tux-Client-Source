package tk.tuxclient.mods;

import java.io.File;

import net.minecraft.util.ResourceLocation;
import tk.tuxclient.Files;

public abstract class ModDraggable extends Mod implements IRenderer {

	protected ScreenPosition pos;
	
	public ModDraggable(String name, ResourceLocation icon) {
		super(name, icon);
		pos = loadPositionFromFile();
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		savePositionToFile();
	}
	
	private File getFolder() {
		File folder = new File(Files.getPosFolder(), this.getClass().getSimpleName());
		folder.mkdir();
		return folder;
	}
	
	private void savePositionToFile() {
		Files.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
	}

	private ScreenPosition loadPositionFromFile() {
		
		ScreenPosition loaded = Files.readFromJson(new File(getFolder(), "pos.json"), ScreenPosition.class);
		
		if(loaded == null) {
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			savePositionToFile();
		}
		
		return loaded;
	}
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}

	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}
	
}
