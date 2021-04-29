package tk.tuxclient.settings;

import java.util.Arrays;
import java.util.List;


public class ModeSetting extends Setting   {
	
	public List<String> modes;
	public int index;
	
	public ModeSetting(String name, String defaultMode, String... modes) {
		this.name = name;
		this.modes = Arrays.asList(modes);
		index = this.modes.indexOf(defaultMode);
	}
	
	public String getMode() {
		return modes.get(index);
	}
	
	public boolean is(String mode) {
        return index == modes.indexOf(mode) - 1;

    }
	
	public void cycle() {
		if(index < modes.size() -1) {
			index++;
		}else {
			index = 0;
		}
	}

}
