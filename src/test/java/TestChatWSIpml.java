import base.UserData;
import chat.ChatWSImpl;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import org.eclipse.jetty.server.Authentication;
import org.junit.Before;
import org.junit.Ignore;
import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;
import static org.mockito.Mockito.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TimeHelper;

/**
 * Created by vanik on 30.03.14.
 */
public class TestChatWSIpml {
    private ChatWSImpl chatWS;
    private UserDataImpl mockedUserData;
    private String sessionId = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private ChatWSImpl mockedChatWS;
    @BeforeMethod
    public void setUp() {
        System.out.println("Before Method");
        chatWS = new ChatWSImpl();
        mockedUserData = mock(UserDataImpl.class);
        System.out.println(TimeHelper.getCurrentTime());
//        PowerMockito.mockStatic(UserDataImpl.class);
//        PowerMockito.when(UserDataImpl.checkServerTime("current_date")).thenReturn(true);

    }
    @Test
    public void testConstructor() {
        System.out.println("Test Method");
        Assert.assertNotNull(chatWS);
    }

    @Test
    public void testOnWebSocketText() {
        String message = " {\"text\":\"hello\",\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\"current_date\"}";
        chatWS.onWebSocketText(message);
        String message1 = "{\"text\":\"\",\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\""+UserDataImpl.getStartServerTime()+"\"}";
        chatWS.onWebSocketText(message1);
        String message2 = "{\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\""+UserDataImpl.getStartServerTime()+"\"}";
        chatWS.onWebSocketText(message2);
    }


    @Test
    public void testAddMessageToChat() {
        UserDataImpl.putLogInUser(sessionId,new UserDataSet());
        String message = "{\"text\":\"hello\",\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\""+UserDataImpl.getStartServerTime()+"\"}";
        chatWS.onWebSocketText(message);

    }
    @Test(expectedExceptions = Exception.class)
    public void testSendMessage(){
        String message = "{\"text\":\"\",\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\""+UserDataImpl.getStartServerTime()+"\"}";
        ChatWSImpl.sendMessage("user1", message);

       ChatWSImpl.sendMessage(null, null);

    }
    @Test
    public void testAddNewChater() {
//        verify(mockedUserData, times(1)).putSessionIdAndChatWS(sessionId, chatWS);
    }
    @AfterMethod
    public void shutDown() {
        System.out.println("After Method");
    }
}
