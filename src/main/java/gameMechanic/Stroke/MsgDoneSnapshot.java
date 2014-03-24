package gameMechanic.Stroke;

import gameClasses.Snapshot;

import base.Address;
import base.WebSocket;
import messageSystem.MsgToWebSocket;


public class MsgDoneSnapshot extends MsgToWebSocket{
	final private Snapshot snapshot;
	final private int id;
	public MsgDoneSnapshot(Address from, Address to, int idIn, Snapshot data){
		super(from,to);
		snapshot=data;
		id=idIn;
	}

	public void exec(WebSocket webSocket){
		webSocket.doneSnapshot(id, snapshot);
	}
}