package org.androidpn.server.service;

import org.androidpn.server.model.Chat;
import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.push.NotificationManager;
import org.androidpn.server.xmpp.session.SessionManager;
import org.dom4j.Element;
import org.xmpp.packet.JID;

public class ChatService {
	private NotificationManager notificationManager;
	private SessionManager sessionManager;

	public ChatService() {
		sessionManager = SessionManager.getInstance();
		notificationManager = new NotificationManager();
	}

	public void sendMessage(Chat packet) {
		Element element = packet.getElement();
		Element msg = element.element("message");
		String message = msg.element("content").getText();
		String nickname = msg.element("nickname").getText();
		String apiKey = Config.getString("apiKey", "");
		JID sender = packet.getFrom();
		try {
			System.out.println("·¢ËÍÕßsession>>>>>>>>>>" + sessionManager.getSession(sender).getId());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		notificationManager.sendAllBroadcast(apiKey, nickname, message, "");
	}

}
