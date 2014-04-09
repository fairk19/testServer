package chat;

import dbService.UserDataSet;
import frontend.UserDataImpl;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vanik on 09.04.14.
 */
public class TestNewWSImpl {
    private String message;
    private ChatWSImpl chatWS;
    private String sessionId;
    private String startServerTime;
    private String text;
    private UserDataSet userDataSet;
    private boolean connection;
    private  Session mockedSession;

    @BeforeGroups("")
    public void setUp() {

    }

    @Test(groups = "")
    public void test() {

    }

    @AfterGroups("")
    public void tearDown() {

    }



    @BeforeGroups("OnWebSocketTextThereAreNoConnect")
    public void setUpOnWebSocketTextThereAreNoConnect() {
        UserDataImpl.clearAllMaps();
        sessionId = "sessionId";
        startServerTime = UserDataImpl.getStartServerTime();
        text  = "textForTest";
        connection = true;

        JSONObject js = new JSONObject();
        js.put("sessionId", sessionId);
        js.put("startServerTime", startServerTime);
        js.put("text", text);
        message = js.toJSONString();

        userDataSet = mock(UserDataSet.class);
        UserDataImpl.putLogInUser(sessionId, userDataSet);

        mockedSession = mock(Session.class);
        when(mockedSession.isOpen()).thenReturn(connection);

        chatWS = new ChatWSImpl();
        chatWS.onWebSocketConnect(mockedSession);





    }

    @Test(groups = "OnWebSocketTextThereAreNoConnect")
    public void testOnWebSocketTextThereAreNoConnect() {
        chatWS.onWebSocketText(message);
        verify(userDataSet).visit();
    }

    @AfterGroups("OnWebSocketTextThereAreNoConnect")
    public void tearDownOnWebSocketTextThereAreNoConnect() {
        UserDataImpl.clearAllMaps();
    }
}
