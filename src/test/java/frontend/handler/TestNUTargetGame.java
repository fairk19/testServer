//package frontend.handler;
//
//import base.MessageSystem;
//import frontend.FrontendImpl;
//import frontend.UserDataImpl;
//import org.eclipse.jetty.server.Request;
//import org.testng.annotations.AfterGroups;
//import org.testng.annotations.BeforeGroups;
//import org.testng.annotations.Test;
//import utils.TemplateHelper;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import static org.mockito.Mockito.*;
//
///**
// * Created by vanik on 07.04.14.
// */
//public class TestNUTargetGame {
//    private MessageSystem mockedMS;
//    private Cookie mockedCookieSessionId;
//    private Cookie mockedCookieServerTime;
//    private FrontendImpl frontend;
//    private String target;
//    private Request baseRequest;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private String SESSION_ID_FIELD;
//    private String sessionIdValue;
//    private String START_SERVER_TIME_FIELD;
//    private String startServerTimeValue;
//
//
//
//    private File returnedPage;
//    private File expectedPage;
//
//    @BeforeGroups("NUTargetGame")
//    public void  setUpNUTargetGame() {
//        SESSION_ID_FIELD = "sessionId";
//        sessionIdValue = "123";
//        START_SERVER_TIME_FIELD = "startServerTime";
//        startServerTimeValue = UserDataImpl.getStartServerTime();
//        mockedMS = mock(MessageSystem.class);
//
//        frontend = new FrontendImpl(mockedMS);
//        response = mock(HttpServletResponse.class);
//        request = mock(HttpServletRequest.class);
//        target = "/game";
//        baseRequest = mock(Request.class);
//        mockedCookieSessionId = mock(Cookie.class);
//        when(mockedCookieSessionId.getName()).thenReturn(SESSION_ID_FIELD);
//        when(mockedCookieSessionId.getValue()).thenReturn(sessionIdValue);
//        mockedCookieServerTime = mock(Cookie.class);
//        when(mockedCookieServerTime.getName()).thenReturn(START_SERVER_TIME_FIELD);
//        when(mockedCookieServerTime.getValue()).thenReturn(startServerTimeValue);
//
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
//    @Test(groups = "NUTargetGame")
//    public void testNUTargetGame() throws IOException {
//        frontend.handle(target,baseRequest,request,response);
//
//
//        verify(response).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
//        verify(response).addHeader("Location", "/");
//
//    }
//
//    @AfterGroups("NUTargetGame")
//    public void tearDownNUTargetGame() {
//    }
//}
