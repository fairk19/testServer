package frontend.handler;


import base.UserData;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by vanik on 09.04.14.
 */
public class TestWebSocketImpl {
    private Map<String, String> usersToColors;
    private String sessionId1;
    private String sessionId2;
    private String colorUser1;
    private String colorUser2;
    private String color1;
    private String color2;
    private UserDataSet userDataSet1;
    private UserDataSet userDataSet2;
    private WebSocketImpl mockedWS;
    private RemoteEndpoint mockedRemoteEndpoint;
    private Session mockedSession;
    private String sessionId;
    private UserDataSet userDataSet;
    private String color;


    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

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
//        when(userDataSet1.getColor()).thenReturn(colorUser1);

        userDataSet2 = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId2, userDataSet2);
        UserDataImpl.putSessionIdAndWS(sessionId2, mockedWS);

        userDataSet = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId, userDataSet);
        UserDataImpl.putSessionIdAndWS(sessionId, mockedWS);

//        when(userDataSet2.getColor()).thenReturn(colorUser2);

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
