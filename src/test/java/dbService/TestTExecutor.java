package dbService;

import dbService.TExecutor;
import dbService.TResultHandler;
import dbService.UserDataSet;
import junit.framework.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

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


    @BeforeGroups("execQuery")
    public void setUp() {
        query = "select * from Users where id = 10";
        mockedConnection = mock(Connection.class);
        mockedHandler = mock(TResultHandler.class);
        mockedStatement = mock(Statement.class);
        mockedResultSet = mock(ResultSet.class);
        uds = new UserDataSet(10,"name",11 , 2 , 2);
        try {
            when(mockedConnection.createStatement()).thenReturn(mockedStatement);
            when(mockedStatement.getResultSet()).thenReturn(mockedResultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        when(mockedHandler.handle(mockedResultSet)).thenReturn(uds);
    }
    @Test(groups = "execQuery")
    public void testExecQuery() {
        Assert.assertEquals(TExecutor.execQuery(mockedConnection,query,mockedHandler).getClass(),uds.getClass());
    }


    @BeforeGroups("ExecQueryWithIncorrectSQL")
    public void setUpExecQueryWithIncorrectSQL() {
        mockedConnection = mock(Connection.class);
        mockedHandler = mock(TResultHandler.class);
        mockedStatement = mock(Statement.class);
        query = "select * from Users were id = 10";

        try {
            when(mockedConnection.createStatement()).thenReturn(mockedStatement);
            when(mockedStatement.execute(query)).thenThrow(new SQLException());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        when(mockedHandler.handle(mockedResultSet)).thenReturn(uds);
    }

    @Test(groups = "ExecQueryWithIncorrectSQL")
    public void testExecQueryWithIncorrectSQL(){
        Assert.assertNull(TExecutor.execQuery(mockedConnection, query, mockedHandler));
    }
}
