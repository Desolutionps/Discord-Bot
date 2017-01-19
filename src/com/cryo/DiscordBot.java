package com.cryo;

import com.cryo.listener.CommandListener;
import com.cryo.listener.MessageEventListener;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class DiscordBot {
	
	private IDiscordClient client;
	
	private DiscordSettings settings;
	
	private CommandListener commandListener;
	
	//example usage
	public static void main(String[] args) {
		DiscordBot bot = new DiscordBot(DiscordSettings.build("token"));
		bot.run();
		bot.registerCommandListener(new DefaultCommandListener(bot, "."));
	}
	
	public DiscordBot(DiscordSettings settings) {
		this.settings = settings;
	}
	
	public void run() {
		ClientBuilder builder = new ClientBuilder();
		builder.withToken(settings.getClientToken());
		try {
			builder.setMaxReconnectAttempts(0);
			client = builder.login();
			EventDispatcher dispatcher = client.getDispatcher();
			dispatcher.registerListener(new MessageEventListener(this));
		} catch (DiscordException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMessage(IMessage message) {
		try {
			message.delete();
		} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String channelId, String message) {
		try {
			client.getChannelByID(channelId).sendMessage(message);
		} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			e.printStackTrace();
		}
	}
	
	public IDiscordClient getClient() {
		return client;
	}
	
	public CommandListener getCommandListener() {
		return commandListener;
	}
	
	public void registerCommandListener(CommandListener listener) {
		this.commandListener = listener;
	}
	
	public DiscordSettings getSettings() {
		return settings;
	}

}
