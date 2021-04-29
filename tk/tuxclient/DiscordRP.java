package tk.tuxclient;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRP {
	
	private boolean running = true;
	private long created = 0;
	
	public void start() {
		
		this.created = System.currentTimeMillis();
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
			System.out.println("Added RPC to " + user.username + "#" + user.discriminator + " with the id of " + user.userId + ".");
			update("Idle", "Main Menu");
		}).build();
		DiscordRPC.discordInitialize("807757119478300752", handlers, running);
		new Thread("Discord RPC Callback") {
			@Override
			public void run() {
				while(running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
			
		}.start();
	}
	
	public void shutdown() {
		running = false;
		DiscordRPC.discordShutdown();
		
	}
	
	public void update(String arg1, String arg2) {
		DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(arg2);
		b.setBigImage("large", "Tux Client");
		b.setDetails(arg1);
		b.setStartTimestamps(created);
		DiscordRPC.discordUpdatePresence(b.build());
	}

}