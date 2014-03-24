package main;

import messageSystem.MessageSystemImpl;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import chat.ChatWSServletImpl;
import chat.GameChatImpl;

import dbService.DBServiceImpl;

import resource.ResourceFactory;

import utils.SysInfo;
import utils.TemplateHelper;
//import utils.VFS;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import frontend.WebSocketServletImpl;

import frontend.FrontendImpl;
import gameMechanic.GameMechanicImpl;

//import accountService.AccountServiceImpl;
//import base.AccountService;
import base.DataAccessObject;
import base.GameChat;
import base.GameMechanic;
import base.MessageSystem;
import base.UserData;

public class Main{

	public static void main(String[] args) throws Exception{
		MessageSystem messageSystem= new MessageSystemImpl();
		FrontendImpl frontend = new FrontendImpl(messageSystem);
//		AccountService accountService = new AccountServiceImpl(messageSystem);
		GameMechanic gameMechanic = new GameMechanicImpl(messageSystem);
		UserData userData = new UserDataImpl(messageSystem);
		DataAccessObject dbService = new DBServiceImpl(messageSystem);
		SysInfo sysInfo = new SysInfo();
		
		Server server = new Server(8000);
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setResourceBase("static");

		Server serverWS = new Server(8050);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS); 
		serverWS.setHandler(context); 
		context.addServlet(new ServletHolder(new WebSocketServletImpl(messageSystem)),"/*");
		serverWS.start();

		Server chatServer = new Server(8010);
		ServletContextHandler context2 = new ServletContextHandler(ServletContextHandler.SESSIONS);
		chatServer.setHandler(context2);
		context2.addServlet(new ServletHolder(new ChatWSServletImpl()),"/*");
		chatServer.start();

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] {frontend, resource_handler});
		server.setHandler(handlers);

		WebSocketImpl webSocket = new WebSocketImpl(true);
		GameChat gameChat = new GameChatImpl(messageSystem);

//		(new Thread(accountService)).start();
		(new Thread(sysInfo)).start();
		(new Thread(dbService)).start();
		(new Thread(userData)).start();
		(new Thread(gameMechanic)).start();
		(new Thread(webSocket)).start();
		(new Thread(gameChat)).start();
		server.start();
		TemplateHelper.init();
		ResourceFactory.instanse();
	}
}