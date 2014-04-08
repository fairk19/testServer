package frontend.handler;

import base.MessageSystem;
import com.google.common.io.Files;
import dbService.UserDataSet;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.Request;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.SHA2;
import utils.TemplateHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.Mockito.*;

/**
 * Created by vanik on 07.04.14.
 */
public class TestRegUserTargetRegPost {
    private MessageSystem mockedMS;
    private FrontendImpl frontend;
    private String target;
    private Request baseRequest;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String SESSION_ID_FIELD = "sessionId";
    private String sessionIdValue = "123";
    private String START_SERVER_TIME_FIELD = "startServerTime";
    private String startServerTimeValue = UserDataImpl.getStartServerTime();

    private File returnedPage;
    private File expectedPage;




    @BeforeGroups("NickAndPasswd=null")
    public void  setUpRegNickAndPasswdAreNull() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/reg";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("POST");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
    }

    @Test(groups = "NickAndPasswd=null")
    public void testRegNickAndPasswdAreNull() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/reg.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("NickAndPasswd=null")
    public void tearDownRegNickAndPasswdAreNull() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }





    @BeforeGroups("NickAndPasswordAreNotNull")
    public void setUpNickAndPasswordAreNotNull() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/reg";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("POST");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        when(request.getParameter("nick")).thenReturn("nick");
        when(request.getParameter("password")).thenReturn("passwd");

    }
    @Test(groups = "NickAndPasswordAreNotNull")
    public void testNickAndPasswordAreNotNull() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(mockedMS).getAddressByName("DBService");
        verify(mockedMS).getAddressByName("UserData");

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location", "/wait");
    }
    @AfterGroups("NickAndPasswordAreNotNull")
    public void tearDownNickAndPasswordAreNotNull() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
    }





    @BeforeGroups("RegNickAndPasswordAreNotNull")
    public void setUpRegNickAndPasswordAreNotNull() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/reg";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("POST");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());

        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn("nick");
        when(request.getParameter("regPassword")).thenReturn("passwd");

    }
    @Test(groups = "RegNickAndPasswordAreNotNull")
    public void testRegNickAndPasswordAreNotNull() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(mockedMS,times(1)).getAddressByName("DBService");
        verify(mockedMS,times(1)).getAddressByName("UserData");

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location", "/wait");
    }
    @AfterGroups("RegNickAndPasswordAreNotNull")
    public void tearDownRegDownNickAndPasswordAreNotNull() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
    }


    @BeforeGroups("RegNickAndPasswordAreNull")
    public void setUpRegNickAndPasswordAreNull() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/reg";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("POST");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());

        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);

    }
    @Test(groups = "RegNickAndPasswordAreNull")
    public void testRegNickAndPasswordAreNull() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/reg.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }
    @AfterGroups("RegNickAndPasswordAreNull")
    public void tearDownRegDownNickAndPasswordAreNull() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }



    @BeforeGroups("TargetWaitAndPostStatusZero")
    public void setUpTargetWaitAndPostStatusZero() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/wait";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());

        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);

    }
    @Test(groups = "TargetWaitAndPostStatusZero")
    public void testTargetWaitAndPostStatusZero() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location","/");
    }
    @AfterGroups("TargetWaitAndPostStatusZero")
    public void tearDownTargetWaitAndPostStatusZero() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
    }


    @BeforeGroups("TargetWait")
    public void setUpTargetWait() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/wait";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "TargetWait")
    public void testTargetWait() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(response).setStatus(HttpServletResponse.SC_OK);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/wait.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }
    @AfterGroups("TargetWait")
    public void tearDownTargetWait() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }





    @BeforeGroups("StatusReadyTargetGame")
    public void setUpStatusReadyTargetGame() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/game";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setId(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "StatusReadyTargetGame")
    public void testStatusReadyTargetGame() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(response).setStatus(HttpServletResponse.SC_OK);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/game.html");


        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
        Assert.assertNotNull(UserDataImpl.getLogInUserBySessionId(sessionIdValue));
    }
    @AfterGroups("StatusReadyTargetGame")
    public void tearDownStatusReadyTargetGame() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }

    @BeforeGroups("StatusReadyTargetMainPage")
    public void setUpStatusReadyTargetMainPage() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setId(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "StatusReadyTargetMainPage")
    public void testStatusReadyTargetMainPage() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        verify(response).setStatus(HttpServletResponse.SC_OK);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/index.html");


        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
        Assert.assertNotNull(UserDataImpl.getLogInUserBySessionId(sessionIdValue));
    }
    @AfterGroups("StatusReadyTargetMainPage")
    public void tearDownStatusReadyTargetMainPage() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }






    @BeforeGroups("StatusReadyTargetLogout")
    public void setUpStatusReadyTargetLogout() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/logout";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setId(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "StatusReadyTargetLogout")
    public void testStatusReadyTargetLogout() throws IOException{
        frontend.handle(target,baseRequest,request,response);


        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location", "/");

        Assert.assertNotNull(UserDataImpl.
                getUserSessionBySessionId(SHA2.
                        getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()))));


    }
    @AfterGroups("StatusReadyTargetLogout")
    public void tearDownStatusReadyTargetLogout() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        UserDataImpl.removeSessionIdAndUserSession(SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue())));

    }






    @BeforeGroups("StatusReadyTargetProfile")
    public void setUpStatusReadyTargetProfile() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/profile";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setId(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "StatusReadyTargetProfile")
    public void testStatusReadyTargetProfile() throws IOException{
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        String expectedNick = "";
        String expectedRating = "0";

        Assert.assertTrue(returnedPageAsString.contains(expectedNick));
        Assert.assertTrue(returnedPageAsString.contains(expectedRating));

        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
        Assert.assertNotNull(UserDataImpl.getLogInUserBySessionId(sessionIdValue));

    }
    @AfterGroups("StatusReadyTargetProfile")
    public void tearDownStatusReadyTargetProfile() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
        returnedPage.delete();
    }





    @BeforeGroups("StatusReadyTargetReg")
    public void setUpStatusReadyTargetReg() {
        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/reg";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("returnedPage.html");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        when(request.getMethod()).thenReturn("GET");
        TemplateHelper.init();
        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setPostStatus(10);
        UserDataImpl.getUserSessionBySessionId(sessionIdValue).setId(10);
        when(request.getParameter("nick")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        when(request.getParameter("regNick")).thenReturn(null);
        when(request.getParameter("regPassword")).thenReturn(null);



    }
    @Test(groups = "StatusReadyTargetReg")
    public void testStatusReadyTargetReg() throws IOException{
        frontend.handle(target,baseRequest,request,response);


        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location","/");



    }
    @AfterGroups("StatusReadyTargetReg")
    public void tearDownStatusReadyTargetReg() {
        UserDataImpl.removeSessionIdAndUserSession(sessionIdValue);
    }


}