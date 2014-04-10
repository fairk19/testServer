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
import utils.SysInfo;
import utils.TemplateHelper;
import utils.TimeHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
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
    private String SESSION_ID_FIELD;
    private String sessionIdValue;
    private String START_SERVER_TIME_FIELD;
    private String startServerTimeValue;
    private Cookie mockedCookieSessionId;
    private Cookie mockedCookieServerTime;

    private File returnedPage;
    private File expectedPage;

    private File expectedCCU;
    private File expectedMemoryUsage;
    private File expectedTime;
    private File expectedTotalMemory;

    PrintWriter pwCCU;
    PrintWriter pwMU;
    PrintWriter pwTime;
    PrintWriter pwTM;



//    @BeforeGroups("RegUserTargetAdmin")
//    public void  setUpRegUserTargetAdmin() throws IOException{
//        expectedCCU = new File("./statistic/ccu");
//        expectedMemoryUsage = new File("./statistic/memoryUsage");
//        expectedTime = new File("./statistic/time");
//        expectedTotalMemory = new File("./statistic/totalMemory");
//        pwCCU = new PrintWriter(expectedCCU);
//        pwCCU.print("1,1");
//        pwCCU.close();
//
//        pwMU= new PrintWriter(expectedMemoryUsage);
//        pwMU.print("9970576,10109392");
//        pwMU.close();
//
//        pwTime = new PrintWriter(expectedTime);
//        pwTime.print("\'15:45:00\',\'15:45:00\'");
//        pwTime.close();
//
//        pwTM = new PrintWriter(expectedTotalMemory);
//        pwTM.print("120782848,120782848");
//        pwTM.close();
//
//
//        SESSION_ID_FIELD = "sessionId";
//        sessionIdValue = "123";
//        START_SERVER_TIME_FIELD = "startServerTime";
//        startServerTimeValue = UserDataImpl.getStartServerTime();
//        mockedMS = mock(MessageSystem.class);
//        frontend = new FrontendImpl(mockedMS);
//        response = mock(HttpServletResponse.class);
//        request = mock(HttpServletRequest.class);
//        target = "/admin";
//        baseRequest = mock(Request.class);
//        Cookie mockedCookieSessionId = mock(Cookie.class);
//        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
//        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
//        Cookie mockedCookieServerTime = mock(Cookie.class);
//        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
//        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
//        try {
//            PrintWriter writer = new PrintWriter("returnedPage.html");
//            when(response.getWriter()).thenReturn(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
//        when(request.getCookies()).thenReturn(arrCookies);
//        when(request.getMethod()).thenReturn("GET");
//        TemplateHelper.init();
//        UserDataImpl.putSessionIdAndUserSession(sessionIdValue, new UserDataSet());
//        SysInfo sysInfo = new SysInfo();
//    }
//
//    @Test(groups = "RegUserTargetAdmin")
//    public void testRegUserTargetAdmin() throws IOException {
//        frontend.handle(target,baseRequest,request,response);
//
//        returnedPage = new File("returnedPage.html");
//        String returnedPageAsString = new String();
//        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
//
//        expectedCCU = new File("./statistic/ccu");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedCCU, defaultCharset())));
//
//        expectedMemoryUsage = new File("./statistic/memoryUsage");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedMemoryUsage, defaultCharset())));
//
//        expectedTime = new File("./statistic/time");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedTime, defaultCharset())));
//
//        expectedTotalMemory = new File("./statistic/totalMemory");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedTotalMemory, defaultCharset())));
//
//        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
//    }
//
//    @AfterGroups("RegUserTargetAdmin")
//    public void tearDownRegUserTargetAdmin() throws IOException{
//        returnedPage.delete();
//
//
//        pwCCU = new PrintWriter(expectedCCU);
//        pwCCU.print("");
//        pwCCU.close();
//
//        pwMU = new PrintWriter(expectedMemoryUsage);
//        pwMU.print("");
//        pwMU.close();
//
//        pwTime = new PrintWriter(expectedTime);
//        pwTime.print("");
//        pwTime.close();
//
//        pwTM = new PrintWriter(expectedTotalMemory);
//        pwTM.print("");
//        pwTM.close();
//    }

