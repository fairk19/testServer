package frontend.handler;

import base.MessageSystem;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import org.testng.Assert;
import org.testng.annotations.*;

import org.eclipse.jetty.server.Request;
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
import java.nio.charset.Charset;

import com.google.common.io.Files;


import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.Mockito.*;
/**
 * Created by vanik on 30.03.14.
 */
public class TestNUTargetAdmin {
    private MessageSystem mockedMS = mock (MessageSystem.class);
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

    @BeforeMethod
    public void  setUpHandleNewUserTargeAdmin() {
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        target = "/admin";
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
        SysInfo sysInfo = new SysInfo();
    }

    @Test
    public void testHandleNewUserTargeAdmin() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));


        returnedPage = new File("returnedPage.html");
        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());

        expectedPage = new File("./statistic/ccu");
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));

        expectedPage = new File("./statistic/memoryUsage");
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));

        expectedPage = new File("./statistic/time");
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));

        expectedPage = new File("./statistic/totalMemory");
        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));

    }

    @AfterMethod
    public void tearDownHandleNewUserTargeAdmin() {
        returnedPage.delete();
    }

}