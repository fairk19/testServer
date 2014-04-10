package dbService;

import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.UserDataSet;
import org.junit.Ignore;
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
//    private String url = "jdbc:mysql://localhost:3306/qualityTestDB?user=root&password=110708";
    private DBServiceImpl dbService;
    private String login;
    private String password;
    private ArrayList<UserDataSet> users;
    @BeforeClass
    public void setUp() {
        dbService = new DBServiceImpl(mockedMS);
    }

    @BeforeGroups("testAddUDS")
    public void setUpAddUDS() {
        login = "user1";
        password = "passwd1";
        dbService.addUDS(login, password);
    }
    @Test(groups = "testAddUDS")
    public void testAddUDS() {

        Assert.assertEquals(login, dbService.getUDS(login, password).getNick());
    }
    @AfterGroups("testAddUDS")
    public void tearDownAddUDS() {
        dbService.deleteUser(login);
    }



    @BeforeGroups("testAddExistsUDS")
    public void setUpAddExistsUDS(){
        login = "user1";
        password = "passwd1";
        dbService.addUDS(login, password);
    }
    @Test(groups = "testAddExistsUDS")
    public void testAddExistsUDS() {
        Assert.assertFalse(dbService.addUDS(login, password));
    }
    @AfterGroups("testAddExistsUDS")
    public void tearDownAddExistsUDS() {
        dbService.deleteUser(login);
    }




    @BeforeGroups("testUpdateUsers")
    public void setUpUpdateUDS() {
        login = "user";
        password = "passwd";
        users = new ArrayList<UserDataSet>();

        dbService.addUDS(login, password);
        users.add(dbService.getUDS(login,password));
    }

    @Test(groups = "testUpdateUsers")
    public void testUpdateUDS() {
        dbService.updateUsers(users);
        Assert.assertEquals(dbService.getUDS(login, password).getNick(), users.get(0).getNick());
    }

    @AfterGroups("testUpdateUsers")
    public void tearDownUpdateUDS() {
        dbService.deleteUser(login);
    }



    @BeforeGroups("testGetNoneExistUDS")
    public void setUpGEtNoneExistUDS() {
        login = "asdfsdafsdf";
        password = "12345";
    }

    @Test(groups = "testGetNoneExistUDS")
    public void testGetNoneExistUDS(){
        Assert.assertNull(dbService.getUDS(login, password));
    }
    @AfterGroups("testGetNoneExistUDS")
    public void tearDownGetNoneExistUDS() {

    }




    @BeforeGroups("testDeleteNoneExistsUDS")
    public void setUpDeleteNoneExistsUDS() {
        login = ";lkjas;djf;kjsdf;kjkjhkjhkd";
        password = "hkhkjhkh;hasdfn,mncxzoin";
    }
    @Test(groups = "testDeleteNoneExistsUDS")
    public void testDeleteNoneExistsUDS() {
        Assert.assertFalse(dbService.deleteUser(login));
    }
    @AfterGroups("testDeleteNoneExistsUDS")
    public void tearDownDeleteNoneExistsUDS() {

    }


    @BeforeGroups("testRun")
    public void setUpRun() {

    }

    @Test(groups = "testRun", timeOut = 500, expectedExceptions = Exception.class)
    public void testRun() {
        dbService.run();
    }
    @AfterGroups("testRun")
    public void tearDownRun(){

    }

//    @AfterTest
//    public void tearDown() {
//        dbService.deleteUser(login);
//    }
}
