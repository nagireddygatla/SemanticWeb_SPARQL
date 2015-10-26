import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ptgroups")
public class Ptgroups extends HttpServlet {

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
        out.println("<TITLE>Periodic Table Groups Page</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Periodic Table Groups</h3>");
        out.println("<table><tr>");
        
        for(int i=1;i<=18;i++)
        {
        	if(i==10)
        	{
        		out.println("</tr><tr><td><input type='button' value='Group "+i+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptgroup?gno=group_"+i+"\"\'/></TD>");
        	}
        	else
        	{
        		out.println("<td><input type='button' value='Group "+i+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptgroup?gno=group_"+i+"\"\'/></TD>");
        	}
        }	
        
        out.println("</BODY>");
        out.println("</HTML>");
       
  }

}