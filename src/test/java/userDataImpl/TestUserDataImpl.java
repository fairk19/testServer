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
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;
import resource.TimeSettings;
import utils.TimeHelper;

import java.net.Socket;
import java.rmi.Remote;

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
    private ChatWSImpl mockedChatWSImpl;
    private int count;
    private TimeSettings timeSettings;
    private int maxExitTime;
    private String sessionId;
    private long currentTime;



    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

    }







    @BeforeGroups("CheckUsers")
    public void setUpCheckUsers() {
        UserDataImpl.clearAllMaps();
        count = 10;
        maxExitTime = TimeSettings.getExitTime();
        sessionId = "sessionId";
        sessionId2 = "sessionId2";

        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);


        userDataSet = mock(UserDataSet.class);
        when(userDataSet.getLastVisit()).
                thenReturn(TimeHelper.getCurrentTime() - maxExitTime * 2);
        UserDataImpl.putSessionIdAndUserSession(sessionId, userDataSet);

        userDataSet2 = mock(UserDataSet.class);
        when(userDataSet2.getLastVisit()).
                thenReturn(TimeHelper.getCurrentTime() - maxExitTime / 2);
        UserDataImpl.putSessionIdAndUserSession(sessionId2, userDataSet2);

    }


    @Test(groups = "CheckUsers")
    public void testCheckUsers() {
        userDataImpl.checkUsers(count);
        Assert.assertNull(UserDataImpl.getUserSessionBySessionId(sessionId));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionId2));
    }

    @AfterGroups("CheckUsers")
    public void tearDownCheckUsers() {
        UserDataImpl.clearAllMaps();
    }






    @BeforeGroups("CheckUsersKeepAliveEqualsOne")
    public void setUpCheckUsersKeepAliveEqualsOne() {
        UserDataImpl.clearAllMaps();
        count = 1;
        maxExitTime = TimeSettings.getExitTime();
        sessionId = "sessionId";

        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);


        userDataSet = mock(UserDataSet.class);
        when(userDataSet.getLastVisit()).
                thenReturn(TimeHelper.getCurrentTime() - maxExitTime / 2);
        UserDataImpl.putSessionIdAndUserSession(sessionId, userDataSet);

        mockedWSImpl = mock(WebSocketImpl.class);
        UserDataImpl.putSessionIdAndWS(sessionId, mockedWSImpl);
    }


    @Test(groups = "CheckUsersKeepAliveEqualsOne")
    public void testCheckUsersKeepAliveEqualsOne() {
        userDataImpl.checkUsers(count);
        verify(mockedWSImpl).getSession();
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionId));

    }

    @AfterGroups("CheckUsersKeepAliveEqualsOne")
    public void tearDownCheckUsersKeepAliveEqualsOne() {
        UserDataImpl.clearAllMaps();
    }




    @BeforeGroups("KeepAliveWSIsNull")
    public void setUpKeepAliveWSIsNull() {
        UserDataImpl.clearAllMaps();
        count = 1;
        maxExitTime = TimeSettings.getExitTime();
        sessionId = "sessionId";

        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);

        mockedWSImpl = mock(WebSocketImpl.class);
//        UserDataImpl.putSessionIdAndWS(sessionId, mockedWSImpl);


    }


    @Test(groups = "KeepAliveWSIsNull")
    public void testKeepAliveWSIsNull() {
        userDataImpl.keepAlive(sessionId);
        verify(mockedWSImpl, never()).getSession();
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));

    }

    @AfterGroups("KeepAliveWSIsNull")
    public void tearDownKeepAliveWSIsNull() {
        UserDataImpl.clearAllMaps();
    }






    @BeforeGroups("CreateGamesNoUsersWantToPlay")
    public void setUpCreateNoUsersWantToPlay() {
        UserDataImpl.clearAllMaps();


        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);

    }

    @Test(groups = "CreateGamesNoUsersWantToPlay", timeOut = 500, expectedExceptions = ThreadTimeoutException.class)
    public void testCreateGamesNoUsersWantToPlay() throws InterruptedException{
        userDataImpl.run();
        verify(mockedMS,never()).getAddressByName("GameMechanic");
    }

    @AfterGroups("CreateGamesNoUsersWantToPlay")
    public void tearDownCreateGamesNoUsersWantToPlay() {
        UserDataImpl.clearAllMaps();
    }




    @BeforeGroups("CreateGames")
    public void setUpCreateGames() {
        UserDataImpl.clearAllMaps();


        mockedMS = mock(MessageSystem.class);
        userDataImpl = new UserDataImpl(mockedMS);

        sessionId1 = "sessionId1";
        userDataSet1 = mock(UserDataSet.class);
        UserDataImpl.playerWantToPlay(sessionId1, userDataSet1);

    }

    @Test(groups = "CreateGames", timeOut = 500, expectedExceptions = ThreadTimeoutException.class)
    public void testCreateGames() throws InterruptedException{
        userDataImpl.run();
        verify(mockedMS).getAddressByName("GameMechanic");
    }

    @AfterGroups("CreateGames")
    public void tearDownCreateGames() {
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("GetChatWSBySessionId")
    public void setUpGetChatWSBySessionId() {
        sessionId1 = "sessionId1";
        sessionId2 = "sessionasdflkjlkj";

        mockedRemote = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.getRemote()).thenReturn(mockedRemote);

        mockedChatWSImpl = mock(ChatWSImpl.class);
        when(mockedChatWSImpl.getSession()).thenReturn(mockedSession);
        UserDataImpl.putSessionIdAndChatWS(sessionId1, mockedChatWSImpl);
    }

    @Test(groups = "GetChatWSBySessionId")
    public void testGetChatWSBySessionId() {
        RemoteEndpoint returnedRemote = UserDataImpl.getChatWSBySessionId(sessionId1);

        Assert.assertEquals(returnedRemote, mockedRemote);
        verify(mockedChatWSImpl).getSession();
        verify(mockedSession).getRemote();

        Assert.assertNull(UserDataImpl.getChatWSBySessionId(sessionId2));
    }

    @AfterGroups("GetChatWSBySessionId")
    public void tearDownGetChatWSBySessionId() {

    }

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
        UserDataImpl.clearAllMaps();

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
        UserDataImpl.clearAllMaps();
    }






    @BeforeGroups("UpdateUserId")
    public void setUpUpdateUserId() {
        UserDataImpl.clearAllMaps();

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

    }

    @Test(groups = "UpdateUserId")
    public void testUpdateUserId() {
        userDataImpl.updateUserId(sessionId2, userDataSet2);
        verify(userDataSet2).makeLike(userDataSet2);
        verify(userDataSet2).setPostStatus(0);
    }

    @AfterGroups("UpdateUserId")
    public void tearDownUpdateUserId() {
        UserDataImpl.clearAllMaps();
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
        UserDataImpl.clearAllMaps();
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
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);


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
        UserDataImpl.clearAllMaps();

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
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);


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
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);


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














}
