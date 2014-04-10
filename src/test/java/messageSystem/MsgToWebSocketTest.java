package messageSystem;

import base.Address;
import frontend.WebSocketImpl;
import gameClasses.Snapshot;
import gameMechanic.GameMechanicImpl;
import gameMechanic.Stroke.MsgDoneSnapshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Alexandr on 10.04.14.
 */
public class MsgToWebSocketTest {

    private WebSocketImpl webSocket;
    private MessageSystemImpl messageSystem;
    private GameMechanicImpl gameMechanic;

    @BeforeMethod
    public void setUp() throws Exception {
        webSocket = new WebSocketImpl();
        messageSystem = new MessageSystemImpl();
        gameMechanic = new GameMechanicImpl(messageSystem);
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testExec() throws Exception {
        Snapshot snapshot = new Snapshot(null, 'w', 1, 'b');
        Address to = messageSystem.getAddressByName("WebSocket");
        Address from = messageSystem.getAddressByName("GameMechanic");
        MsgDoneSnapshot msg=new MsgDoneSnapshot(from, to, 2, snapshot);
        messageSystem.putMsg(to, msg);
    }
}
