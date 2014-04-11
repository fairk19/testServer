package messageSystem;

import base.Address;
import dbService.DBServiceImpl;
import dbService.MysqlConnectionCreator;
import frontend.UserDataImpl;
import frontend.newOrLoginUser.MsgAddUser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Александр on 09.04.14.
 */
public class MsgToUserDataTest {

    private DBServiceImpl dbService;
    private MessageSystemImpl messageSystem;
    private UserDataImpl userData;

    @BeforeMethod
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        dbService = new DBServiceImpl(messageSystem, MysqlConnectionCreator.getConnection());
        userData = new UserDataImpl(messageSystem);
    }

    @Test
    public void testExec() throws Exception {
        Address to = messageSystem.getAddressByName("DBService");
        Address from = messageSystem.getAddressByName("UserData");
        MsgAddUser msg = new MsgAddUser(from, to,"1q2w3e4r5t", "test", "test");
        messageSystem.putMsg(to, msg);
        dbService.methodRun(0);
    }
}
