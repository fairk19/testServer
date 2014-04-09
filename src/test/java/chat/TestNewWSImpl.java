package chat;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Created by vanik on 09.04.14.
 */
public class TestNewWSImpl {
    private String message;
    private ChatWSImpl chatWS;
    private String sessionId;

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
        sessionId = "sessionId";
        JSONObject js = new JSONObject();
        js.put()
                

    }

    @Test(groups = "OnWebSocketTextThereAreNoConnect")
    public void testOnWebSocketTextThereAreNoConnect() {

    }

    @AfterGroups("OnWebSocketTextThereAreNoConnect")
    public void tearDownOnWebSocketTextThereAreNoConnect() {

    }
}
