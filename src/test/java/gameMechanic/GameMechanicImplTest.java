package gameMechanic;

import base.GameChat;
import base.MessageSystem;
import base.UserData;
import base.WebSocket;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import gameClasses.Stroke;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 08.04.14.
 */
public class GameMechanicImplTest {

    private GameMechanicImpl gameMechanic;
    private Map<String, UserDataSet> users;
    private MessageSystem messageSystem;
    private Map<String, String> sessionIdToColor;
    private GameChat gameChat;
    private GameSession gameSession;
    private UserData userData;
    private WebSocket webSocket;

    @BeforeMethod
    public void setUp() throws Exception{
        this.messageSystem = new MessageSystemImpl();
        this.gameMechanic = new GameMechanicImpl(messageSystem);
        this.gameSession = new GameSession(1, 2);
        this.users = new HashMap<String, UserDataSet>();
        this.sessionIdToColor = new HashMap<String, String>();
        this.gameChat = new GameChatImpl(messageSystem);
        this.userData = new UserDataImpl(messageSystem);
        WebSocketImpl.setMS(messageSystem);
        this.webSocket = new WebSocketImpl(true);
        UserDataSet userDataSetId1 = new UserDataSet(1, "user1", 0, 1, 0);
        UserDataSet userDataSetId2 = new UserDataSet(2, "user2", 0, 1, 1);
        UserDataSet userDataSetId3 = new UserDataSet(3, "user3", 0, 1, 2);
        UserDataSet userDataSetId4 = new UserDataSet(4, "user3", 0, 3, 5);
        this.users.put("1", userDataSetId1);
        this.users.put("2", userDataSetId2);
        this.users.put("3", userDataSetId3);
        this.users.put("4", userDataSetId4);
    }

    @Test
    public void testCreateGames() throws Exception {

        Map<String, String> games = this.gameMechanic.createGames(users);
        Assert.assertFalse(games.isEmpty());
        this.users.remove("1");
        this.users.remove("2");
        this.users.remove("3");
        this.users.remove("4");
        games = this.gameMechanic.createGames(users);
        Assert.assertTrue(games.isEmpty());

    }

    @Test
    public void testCheckStroke() throws Exception {

        Stroke testStroke = new Stroke(0, 1, 2, 3, "win");
        Map<Integer,Stroke> checkStroke = this.gameMechanic.checkStroke(1, testStroke);
        Assert.assertTrue(checkStroke.isEmpty());

        this.gameMechanic.createGame("1", "2", sessionIdToColor, users);
        checkStroke = this.gameMechanic.checkStroke(1, testStroke);
        Assert.assertTrue(checkStroke.size() == 1);

    }

    @Test
    public void testCheckStrokeLose() throws Exception {

        this.gameMechanic.createGame("1", "2", sessionIdToColor, users);
        Stroke testStroke = new Stroke(0, 1, 2, 3, "lose");
        Map<Integer,Stroke> checkStroke = this.gameMechanic.checkStroke(1, testStroke);
        Assert.assertTrue(checkStroke.isEmpty());

    }
}
