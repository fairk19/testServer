package base;

public interface AccountService extends Abonent,Runnable{
	public int getUserId(String nick, String password);
	public MessageSystem getMessageSystem();
}