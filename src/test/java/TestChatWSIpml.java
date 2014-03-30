import base.UserData;
import chat.ChatWSImpl;
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
    private String sessionId = "123";
    private String message = " {\"text\":\"hello\",\"sessionId\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"startServerTime\":\"current_date\"}";
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
        chatWS.onWebSocketText(message);

//        {"sessionId":"d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35","startServerTime":"9dcd0cff5dddb7f2b24811ce5c9588879d78565eaf73659b56281fec60d809c5"}
//        {"JSESSIONID":"nagcnhtw2mar1p2agooh0ntga","clicktracking-session":"AO4XCfEGi1L4Pc8oX8ziSHN8ksjtgvkVj","sessionId":"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b","startServerTime":"9dcd0cff5dddb7f2b24811ce5c9588879d78565eaf73659b56281fec60d809c5"}
//        {"text":"hello","sessionId":"d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35","startServerTime":"9dcd0cff5dddb7f2b24811ce5c9588879d78565eaf73659b56281fec60d809c5"}
//        verify()
    }


    @Test
    public void testAddMessageToChat() {
//        verify(mockedUserData.)
    }

    @Test
    public void testAddNewChater() {
        System.out.println("testAddNewChater");
//        verify(mockedUserData, times(1)).putSessionIdAndChatWS(sessionId, chatWS);
    }
    @AfterMethod
    public void shutDown() {
        System.out.println("After Method");
    }
}
