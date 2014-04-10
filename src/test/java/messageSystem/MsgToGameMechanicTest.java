package messageSystem;

import base.Address;
import base.MessageSystem;
import frontend.MsgRemoveUserFromGM;
import gameMechanic.GameMechanicImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Александр on 09.04.14.
 */
public class MsgToGameMechanicTest {

    private MessageSystem messageSystem;
    private GameMechanicImpl gameMechanic;

    @BeforeMethod
    public void setUp() throws Exception {
        this.messageSystem = new MessageSystemImpl();
        this.gameMechanic = new GameMechanicImpl(messageSystem);
    }

    @Test
    public void testExec() throws Exception {
        Address to = messageSystem.getAddressByName("GameMechanic");
        MsgRemoveUserFromGM msg = new MsgRemoveUserFromGM(null, to, "1q2w3e4r5t");
        messageSystem.putMsg(to, msg);
        gameMechanic.methodRun(0);
    }
}
