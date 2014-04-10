package gameMechanic.gameCreating;

import java.util.Map;

import base.Address;
import base.WebSocket;
import messageSystem.MsgToWebSocket;


public class MsgUpdateColors extends MsgToWebSocket{
	final private Map<String,String> usersToColors;

	public MsgUpdateColors(Address from, Address to, Map<String, String> data){
		super(from,to);
		usersToColors=data;
	}

	public void exec(WebSocket webSocket){
		webSocket.updateUsersColor(usersToColors);
	}
}