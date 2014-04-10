package chat;

public class ChatMessage {
	String sender, text;
	
	public ChatMessage(String sender, String text){
		this.sender=sender;
		this.text=text;
	}
	
	public String getSender(){
		return sender;
	}
	
	public String getMessage(){
		return text;
	}
	
	public String json(){
		return ("{\"sender\":\""+sender+"\",\"text\":\""+text+"\"}");
	}
}
