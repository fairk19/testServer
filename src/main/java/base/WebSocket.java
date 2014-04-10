package base;

import gameClasses.Snapshot;
import gameClasses.Stroke;

import java.util.Map;


public interface WebSocket extends Abonent,Runnable{
	public void sendStroke(Map<Integer,Stroke> userIdToStroke);
	public void doneSnapshot(int id, Snapshot snapshot);
	public void updateUsersColor(Map<String, String> usersToColors);
}