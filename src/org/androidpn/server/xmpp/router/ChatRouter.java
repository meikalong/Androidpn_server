package org.androidpn.server.xmpp.router;

import org.androidpn.server.model.Chat;
import org.androidpn.server.service.ChatService;

public class ChatRouter {

	private ChatService chatService;

	/**
	 * Constucts a packet router registering new IQ handlers.
	 */
	public ChatRouter() {
		chatService = new ChatService();
	}

	public void route(Chat packet) {
		if (packet == null) {
			throw new NullPointerException();
		}
		chatService.sendMessage(packet);
	}

}
