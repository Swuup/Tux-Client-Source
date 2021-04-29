package tk.tuxclient.events;

public class EventCancelable extends Event {
	
	private boolean cancelled = false;
	
	public boolean isCanceled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
