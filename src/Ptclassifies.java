import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ptclassifies")
public class Ptclassifies extends HttpServlet {

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
        out.println("<TITLE>Periodic Table Periods Page</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Periodic Table: Periods</h3>");
        out.println("<table><tr>");
        
        out.println("<td><input type='button' value='Metallic' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptclassify?pclass=Metallic\"\'/></TD>");
        out.println("<td><input type='button' value='Non-Metallic' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptclassify?pclass=Non-metallic\"\'/></TD>");
        out.println("<td><input type='button' value='Semi-Metallic' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptclassify?pclass=Semi-metallic\"\'/></TD>");
        	
        out.println("</tr></table>");
        out.println("</BODY>");
        out.println("</HTML>");
       
  }

}