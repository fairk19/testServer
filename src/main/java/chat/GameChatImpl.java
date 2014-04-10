package chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import utils.TimeHelper;

import base.Address;
import base.GameChat;
import base.MessageSystem;

import dbService.UserDataSet;

import frontend.UserDataImpl;

public class GameChatImpl implements GameChat{

	private static final Map<String, List<ChatMessage>> sessionIdToChat = 
			new HashMap<String, List<ChatMessage>>();
	private static final Map<String, String> sessionIdToAnotherSessionId = 
			new HashMap<String, String>();
	private MessageSystem messageSystem;
	private Address address = new Address();

	public GameChatImpl(MessageSystem messageSystem){
		this.messageSystem = messageSystem;
		messageSystem.addService(this, "GameChat");
	}

	public Address getAddress(){
		return address;
	}

	public MessageSystem getMessageSystem(){
		return messageSystem;
	}

	public void createChat(String sessionId1, String sessionId2){
		List<ChatMessage> chat = new Vector<ChatMessage>();
		sessionIdToChat.put(sessionId1, chat);
		sessionIdToChat.put(sessionId2, chat);
		sessionIdToAnotherSessionId.put(sessionId1, sessionId2);
		sessionIdToAnotherSessionId.put(sessionId2, sessionId1);
	}

	public static void sendMessage(String sessionId, String text){
		UserDataSet sender = UserDataImpl.getLogInUserBySessionId(sessionId);
		ChatMessage message = new ChatMessage(sender.getNick(), text);
        System.out.println("text"+text);
		if(sessionIdToChat.get(sessionId)!=null){
			sessionIdToChat.get(sessionId).add(message);
			ChatWSImpl.sendMessage(sessionId, message.json());
			String anotherSessionId = sessionIdToAnotherSessionId.get(sessionId);
			ChatWSImpl.sendMessage(anotherSessionId, message.json());
		}
	}
    public static void putSessionIdToChat(String sessionId, List<ChatMessage> listChatMessage) {
        sessionIdToChat.put(sessionId, listChatMessage);
    }
    public static void clearAllMaps() {
        sessionIdToChat.clear();
        sessionIdToAnotherSessionId.clear();
    }

    public void methodRun(int timeOut){
        messageSystem.execForAbonent(this);
        TimeHelper.sleep(timeOut);
    }
	public void run(){
		while(true){
			methodRun(200);
		}
	}
}
