package base;

import java.util.List;

import dbService.UserDataSet;
import base.Abonent;

public interface DataAccessObject extends Abonent,Runnable{
	public MessageSystem getMessageSystem();
	public UserDataSet getUDS(final String login,String password);
	public boolean addUDS(final String login,String password);
	public void updateUsers(List<UserDataSet> users);
}