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

//        url="jdbc:mysql://localhost:3306/qualityTestDB?user=root&password=110708";
//        try{
//            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
//            DriverManager.registerDriver(driver);
//            connection = DriverManager.getConnection(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        uncknownLogin = "asdfsdf";

        login = "user1login";
        password = "user1passwd";

        mockedMS = mock(MessageSystem.class);
        dbService = new DBServiceImpl(mockedMS, SQLiteConnectionCreator.getConnection());
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



//
//    @BeforeGroups("execQuery")
//    public void setUpExecQuery() {
//        query = "select * from Users where id = 10";
//        mockedConnection = mock(Connection.class);
//        mockedHandler = mock(TResultHandler.class);
//        mockedStatement = mock(Statement.class);
//        mockedResultSet = mock(ResultSet.class);
//        uds = new UserDataSet(10,"name",11 , 2 , 2);
//        try {
//            when(mockedConnection.createStatement()).thenReturn(mockedStatement);
//            when(mockedStatement.getResultSet()).thenReturn(mockedResultSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        when(mockedHandler.handle(mockedResultSet)).thenReturn(uds);
//    }
//    @Test(groups = "execQuery")
//    public void testExecQuery() {
//        Assert.assertEquals(TExecutor.execQuery(mockedConnection,query,mockedHandler).getClass(),uds.getClass());
//    }
//
//
//    @BeforeGroups("ExecQueryWithIncorrectSQL")
//    public void setUpExecQueryWithIncorrectSQL() {
//        mockedConnection = mock(Connection.class);
//        mockedHandler = mock(TResultHandler.class);
//        mockedStatement = mock(Statement.class);
//        query = "select * from Users were id = 10";
//
//        try {
//            when(mockedConnection.createStatement()).thenReturn(mockedStatement);
//            when(mockedStatement.execute(query)).thenThrow(new SQLException());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        when(mockedHandler.handle(mockedResultSet)).thenReturn(uds);
//    }
//
//    @Test(groups = "ExecQueryWithIncorrectSQL")
//    public void testExecQueryWithIncorrectSQL(){
//        Assert.assertNull(TExecutor.execQuery(mockedConnection, query, mockedHandler));
//    }
}
