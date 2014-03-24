package messageSystem;

import base.Abonent;
import base.Address;
import base.Msg;
import base.WebSocket;


public abstract class MsgToWebSocket extends Msg{

	public MsgToWebSocket(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof WebSocket){
			exec((WebSocket)abonent);
		}
	}
	public abstract void exec(WebSocket webSocket);
}