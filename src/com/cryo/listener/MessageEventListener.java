package com.cryo.listener;

import com.cryo.DiscordBot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

public class MessageEventListener {
	
	private DiscordBot bot;
	
	public MessageEventListener(DiscordBot bot) {
		this.bot = bot;
	}
	
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		if(bot.getCommandListener() == null)
			return;
		String prefix = bot.getCommandListener().getCommandPrefix();
		if(event.getMessage().getContent().startsWith(prefix)) {
			String command = event.getMessage().getContent().replaceFirst(prefix, "");
			String[] cmd = command.split(" ");
			bot.getCommandListener().handleCommand(event.getMessage(), command, cmd);
		}
	}

}
