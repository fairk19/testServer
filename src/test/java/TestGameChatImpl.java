import base.GameChat;
import base.MessageSystem;
import chat.GameChatImpl;
import static org.mockito.Mockito.*;

import dbService.UserDataSet;
import frontend.UserDataImpl;
import junit.framework.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by vanik on 30.03.14.
 */
public class TestGameChatImpl {
    MessageSystem ms = mock(MessageSystem.class);
    GameChat gameChat = new GameChatImpl(ms);
    private String sessionId1 = "user1";
    private String sessionId2 = "user2";
    private String sessionId3 = "user3";
    @BeforeMethod
    public void setUp(){

    }
    @Test
    public void testGetAddress(){
        Assert.assertNotNull(gameChat.getAddress());
    }
    @Test
    public void testGetMessageSystem() {
        Assert.assertNotNull(gameChat.getMessageSystem());
    }
    @Test
    public void testSendMessage() {
        gameChat.createChat(sessionId1,sessionId2);
        UserDataSet userDataSet = new UserDataSet();
        UserDataImpl.putLogInUser(sessionId1, userDataSet);
        GameChatImpl.sendMessage(sessionId1,"text");

        UserDataImpl.putLogInUser(sessionId3, userDataSet);
        GameChatImpl.sendMessage(sessionId3,"text2");

        GameChatImpl.sendMessage(null, null);
    }

    @Test(timeOut = 100)
    public void testRun() {
        gameChat.run();
    }
    @AfterMethod
    public void tearDown() {

    }
}
