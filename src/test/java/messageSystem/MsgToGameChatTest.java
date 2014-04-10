package messageSystem;

import base.Address;
import base.MessageSystem;
import chat.GameChatImpl;
import gameMechanic.GameMechanicImpl;
import gameMechanic.gameCreating.MsgCreateChat;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
* Created by Александр on 09.04.14.
*/
public class MsgToGameChatTest {

    private MessageSystem messageSystem;
    private GameChatImpl gameChat;
    private GameMechanicImpl gameMechanic;

    @BeforeMethod
    public void setUp() throws Exception {
        this.messageSystem = new MessageSystemImpl();
        this.gameChat = new GameChatImpl(messageSystem);
        this.gameMechanic = new GameMechanicImpl(messageSystem);
    }

    @Test
    public void testExec() throws Exception {
        Address to = messageSystem.getAddressByName("GameChat");
        MsgCreateChat msg = new MsgCreateChat(this.gameMechanic.getAddress(), to, "1q2w3e4r5t", "1a2s3d4f5g");
        messageSystem.putMsg(to, msg);
        gameChat.methodRun(0);
    }
}

