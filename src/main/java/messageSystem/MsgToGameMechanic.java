package messageSystem;

import base.Abonent;
import base.Address;
import base.GameMechanic;
import base.Msg;


public abstract class MsgToGameMechanic extends Msg{

	public MsgToGameMechanic(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof GameMechanic){
			exec((GameMechanic)abonent);
            System.out.println("1!!");
		}
        System.out.println("2!!");
	}
	public abstract void exec(GameMechanic gameMechanic);
}