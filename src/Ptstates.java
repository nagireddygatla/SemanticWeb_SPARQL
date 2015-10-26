import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ptstates")
public class Ptstates extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Periodic Table Standard States Page</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Periodic Table: Standard States</h3>");
        out.println("<table><tr>");
        
        out.println("<td><input type='button' value='state_unknown' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptstate?pstate=state_unknown\"\'/></TD>");
        out.println("<td><input type='button' value='gas' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptstate?pstate=gas\"\'/></TD>");
        out.println("<td><input type='button' value='solid' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptstate?pstate=solid\"\'/></TD>");
        out.println("<td><input type='button' value='liquid' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptstate?pstate=liquid\"\'/></TD>");
        	
        out.println("</tr></table>");
        out.println("</BODY>");
        out.println("</HTML>");
       
  }

}