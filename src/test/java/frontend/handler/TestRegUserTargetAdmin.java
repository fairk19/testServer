//package frontend.handler;
//
//import base.MessageSystem;
//import com.google.common.io.Files;
//import dbService.UserDataSet;
//import frontend.FrontendImpl;
//import frontend.UserDataImpl;
//import org.eclipse.jetty.server.Request;
//import org.testng.Assert;
//import org.testng.annotations.AfterGroups;
//import org.testng.annotations.BeforeGroups;
//import org.testng.annotations.Test;
//import utils.SysInfo;
//import utils.TemplateHelper;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import static java.nio.charset.Charset.defaultCharset;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by vanik on 07.04.14.
// */
//public class TestRegUserTargetAdmin {
//    private MessageSystem mockedMS = mock (MessageSystem.class);
//    private FrontendImpl frontend;
//    private String target;
//    private Request baseRequest;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private String SESSION_ID_FIELD = "sessionId";
//    private String sessionIdValue = "123";
//    private String START_SERVER_TIME_FIELD = "startServerTime";
//    private String startServerTimeValue = UserDataImpl.getStartServerTime();
//
//    private File returnedPage;
//    private File expectedPage;
//
//
//
//
//    @BeforeGroups("RegUserTargetAdmin")
//    public void  setUpRegUserTargetAdmin() {
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
//        Assert.assertTrue(returnedPageAsString.contains(Files.toString(expectedPage, defaultCharset())));
//        Assert.assertNotNull(UserDataImpl.getUserSessionBySessionId(sessionIdValue));
//    }
//
//    @AfterGroups("RegUserTargetAdmin")
//    public void tearDownRegUserTargetAdmin() {
//        returnedPage.delete();
//    }
//}
