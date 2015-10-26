import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ptblocks")
public class Ptblocks extends HttpServlet {

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
        out.println("<TITLE>Periodic Table Blocks Page</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Periodic Table Blocks</h3>");
        out.println("<table><tr>");
        
        out.println("<td><input type='button' value='s-block' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptblock?bn=s-block\"\'/></TD>");
        out.println("<td><input type='button' value='p-block' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptblock?bn=p-block\"'/></TD>");
        out.println("<td><input type='button' value='d-block' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptblock?bn=d-block\"'/></TD>");
        out.println("<td><input type='button' value='f-block' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptblock?bn=f-block\"'/></TD>");
        	
        
        out.println("</BODY>");
        out.println("</HTML>");
       
  }

}