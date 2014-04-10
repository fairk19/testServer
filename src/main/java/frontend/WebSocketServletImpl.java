package frontend;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import base.MessageSystem;

public class WebSocketServletImpl extends WebSocketServlet {
	private static final long serialVersionUID = 5178761620260814811L;
	private MessageSystem messageSystem = null;

	public WebSocketServletImpl(MessageSystem msgSystem) {
		messageSystem=msgSystem;
	}

	public void configure(WebSocketServletFactory factory) {
		WebSocketImpl.setMS(messageSystem); 
		factory.register(WebSocketImpl.class);
	}
}