package frontend.handler;

import base.MessageSystem;
import com.google.common.io.Files;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import org.eclipse.jetty.server.Request;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.SHA2;
import utils.TemplateHelper;
import utils.TimeHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.Mockito.*;

/**
 * Created by vanik on 07.04.14.
 */
public class TestNUTargetMainPage {
    private MessageSystem mockedMS = mock (MessageSystem.class);
    private Cookie mockedCookieSessionId;
    private Cookie mockedCookieServerTime;
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
    public void  setUpHandleNewUserTargetMainPage() {
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

    @Test
    public void testHandleNewUserTargetMainPage() throws IOException {
        frontend.handle(target,baseRequest,request,response);

        returnedPage = new File("returnedPage.html");
        expectedPage = new File("./static/html/index.html");

        String returnedPageAsString = new String();
        returnedPageAsString = Files.toString(returnedPage, defaultCharset());
        sessionIdValue = SHA2.getSHA2(String.valueOf(frontend.getCreatorSessionId().intValue()));

        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
    }

    @AfterMethod
    public void tearDownHandleNewUserTargetMainPage() {
        returnedPage.delete();
        TimeHelper.sleep(100);
    }

}
