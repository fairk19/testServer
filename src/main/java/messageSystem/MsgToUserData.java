package messageSystem;

import base.Abonent;
import base.Address;
import base.Msg;
import base.UserData;


public abstract class MsgToUserData extends Msg{

	public MsgToUserData(Address from, Address to){
		super(from,to);
	}

	public void exec(Abonent abonent){
		if (abonent instanceof UserData){
            System.out.println("1!!");
			exec((UserData)abonent);
		}
        System.out.println("2!!");

	}
	public abstract void exec(UserData frontend);
}