import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.UserDataSet;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.mockito.Mockito.mock;

/**
 * Created by vanik on 26.03.14.
 */
public class TestDBServiceImpl {
    private MessageSystem mockedMS = mock(MessageSystem.class);
    private DBServiceImpl dbService;
    private String login;
    private String password;
    private ArrayList<UserDataSet> users;
    @BeforeClass
    public void setUp() {
        dbService = new DBServiceImpl(mockedMS);
//        login = "user1";
//        password = "passwd1";
//        dbService.addUDS(login, password);
    }
    @BeforeGroups("testAddUDS")
    public void setUpAddUDS() {
//        dbService = new DBServiceImpl(mockedMS);
        login = "user1";
        password = "passwd1";
        dbService.addUDS(login, password);
    }

    @Test(groups = "testAddUDS")
    public void testAddUDS() {
        System.out.println("nick"+dbService.getUDS(login, password).getNick());
        Assert.assertEquals(login, dbService.getUDS(login, password).getNick());
    }
    @AfterGroups("testAddUDS")
    public void tearDownAddUDS() {
        dbService.deleteUser(login);
    }


    @BeforeGroups("testUpdateUsers")
    public void setUpDeleteUDS() {
        login = "user";
        password = "passwd";
        users = new ArrayList<UserDataSet>();

        for(int i = 0; i < 5; i++) {
            System.out.println(login+i);
            dbService.addUDS(login+i, password+i);
            users.add(dbService.getUDS(login+i,password+i));
        }

    }

    @Test(groups = "testUpdateUsers")
    public void testDeleteUDS() {
        dbService.updateUsers(users);
//        ListIterator li = users.listIterator();
//        while(li.hasNext()) {
//            li.next();
//            Assert.assertEquals();
        }
    }

    @AfterGroups("testUpdateUsers")
    public void tearDownDeleteUDS() {

    }

//    @BeforeGroups("testDeleteUDS")
//    public void setUpDeleteUDS() {
//
//    }
//
//    @Test(groups = "testDeleteUDS")
//    public void testDeleteUDS() {
//
//    }
//
//    @AfterGroups("testDeleteUDS")
//    public void tearDownDeleteUDS() {
//
//    }

//    @AfterTest
//    public void tearDown() {
//        dbService.deleteUser(login);
//    }
}
