import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.UserDataSet;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by vanik on 26.03.14.
 */
public class TestDBServiceImpl {
    private MessageSystem mockedMS = mock(MessageSystem.class);
    private DBServiceImpl dbService;
    private String login;
    private String password;
    @BeforeMethod
    public void setUp() {
        dbService = new DBServiceImpl(mockedMS);
        login = "user1";
        password = "passwd1";
        dbService.addUDS(login, password);
    }
    @Test
    public void test() {
        System.out.println("nick"+dbService.getUDS(login, password).getNick());
        Assert.assertEquals(login, dbService.getUDS(login, password).getNick());
    }
    @AfterTest
    public void tearDown() {
        dbService.deleteUser(login);
    }
}