//
//    @BeforeGroups("NUTargetRules")
//    public void  setUpNUTargetRuless() {
//        UserDataImpl.clearAllMaps();
//        mockedMS = mock(MessageSystem.class);
//
//        SESSION_ID_FIELD = "sessionId";
//        sessionIdValue = "123";
//        START_SERVER_TIME_FIELD = "startServerTime";
//        startServerTimeValue = UserDataImpl.getStartServerTime();
//        frontend = new FrontendImpl(mockedMS);
//        response = mock(HttpServletResponse.class);
//        request = mock(HttpServletRequest.class);
//        target = "/rules";
//        baseRequest = mock(Request.class);
//        Cookie mockedCookieSessionId = mock(Cookie.class);
//        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
//        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
//        Cookie mockedCookieServerTime = mock(Cookie.class);
//        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
//        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
//        try {
//            PrintWriter writer = new PrintWriter("returnedPage.html");
//            when(response.getWriter()).thenReturn(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
//        when(request.getCookies()).thenReturn(arrCookies);
//        TemplateHelper.init();
//    }
//
//    @Test(groups = "NUTargetRules")
//    public void testNUTargetRuless() throws IOException {
//        frontend.handle(target,baseRequest,request,response);
//
//        returnedPage = new File("returnedPage.html");
//        expectedPage = new File("./static/html/rules.html");
//
//        String returnedPageAsString = new String();
//        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
//        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));
//
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
//    }
//
//    @AfterGroups("NUTargetRules")
//    public void tearDownNUTargetRuless() {
//        UserDataImpl.clearAllMaps();
//        returnedPage.delete();
//    }





    @BeforeGroups("RegUserTargetReg")
    public void  setUpRegUserTargetReg() {

        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
    }

    @Test(groups = "RegUserTargetReg")
    public void testRegUserTargetReg() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/reg.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("RegUserTargetReg")
    public void tearDownRegUserTargetReg() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("RegUserTargetReg")
    public void  setUpRegUserTargetMainPage() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


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
    }

    @Test(groups = "RegUserTargetReg")
    public void testRegUserTargetMainPage() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/index.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("RegUserTargetReg")
    public void tearDownRegUserTargetMainPage() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();

    }







    @BeforeGroups("NUTargetMainPage")
    public void  setUpNUTargetMainPage() {
        UserDataImpl.clearAllMaps();
        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/";
        baseRequest = mock(Request.class);
        mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);

        mockedCookieServerTime = mock(Cookie.class);
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
        TemplateHelper.init();
    }

    @Test(groups = "NUTargetMainPage")
    public void testNUTargetMainPage() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/index.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));

        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("NUTargetMainPage")
    public void tearDownNUTargetMainPage() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();
    }




    @BeforeGroups("RegUserTargetGame")
    public void  setUpRegUserTargetGame() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

        mockedMS = mock(MessageSystem.class);
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
    }

    @Test(groups = "RegUserTargetGame")
    public void testRegUserTargetGame() throws IOException {
        frontend.handle(target,baseRequest,request,response);
        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location", "/");
    }

    @AfterGroups("RegUserTargetGame")
    public void tearDownRegUserTargetGame() {
        UserDataImpl.clearAllMaps();
    }




    @BeforeGroups("RegUserTarget404")
    public void  setUpRegUserTarget404() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


        mockedMS = mock(MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/notFound";
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
    }

    @Test(groups = "RegUserTarget404")
    public void testRegUserTarget404() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/404.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());

        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("RegUserTarget404")
    public void tearDownRegUserTarget404() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();
    }












    @BeforeGroups("NUTargetGame")
    public void  setUpNUTargetGame() {
        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();
        mockedMS = mock(MessageSystem.class);

        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/game";
        baseRequest = mock(Request.class);
        mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        mockedCookieServerTime = mock(Cookie.class);
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
        TemplateHelper.init();
    }

    @Test(groups = "NUTargetGame")
    public void testNUTargetGame() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(response).addHeader("Location", "/");

    }

    @AfterGroups("NUTargetGame")
    public void tearDownNUTargetGame() {
    }







