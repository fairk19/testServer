package userDataImpl;

import base.MessageSystem;
import base.UserData;
import base.WebSocket;
import chat.ChatWSImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vanik on 07.04.14.
 */
public class TestUserDataImpl {
    private String startServerTime;
    private int userID;
    private String sessionId1 = "sessionId1";
    private String sessionId2 = "sessionId2";
    private UserDataImpl userDataImpl;
    private UserDataSet userDataSet;
    private UserDataSet userDataSet1;
    private UserDataSet userDataSet2;
    private int userID1;
    private int userID2;
    private ChatWSImpl chatWS;
    private WebSocketImpl ws;
    private Socket socket;
    private RemoteEndpoint remoteEndPoint;
    private MessageSystem mockedMS;

    @BeforeGroups("CheckServerTime")
    public void setUpCheckServerTime() {
        startServerTime = UserDataImpl.getStartServerTime();
    }

    @Test( groups = "CheckServerTime")
    public void testCheckServerTime() {
        String unCorrectValue = "1231dfa";
//        String correctValue = startServerTime
        Assert.assertTrue(UserDataImpl.checkServerTime(startServerTime));
        Assert.assertFalse(UserDataImpl.checkServerTime(unCorrectValue));

//        Assert.assertEquals(UserDataImpl.checkServerTime(unCorrectValue), unCorrectValue);
    }


    @BeforeGroups("GetSessionIdByUserId")
    public void setUpGetSessionIdByUserId() {
        userID = 1;
        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";
        UserDataImpl.putLogInUser(sessionId1,new UserDataSet(userID,"user1", 1, 1, 1));
        UserDataImpl.putLogInUser(sessionId2,new UserDataSet(userID+1,"user2", 2, 2, 2));

    }

    @Test(groups = "GetSessionIdByUserId")
    public void testGetSessionIdByUserId() {
        Assert.assertEquals(UserDataImpl.getSessionIdByUserId(userID), sessionId1);
        Assert.assertNull(UserDataImpl.getSessionIdByUserId(-1));
    }

    @AfterGroups("GetSessionIdByUserId")
    public void tearDownGetSessionIdByUserId() {
        UserDataImpl.removeUserFromLogInUsers(sessionId1);
        UserDataImpl.removeUserFromLogInUsers(sessionId2);
    }



    @BeforeGroups("GetLogInUserBySessionId")
    public void setUpGetLogInUserBySessionId() {
        sessionId1 = "sessionId1";
        userID = 1;
        userDataSet = new UserDataSet(userID, "user1", 1, 1, 1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet);
    }

    @Test(groups = "GetLogInUserBySessionId")
    public void testGetLogInUserBySessionId() {
        Assert.assertEquals(UserDataImpl.getLogInUserBySessionId(sessionId1), userDataSet);
    }

    @AfterGroups("GetLogInUserBySessionId")
    public void tearDownGetLogInUserBySessionId() {
        UserDataImpl.removeUserFromLogInUsers(sessionId1);
    }




    @BeforeGroups("PartyEnd")
    public void setUpPartyEnd() {
        mockedMS = mock(MessageSystem.class);
        chatWS = mock(ChatWSImpl.class);
        userDataImpl = new UserDataImpl(mockedMS);
        userID1 = 1;
        userID2 = 2;

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getId()).thenReturn(userID1);
        UserDataImpl.putSessionIdAndUserSession(sessionId1,userDataSet1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);
        UserDataImpl.putSessionIdAndChatWS(sessionId1, chatWS);


        sessionId2 = "sessionId2";
        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getId()).thenReturn(userID2);
        UserDataImpl.putSessionIdAndUserSession(sessionId2,userDataSet2);
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);


    }

    @Test(groups = "PartyEnd")
    public void testPartyEnd() {
        userDataImpl.partyEnd(userID1, userID2);
        verify(mockedMS).getAddressByName("DBService");
    }

    @AfterGroups("PartyEnd")
    public void tearDownPartyEnd() {

    }

//    @BeforeGroups("GetWSBySessionId")
//    public void setUpGetWSBySessionId() {
//        ws = mock(WebSocketImpl.class);
//        remoteEndPoint = mock(RemoteEndpoint.class);
//        session  = mock(Session.class);
//        when(session.)
//        when(ws.getSession().getRemote()).thenReturn(remoteEndPoint);
//        sessionId1 = "sessionId1";
//        UserDataImpl.putSessionIdAndWS(sessionId1, ws);
//
//    }
//
//    @Test(groups = "GetWSBySessionId")
//    public void testGetWSBySessionId() {
//        UserDataImpl.getWSBySessionId(sessionId1);
//        verify(ws).getSession().getRemote();
//    }
//
//    @AfterGroups("GetWSBySessionId")
//    public void tearDownGetWSBySessionId() {
//
//    }






    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

    }


}
