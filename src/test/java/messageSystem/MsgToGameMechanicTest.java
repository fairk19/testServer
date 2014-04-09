package messageSystem;

import base.Address;
import base.GameChat;
import base.MessageSystem;
import chat.GameChatImpl;
import dbService.UserDataSet;
import gameMechanic.GameMechanicImpl;
import gameMechanic.gameCreating.MsgCreateChat;
import gameMechanic.gameCreating.MsgCreateGames;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 09.04.14.
 */
public class MsgToGameMechanicTest {

    private MessageSystem messageSystem;
    private GameChat gameChat;
    private GameMechanicImpl gameMechanic;
    private Map<String,UserDataSet> testData;

    @BeforeMethod
    public void setUp() throws Exception {
        this.messageSystem = new MessageSystemImpl();
        this.gameChat = new GameChatImpl(messageSystem);
        this.gameMechanic = new GameMechanicImpl(messageSystem);
        this.testData = new HashMap<String, UserDataSet>();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test(timeOut = 50, expectedExceptions = ThreadTimeoutException.class)
    public void testExec() throws Exception {
        Address to = messageSystem.getAddressByName("GameChat");
        MsgCreateGames msg = new MsgCreateGames(this.gameMechanic.getAddress(), to, this.testData);
        messageSystem.putMsg(to, msg);
        gameMechanic.run();
    }
}
