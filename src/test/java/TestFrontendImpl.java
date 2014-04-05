import base.MessageSystem;
import frontend.FrontendImpl;
import frontend.UserDataImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import org.eclipse.jetty.server.Request;
import utils.TemplateHelper;
import utils.TimeHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;
/**
 * Created by vanik on 30.03.14.
 */
public class TestFrontendImpl {
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
    @BeforeGroups("handle")
    public void  setUpHandle() {
        frontend = new FrontendImpl(mockedMS);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
//        try {
//            when(response.getWriter()).thenReturn(new PrintWriter());
//        } catch (IOException e) {}
        target = "/rules";
        baseRequest = mock(Request.class);
        Cookie mockedCookieSessionId = mock(Cookie.class);
        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
        Cookie mockedCookieServerTime = mock(Cookie.class);
        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
        try {
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(response.getWriter()).thenReturn(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Cookie[] arrCookiesExists = {new Cookie(SESSION_ID_FIELD,sessionId),
//                               new Cookie("startServerTime", queryTime)
//                              };
        Cookie[] arrCookies = {mockedCookieSessionId, mockedCookieServerTime};
        when(request.getCookies()).thenReturn(arrCookies);
        TemplateHelper.init();
    }

    @Test(groups = "handle")
    public void testHandle() {
        frontend.handle(target,baseRequest,request,response);
//        File returnedPage = new File("somefile.txt");
//        System.out.println(returnedPage.compareTo(new File("./static/html/rules.html")));
//        returnedPage.compareTo(new File("./static/html/rules.html"));
        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
        Assert.assertEquals(UserDataImpl.getUserSessionBySessionId(sessionIdValue).getLastVisit(),startServerTimeValue);
    }
}
