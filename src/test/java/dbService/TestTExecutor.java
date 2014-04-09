package dbService;

import base.MessageSystem;
import dbService.TExecutor;
import dbService.TResultHandler;
import dbService.UserDataSet;
import junit.framework.Assert;
import org.testng.annotations.*;

import java.sql.*;

import static org.mockito.Mockito.*;

/**
 * Created by vanik on 04.04.14.
 */
public class TestTExecutor {
    String query;
    Connection mockedConnection;
    TResultHandler mockedHandler;
    Statement mockedStatement;
    ResultSet mockedResultSet;
    UserDataSet uds;
    String login;
    DBServiceImpl dbService;
    MessageSystem mockedMS;
    String password;
    String uncknownLogin;
    String url;
    Connection connection;
    @BeforeGroups("FindUser")
    public void setUpFindUser() {
        mockedConnection = mock(Connection.class);
        uncknownLogin = "asdfsdf";
        login = "user1login";
        password = "user1passwd";

        mockedMS = mock(MessageSystem.class);
        dbService = new DBServiceImpl(mockedMS);
        dbService.addUDS(login, password);


    }

    @Test(groups = "FindUser")
    public void testFindUser() {
        Assert.assertEquals(TExecutor.findUser(dbService.getConnction(), login), 1);
        Assert.assertEquals(TExecutor.findUser(dbService.getConnction(), uncknownLogin), 0);
    }

    @AfterGroups("FindUser")
    public void tearDown() {
        dbService.deleteUser(login);
    }
}
