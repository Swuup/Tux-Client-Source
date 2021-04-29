package tk.tuxclient.events.impl;

import tk.tuxclient.events.EventCancelable;

public class KeyEvent extends EventCancelable {

	private final int key;
	
	public KeyEvent(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
}
