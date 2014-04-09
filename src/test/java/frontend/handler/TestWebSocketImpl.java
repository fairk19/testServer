package frontend.handler;


import base.MessageSystem;
import base.UserData;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import junit.framework.Assert;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;


import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by vanik on 09.04.14.
 */

public class TestWebSocketImpl {
    private Map<String, String> usersToColors;
    private String sessionId;
    private String sessionId1;
    private String sessionId2;
    private String colorUser1;
    private String colorUser2;
    private String color;
    private String color1;
    private String color2;
    private UserDataSet userDataSet;
    private UserDataSet userDataSet1;
    private UserDataSet userDataSet2;
    private WebSocketImpl mockedWS;
    private RemoteEndpoint mockedRemoteEndpoint;
    private Session mockedSession;
    private MessageSystem mockedMS;
    private WebSocketImpl ws;
    private boolean useMS;
    private boolean connection;
    private String startServeTime;
    private UserDataImpl mockedUserDataImpl;

    private String message;
    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

    }




    @BeforeGroups("OnWebSocketTextAddNewWebSocket")
    public void setUpOnWebSocketTextAddNewWebSocket() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;


        startServeTime = UserDataImpl.getStartServerTime();
        sessionId = "sessionId1";

        userDataSet = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId, userDataSet);


        int from_x = -1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextAddNewWebSocket")
    public void testOnWebSocketTextAddNewWebSocket() {
        ws.onWebSocketText(message);
        verify(userDataSet, atLeastOnce()).visit();
        Assert.assertNotNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextAddNewWebSocket")
    public void tearDownOnWebSocketTextAddNewWebSocket() {
        UserDataImpl.clearAllMaps();
    }






    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk")
    public void setUpOnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = UserDataImpl.getStartServerTime();
        sessionId = "sessionId1";

        int from_x = -1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk")
    public void testOnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrectButServerTimeIsOk() {
        UserDataImpl.clearAllMaps();
    }









    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrect5")
    public void setUpOnWebSocketTextCoordinatesAreIncorrect5() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";

        int from_x = 1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrect5")
    public void testOnWebSocketTextCoordinatesAreIncorrect5() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrect5")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrect5() {
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrect4")
    public void setUpOnWebSocketTextCoordinatesAreIncorrect4() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = 1;
        int from_y = 1;
        int to_x = 2;
        int to_y = -1;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrect4")
    public void testOnWebSocketTextCoordinatesAreIncorrect4() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrect4")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrect4() {
        UserDataImpl.clearAllMaps();
    }







    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrect3")
    public void setUpOnWebSocketTextCoordinatesAreIncorrect3() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = 1;
        int from_y = 1;
        int to_x = -1;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrect3")
    public void testOnWebSocketTextCoordinatesAreIncorrect3() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrect3")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrect3() {
        UserDataImpl.clearAllMaps();
    }




    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrect2")
    public void setUpOnWebSocketTextCoordinatesAreIncorrect2() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = 1;
        int from_y = -1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrect2")
    public void testOnWebSocketTextCoordinatesAreIncorrect2() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrect2")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrect2() {
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("OnWebSocketTextCoordinatesAreIncorrect1")
    public void setUpOnWebSocketTextCoordinatesAreIncorrect1() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = -1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextCoordinatesAreIncorrect1")
    public void testOnWebSocketTextCoordinatesAreIncorrect1() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextCoordinatesAreIncorrect1")
    public void tearDownOnWebSocketTextCoordinatesAreIncorrect1() {
        UserDataImpl.clearAllMaps();
    }











    @BeforeGroups("OnWebSocketTextThereAreNoConnection")
    public void setUpOnWebSocketTextThereAreNoConnection() {
        UserDataImpl.clearAllMaps();

        useMS = true;
        connection = false;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = 1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextThereAreNoConnection")
    public void testOnWebSocketTextThereAreNoConnection() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextThereAreNoConnection")
    public void tearDownOnWebSocketTextThereAreNoConnection() {
        UserDataImpl.clearAllMaps();
    }







    @BeforeGroups("OnWebSocketTextServerTimeIsNotCorrect")
    public void setUpOnWebSocketTextServerTimeIsNotCorrect() {
        UserDataImpl.clearAllMaps();
        useMS = true;
        connection = true;

        startServeTime = "11111111";
        sessionId = "sessionId1";
        int from_x = 1;
        int from_y = 1;
        int to_x = 2;
        int to_y = 2;
        String status = "status";
        JSONObject js = new JSONObject();
        js.put("startServerTime", startServeTime);
        js.put("sessionId", sessionId);
        js.put("from_x", from_x);
        js.put("from_y", from_y);
        js.put("to_x", to_x);
        js.put("to_y", to_y);
        js.put("status", status);


        message = js.toJSONString();

        mockedMS = mock(MessageSystem.class);
        ws.setMS(mockedMS);
        ws = new WebSocketImpl(useMS);

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        ws.onWebSocketConnect(mockedSession);

    }

    @Test(groups = "OnWebSocketTextServerTimeIsNotCorrect")
    public void testOnWebSocketText() {
        ws.onWebSocketText(message);
        Assert.assertNull(UserDataImpl.getWSBySessionId(sessionId));
    }

    @AfterGroups("OnWebSocketTextServerTimeIsNotCorrect")
    public void tearDownOnWebSocketTextServerTimeIsNotCorrect() {
        UserDataImpl.clearAllMaps();
    }



    @BeforeGroups("WebSocketImpl")
    public void setUpWebSocketImpl() {
        mockedMS = mock(MessageSystem.class);
        WebSocketImpl.setMS(mockedMS);
        useMS = true;
    }

    @Test(groups = "WebSocketImpl")
    public void testWebSocketImpl() {
        ws = new WebSocketImpl(useMS);
        verify(mockedMS).addService(ws, "WebSocket");
        ws = new WebSocketImpl(!useMS);
        verify(mockedMS, never()).addService(ws, "WebSocket");
    }

    @AfterGroups("WebSocketImpl")
    public void tearDownWebSocketImpl() {

    }

    @BeforeGroups("UpdateUsersColor")
    public void setUpUpdateUsersColor() {
        UserDataImpl.clearAllMaps();

        sessionId1 = "sessionId1";
        colorUser1 = "b";
        color1 = "black";

        sessionId2 = "sessionId2";
        colorUser2 = "w";
        color2 = "white";

        sessionId = "sessionId";
        color = "norFoundColor";

        mockedRemoteEndpoint = mock(RemoteEndpoint.class);

        mockedSession = mock(Session.class);
        when(mockedSession.getRemote()).thenReturn(mockedRemoteEndpoint);

        mockedWS = mock(WebSocketImpl.class);
        when(mockedWS.getSession()).thenReturn(mockedSession);

        userDataSet1 = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId1, userDataSet1);
        UserDataImpl.putSessionIdAndWS(sessionId1, mockedWS);

        userDataSet2 = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);
        UserDataImpl.putSessionIdAndWS(sessionId2, mockedWS);

        userDataSet = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId, userDataSet);
        UserDataImpl.putSessionIdAndWS(sessionId, mockedWS);

        usersToColors = new HashMap<String, String>();
        usersToColors.put(sessionId1, color1);
        usersToColors.put(sessionId2, color2);
        usersToColors.put(sessionId, color);

    }

    @Test(groups = "UpdateUsersColor")
    public void testUpdateUsersColor() {
        WebSocketImpl wsImpl= new WebSocketImpl();
        wsImpl.updateUsersColor(usersToColors);
        verify(userDataSet1).setColor(colorUser1);
        verify(userDataSet2).setColor(colorUser2);

        verify(userDataSet, never()).setColor(colorUser1);
        verify(userDataSet, never()).setColor(colorUser2);

    }

    @AfterGroups("UpdateUsersColor")
    public void tearDownUpdateUsersColor() {
        UserDataImpl.clearAllMaps();
    }


}
