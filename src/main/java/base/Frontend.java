package base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public interface Frontend extends Abonent{
	public void handle(String target,Request baseRequest,
			HttpServletRequest request, HttpServletResponse response) 
					throws IOException, ServletException;
}