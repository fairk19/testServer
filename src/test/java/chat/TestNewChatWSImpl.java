package chat;

import base.GameChat;
import base.MessageSystem;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.*;

/**
 * Created by vanik on 09.04.14.
 */
public class TestNewChatWSImpl {
    private String message;
    private ChatWSImpl chatWS;
    private String sessionId;
    private String startServerTime;
    private String text;
    private RemoteEndpoint mockedRemoteEndpoint;
    private Session mockedSession;
    private boolean connection;
    private UserDataSet userDataSet1;
    private String nick1;
    private GameChatImpl gameChat;
    private MessageSystem mockedMS;
    private String sessionId1;
    private String sessionId2;

    List<ChatMessage> chatList;

    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

    }


    @BeforeGroups("OnWebSocketTextTextIsEmpty")
    public void setUpOnWebSocketTextTextIsEmpty() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("startServerTime", startServerTime);
        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextTextIsEmpty")
    public void testOnWebSocketTextTextIsEmpty() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, atLeastOnce()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextTextIsEmpty")
    public void tearDownOnWebSocketTextTextIsEmpty() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }
    
    
    
    
    

    @BeforeGroups("OnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull")
    public void setUpOnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime()+1;
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("startServerTime", startServerTime);
//        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull")
    public void testOnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, never()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull")
    public void tearDownOnWebSocketTextStartServerTimeIsIncorrectAndTextIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }
    
    
    
    


    @BeforeGroups("OnWebSocketTextTextIsNull")
    public void setUpOnWebSocketTextTextIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("startServerTime", startServerTime);
//        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextTextIsNull")
    public void testOnWebSocketTextTextIsNull() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, atLeastOnce()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextTextIsNull")
    public void tearDownOnWebSocketTextTextIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }
    
    
    
    
    

    @BeforeGroups("OnWebSocketTextStartServerTimeIsNull")
    public void setUpOnWebSocketTextStartServerTimeIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextStartServerTimeIsNull")
    public void testOnWebSocketTextStartServerTimeIsNull() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, never()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextStartServerTimeIsNull")
    public void tearDownOnWebSocketTextStartServerTimeIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }
    
    
    


    @BeforeGroups("OnWebSocketTextSessionIdIsNull")
    public void setUpOnWebSocketTextSessionIdIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServerTime);
        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextSessionIdIsNull")
    public void testOnWebSocketTextSessionIdIsNull() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, never()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextSessionIdIsNull")
    public void tearDownOnWebSocketTextSessionIdIsNull() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }





    @BeforeGroups("OnWebSocketTextThereAreNoConnect")
    public void setUpOnWebSocketTextThereAreNoConnect() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = false;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);

        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("startServerTime", startServerTime);
        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test(groups = "OnWebSocketTextThereAreNoConnect")
    public void testOnWebSocketTextThereAreNoConnect() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1, never()).visit();
        verify(mockedSession, never()).getRemote();
    }

    @AfterGroups("OnWebSocketTextThereAreNoConnect")
    public void tearDownOnWebSocketTextThereAreNoConnect() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }






    @BeforeGroups("OnWebSocketTextConnectIsTrue")
    public void setUpOnWebSocketTextConnectIsTrue() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        sessionId2 = "sessionId2";

        text = "textForTest";
        connection = true;
        nick1 = "nick1";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        userDataSet1 = mock(UserDataSet.class);
        when(userDataSet1.getNick()).thenReturn(nick1);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);



        startServerTime = UserDataImpl.getStartServerTime();
        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId1);
        js.put("startServerTime", startServerTime);
        js.put("text", text);
        message = js.toJSONString();
        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);

        mockedMS = mock(MessageSystem.class);
        gameChat = new GameChatImpl(mockedMS);
        gameChat.createChat(sessionId1, sessionId2);

        UserDataImpl.putSessionIdAndChatWS(sessionId1, chatWS);
        UserDataImpl.putSessionIdAndChatWS(sessionId2, chatWS);

    }

    @Test(groups = "OnWebSocketTextConnectIsTrue")
    public void testOnWebSocketTextConnectIsTrue() {
        chatWS.onWebSocketText(message);
        verify(userDataSet1,atLeastOnce()).visit();
        verify(mockedSession, times(2)).getRemote();

    }

    @AfterGroups("OnWebSocketTextConnectIsTrue")
    public void tearDownOnWebSocketTextConnectIsTrue() {
        UserDataImpl.clearAllMaps();
        GameChatImpl.clearAllMaps();
    }
}