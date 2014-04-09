package frontend.newOrLoginUser;

import base.*;
import dbService.DBServiceImpl;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

/**
 * Created by Александр on 09.04.14.
 */
public class MsgAddUserTest {

    private FrontendImpl frontend;
    private MessageSystem messageSystem;
    private MsgAddUser msg;
    private UserData userData;
    private DBServiceImpl dbService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.messageSystem = new MessageSystemImpl();
        this.frontend = new FrontendImpl(messageSystem);
        this.userData = new UserDataImpl(messageSystem);
        this.dbService = new DBServiceImpl(messageSystem);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        this.dbService.deleteUser("test");
    }

    @Test(timeOut = 50, expectedExceptions = ThreadTimeoutException.class)
    public void testExec() throws Exception {

        Address to=messageSystem.getAddressByName("DBService");
        Address from=messageSystem.getAddressByName("UserData");
        this.msg=new MsgAddUser(from, to, "1q2w3e4r5t", "test", "test");
        messageSystem.putMsg(to, msg);
        this.dbService.run();
    }
}
