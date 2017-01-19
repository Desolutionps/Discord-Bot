package com.cryo;

import com.cryo.listener.CommandListener;

import sx.blah.discord.handle.obj.IMessage;

public class DefaultCommandListener extends CommandListener {

	public DefaultCommandListener(DiscordBot bot, String commandPrefix) {
		super(bot, commandPrefix);
	}

	@Override
	public void handleCommand(IMessage message, String command, String[] cmd) {
		cmd[0] = cmd[0].toLowerCase();
		String channelId = message.getChannel().getID();
		switch(cmd[0]) {
		case "test":
			String author_id = message.getAuthor().getID();
			bot.deleteMessage(message);
			bot.sendMessage(channelId, "UserID: "+author_id);
			break;
		}
	}

}
