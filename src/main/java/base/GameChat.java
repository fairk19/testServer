package base;

public interface GameChat extends Runnable, Abonent {
	public MessageSystem getMessageSystem();
	public void createChat(String sessionId1, String sessionId2);
}
