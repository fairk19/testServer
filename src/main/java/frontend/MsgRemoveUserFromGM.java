package frontend;

import messageSystem.MsgToGameMechanic;
import base.Address;
import base.GameMechanic;

public class MsgRemoveUserFromGM extends MsgToGameMechanic{
	final private String sessionId;
	
	public MsgRemoveUserFromGM(Address from, Address to, String sessionId){
		super(from, to);
		this.sessionId=sessionId;
	}
	
	public void exec(GameMechanic gameMechanic){
		gameMechanic.removeUser(sessionId);
	}
}
