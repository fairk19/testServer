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

import static org.mockito.Mockito.*;

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
    private WebSocketImpl mockedWSImpl;
    private Session mockedSession;
    private RemoteEndpoint mockedRemote ;
    private MessageSystem mockedMS;

    @BeforeGroups("CheckServerTime")
    public void setUpCheckServerTime() {
        startServerTime = UserDataImpl.getStartServerTime();
    }

    @Test( groups = "CheckServerTime")
    public void testCheckServerTime() {
        String unCorrectValue = "1231dfa";
        Assert.assertTrue(UserDataImpl.checkServerTime(startServerTime));
        Assert.assertFalse(UserDataImpl.checkServerTime(unCorrectValue));
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







    @BeforeGroups("PutSessionIdAndChatWS")
    public void setUpPutSessionIdAndChatWS() {
        sessionId1 = "sessionId1";
        chatWS = mock(ChatWSImpl.class);

        sessionId2 = "sessionId2";

        userDataSet1 = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

    }

    @Test(groups = "PutSessionIdAndChatWS")
    public void testPutSessionIdAndChatWS() {
        UserDataImpl.putSessionIdAndChatWS(sessionId1,chatWS);
        verify(userDataSet1).visit();
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);
        verify(userDataSet2, never()).visit();
    }

    @AfterGroups("PutSessionIdAndChatWS")
    public void tearDownPutSessionIdAndChatWS() {

    }






    @BeforeGroups("UpdateUserId")
    public void setUpUpdateUserId() {
        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);
        userID = 1;

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getId()).thenReturn(userID);
        UserDataImpl.putSessionIdAndUserSession(sessionId1, userDataSet);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        sessionId2 = "sessionId2";
        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getId()).thenReturn(userID);
        UserDataImpl.putSessionIdAndUserSession(sessionId2, userDataSet2);

        System.out.println("before 1= " + userDataSet1.hashCode());
        System.out.println("before 2= " + userDataSet2.hashCode());
    }

    @Test(groups = "UpdateUserId")
    public void testUpdateUserId() {
        userDataImpl.updateUserId(sessionId2, userDataSet2);
        System.out.println("after 1= " + userDataSet1.hashCode());
        System.out.println("after 2= " + userDataSet2.hashCode());
        verify(userDataSet2).makeLike(userDataSet2);
        verify(userDataSet2).setPostStatus(0);
    }

    @AfterGroups("UpdateUserId")
    public void tearDownUpdateUserId() {
        UserDataImpl.clearSessionIdAndrUserSession();
    }






    @BeforeGroups("UpdateUserIdOldUserIsNull")
    public void setUpUpdateUserIdOldUserIsNull() {
        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);
        userID = 1;

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getId()).thenReturn(userID);
        UserDataImpl.putSessionIdAndUserSession(sessionId1, userDataSet);

        sessionId2 = "sessionId2";
        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getId()).thenReturn(userID);
        UserDataImpl.putSessionIdAndUserSession(sessionId2, userDataSet2);

    }

    @Test(groups = "UpdateUserIdOldUserIsNull")
    public void testUpdateUserIdOldUserIsNull() {
        userDataImpl.updateUserId(sessionId2, userDataSet2);
        Assert.assertNull(UserDataImpl.getLogInUserBySessionId(sessionId1));
        verify(userDataSet2).makeLike(userDataSet2);
        verify(userDataSet2).setPostStatus(0);
    }

    @AfterGroups("UpdateUserId")
    public void tearDownUpdateUserIdOldUserIsNull() {
        UserDataImpl.clearSessionIdAndrUserSession();
    }





    @BeforeGroups("UpdateUserIdNewUserIsNull")
    public void setUpUpdateUserIdNewUserIsNull() {
        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        UserDataImpl.putSessionIdAndUserSession(sessionId1, userDataSet1);

        userDataSet2 = null;


    }

    @Test(groups = "UpdateUserIdNewUserIsNull")
    public void testUpUpdateUserIdNewUserIsNull() {
        userDataImpl.updateUserId(sessionId1, userDataSet2);
        verify(userDataSet1).setPostStatus(0);
    }

    @AfterGroups("UpdateUserIdNewUserIsNull")
    public void tearDownUpdateUserIdNewUserIsNull() {
        UserDataImpl.clearSessionIdAndrUserSession();
    }






    @BeforeGroups("GetWSBySessionId")
    public void setUpGetWSBySessionId() {
        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        mockedRemote = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.getRemote()).thenReturn(mockedRemote);

        mockedWSImpl = mock(WebSocketImpl.class);
        when(mockedWSImpl.getSession()).thenReturn(mockedSession);
        UserDataImpl.putSessionIdAndWS(sessionId1, mockedWSImpl);

    }

    @Test(groups = "GetWSBySessionId")
    public void testGetWSBySessionId() {
        RemoteEndpoint returnedRemote = UserDataImpl.getWSBySessionId(sessionId1);

        Assert.assertEquals(returnedRemote, mockedRemote);
        verify(mockedWSImpl).getSession();
        verify(mockedSession).getRemote();

        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId2));
    }

    @AfterGroups("GetWSBySessionId")
    public void tearDownGetWSBySessionId() {

    }





    @BeforeGroups("GetLogInUserBySessionId")
    public void setUpGetLogInUserBySessionId() {
        sessionId1 = "sessionId1";
        userDataSet = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId1, userDataSet);
        sessionId2 = "lalkjljf";
    }

    @Test(groups = "GetLogInUserBySessionId")
    public void testGetLogInUserBySessionId() {
        UserDataSet returnedUDS = UserDataImpl.getLogInUserBySessionId(sessionId1);
        verify(userDataSet).visit();
        Assert.assertEquals(returnedUDS, userDataSet);
        Assert.assertNull(UserDataImpl.getLogInUserBySessionId(sessionId2));
    }

    @AfterGroups("GetLogInUserBySessionId")
    public void tearDownGetLogInUserBySessionId() {
        UserDataImpl.clearSessionIdAndrUserSession();
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
        UserDataImpl.clearSessionIdAndrUserSession();
    }




    @BeforeGroups("PartyEndFirstIsNull")
    public void setUpPartyEndFirstIsNull() {
        mockedMS = mock(MessageSystem.class);
        chatWS = mock(ChatWSImpl.class);
        userDataImpl = new UserDataImpl(mockedMS);
        userID1 = 1;
        userID2 = 2;

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getId()).thenReturn(userID1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);
        UserDataImpl.putSessionIdAndChatWS(sessionId1, chatWS);


        sessionId2 = "sessionId2";
        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getId()).thenReturn(userID2);
        UserDataImpl.putSessionIdAndUserSession(sessionId2,userDataSet2);
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);


    }

    @Test(groups = "PartyEndFirstIsNull")
    public void testPartyEndFirstIsNull() {
        userDataImpl.partyEnd(userID1, userID2);
        verify(mockedMS).getAddressByName("DBService");
    }

    @AfterGroups("PartyEndFirstIsNull")
    public void tearDownPartyEndFirstIsNull() {
        UserDataImpl.clearSessionIdAndrUserSession();
    }





    @BeforeGroups("PartyEndSecondIsNull")
    public void setUpPartyEndSecondIsNull() {
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
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);


    }

    @Test(groups = "PartyEndSecondIsNull")
    public void testPartyEndSecondIsNull() {
        userDataImpl.partyEnd(userID1, userID2);
        verify(mockedMS).getAddressByName("DBService");
    }

    @AfterGroups("PartyEndSecondIsNull")
    public void tearDownPartyEndSecondIsNull() {
        UserDataImpl.clearSessionIdAndrUserSession();
    }



    @BeforeGroups("PartyEndAllIsNull")
    public void setUpPartyEndPartyEndAllIsNull() {
        mockedMS = mock(MessageSystem.class);
        chatWS = mock(ChatWSImpl.class);
        userDataImpl = new UserDataImpl(mockedMS);
        userID1 = 1;
        userID2 = 2;

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getId()).thenReturn(userID1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);
        UserDataImpl.putSessionIdAndChatWS(sessionId1, chatWS);


        sessionId2 = "sessionId2";
        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getId()).thenReturn(userID2);
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);


    }

    @Test(groups = "PartyEndAllIsNull")
    public void testPartyEndAllIsNull() {
        userDataImpl.partyEnd(userID1, userID2);
        verify(mockedMS,never()).getAddressByName("DBService");
    }

    @AfterGroups("PartyEndAllIsNull")
    public void tearDownPartyEndAllIsNull() {
        UserDataImpl.clearSessionIdAndrUserSession();
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
