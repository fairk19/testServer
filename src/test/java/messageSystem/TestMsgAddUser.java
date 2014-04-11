package messageSystem;

import base.Address;
import base.MessageSystem;
import dbService.DBServiceImpl;
import frontend.newOrLoginUser.MsgAddUser;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Created by vanik on 10.04.14.
 */
public class TestMsgAddUser {
    private DBServiceImpl mockedDBService;
    private MessageSystem mockedMS;
    private Address dbAddress;
    private Address frontendAddress;
    private Address from;
    private Address to;
    private String sessionId1;
    private String password1;
    private String nick1;
    private String sessionId2;
    private String password2;
    private String nick2;



    @BeforeGroups("AddUser")
    public void setUpAddUser() {
        nick1 = "nick1";
        sessionId1 = "sessionId1";
        password1 = "password1";

        nick2 = "nick2";
        sessionId2 = "sessionId2";
        password2 = "password2";


        frontendAddress = new Address();
        dbAddress = new Address();

        from = frontendAddress;
        to = dbAddress;
        mockedMS = mock(MessageSystem.class);

        mockedDBService = mock(DBServiceImpl.class);
        when(mockedDBService.addUDS(nick1, password1)).thenReturn(true);
        when(mockedDBService.addUDS(nick2, password2)).thenReturn(false);
        when(mockedDBService.getMessageSystem()).thenReturn(mockedMS);

        MsgAddUser msg = new MsgAddUser(frontendAddress, dbAddress, sessionId1, nick1, password1);
        msg.exec(mockedDBService);

        MsgAddUser msgNotFound = new MsgAddUser(frontendAddress, dbAddress, sessionId2, nick2, password2);
        msgNotFound.exec(mockedDBService);
    }

    @Test(groups = "AddUser")
    public void testAddUser() {
        verify(mockedDBService).getUDS(nick1, password1);
        verify(mockedDBService, never()).getUDS(nick2, password2);

    }

    @AfterGroups("AddUser")
    public void tearDown() {

    }

}