//    @BeforeGroups("NUTargetAdmin")
//    public void  setUpNUTargetAdmin() {
//        SESSION_ID_FIELD = "sessionId";
//        sessionIdValue = "123";
//        START_SERVER_TIME_FIELD = "";
//        startServerTimeValue = UserDataImpl.getStartServerTime();
//
//        mockedMS = mock (MessageSystem.class);
//        frontend = new FrontendImpl(mockedMS);
//        response = mock(HttpServletResponse.class);
//        request = mock(HttpServletRequest.class);
//        target = "/admin";
//        baseRequest = mock(Request.class);
//        Cookie mockedCookieSessionId = mock(Cookie.class);
//        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
//        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
//        Cookie mockedCookieServerTime = mock(Cookie.class);
//        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
//        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
//        try {
//            PrintWriter writer = new PrintWriter("returnedPage.html");
//            when(response.getWriter()).thenReturn(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
//        when(request.getCookies()).thenReturn(arrCookies);
//        TemplateHelper.init();
//        SysInfo sysInfo = new SysInfo();
//    }
//
//    @Test(groups = "NUTargetAdmin")
//    public void testNUTargetAdmin() throws IOException {
//        frontend.handle(target,baseRequest,request,response);
//
//        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));
//        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
//
//
//        returnedPage = new File("returnedPage.html");
//        String returnedPageAsString = new String();
//        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
//
//        expectedPage = new File("./statistic/ccu");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//
//        expectedPage = new File("./statistic/memoryUsage");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//
//        expectedPage = new File("./statistic/time");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//
//        expectedPage = new File("./statistic/totalMemory");
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//
//    }
//
//    @AfterGroups("NUTargetAdmin")
//    public void tearDownNUTargetAdmin() {
//        returnedPage.delete();
//    }








    @BeforeGroups("NUTarget404")
    public void  setUpNUTarget404() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

        mockedMS = mock (MessageSystem.class);
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/notFound";
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
        TemplateHelper.init();
    }

    @Test(groups = "NUTarget404")
    public void testNUTarget404() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/404.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));

        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("NUTarget404")
    public void tearDownNUTarget404() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();
    }


    @BeforeGroups("NickAndPasswd=null")
    public void  setUpRegNickAndPasswdAreNull() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";

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
        TimeHelper.sleep(200);
        expectedPage = new File("./static/html/reg.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterGroups("NickAndPasswd=null")
    public void tearDownRegNickAndPasswdAreNull() {
        returnedPage.delete();
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("NickAndPasswordAreNotNull")
    public void setUpNickAndPasswordAreNotNull() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
        UserDataImpl.clearAllMaps();
    }





    @BeforeGroups("RegNickAndPasswordAreNotNull")
    public void setUpRegNickAndPasswordAreNotNull() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
        UserDataImpl.clearAllMaps();
    }


    @BeforeGroups("RegNickAndPasswordAreNull")
    public void setUpRegNickAndPasswordAreNull() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
        TimeHelper.sleep(200);
        expectedPage = new File("./static/html/reg.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }
    @AfterGroups("RegNickAndPasswordAreNull")
    public void tearDownRegDownNickAndPasswordAreNull() {
        UserDataImpl.clearAllMaps();
        returnedPage.delete();
    }



    @BeforeGroups("TargetWaitAndPostStatusZero")
    public void setUpTargetWaitAndPostStatusZero() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
        UserDataImpl.clearAllMaps();
    }


    @BeforeGroups("TargetWait")
    public void setUpTargetWait() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


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
        TimeHelper.sleep(200);
        expectedPage = new File("./static/html/wait.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }
    @AfterGroups("TargetWait")
    public void tearDownTargetWait() {
        UserDataImpl.clearAllMaps();
        returnedPage.delete();
    }





    @BeforeGroups("StatusReadyTargetGame")
    public void setUpStatusReadyTargetGame() {
        UserDataImpl.clearAllMaps();
        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();



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
        UserDataImpl.clearAllMaps();
        returnedPage.delete();
    }



    @BeforeGroups("StatusReadyTargetMainPage")
    public void setUpStatusReadyTargetMainPage() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


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
        TimeHelper.sleep(200);
        expectedPage = new File("./static/html/index.html");


        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
        Assert.assertNotNull(UserDataImpl.getLogInUserBySessionId(sessionIdValue));
    }
    @AfterGroups("StatusReadyTargetMainPage")
    public void tearDownStatusReadyTargetMainPage() {
        UserDataImpl.clearAllMaps();
        returnedPage.delete();
    }






    @BeforeGroups("StatusReadyTargetLogout")
    public void setUpStatusReadyTargetLogout() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();



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
        UserDataImpl.clearAllMaps();

    }






    @BeforeGroups("StatusReadyTargetProfile")
    public void setUpStatusReadyTargetProfile() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();


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
//        Assert.assertNotNull(UserDataImpl.getLogInUserBySessionId(sessionIdValue));

    }
    @AfterGroups("StatusReadyTargetProfile")
    public void tearDownStatusReadyTargetProfile() {
        UserDataImpl.clearAllMaps();
        returnedPage.delete();
    }





    @BeforeGroups("StatusReadyTargetReg")
    public void setUpStatusReadyTargetReg() {
        UserDataImpl.clearAllMaps();

        SESSION_ID_FIELD = "sessionId";
        sessionIdValue = "123";
        START_SERVER_TIME_FIELD = "startServerTime";
        startServerTimeValue = UserDataImpl.getStartServerTime();

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
        UserDataImpl.clearAllMaps();
    }


}
