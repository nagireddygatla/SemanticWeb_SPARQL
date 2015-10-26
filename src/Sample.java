import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.TDBFactory;
@SuppressWarnings("serial")
@WebServlet("/home")
public class Sample extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    try {
      
      String prefix="PREFIX table: <http://www.daml.org/2003/01/periodictable/PeriodicTable#>";
      String queryString = prefix + "SELECT ?symbol WHERE { ?element table:symbol ?symbol.} " ;
        
        
      String directory = "/student/svuppala2/public_html/pt/NOBELDB" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
           
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ; 
      try {
        ResultSet rs = qe.execSelect() ;
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Periodic Table Home Page</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\"><center>");
        out.println("<h3>Periodic Table Home Page</h3>");
        String [] symbols=new String[200];
        int i=1;
        
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String winner = qs.getLiteral("?symbol").getString();
          symbols[i]=winner;
          i++;
        }
       
        out.print("<TABLE border=1 width=800px>");
       
        for(i=1;i<=symbols.length; i++)
        {
        	if(i==1)
        	{
        		out.print("<TR>"+"<TD>"+"<input type='button' value='"+symbols[i]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i]+"\"\'/></TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		out.print("<td>"+""+"</td>"+"<td>"+""+"</td>");
        		out.print("<TD> <input type='button' value='"+symbols[i+1]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+1]+"\"\' /></TD>");
        		out.print("</TR>");
        		i=2;
        	}
        	else if(i==3)
        	{
        		out.print("<TR>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i]+"\"\'/></TD>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i+1]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+1]+"\"\'/></TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		for(int j=2;j<8;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}       		
        		out.print("</TR>");
        		i=10;
        	}
        	else if(i==11)
        	{
        		out.print("<TR>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i]+"\"\'/></TD>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i+1]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+1]+"\"\'/></TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+"");
        		out.print("</TD>"+"<TD>"+" "+"</TD>"+"<TD>"+" "+"</TD>"+"<TD>"+" "+"</TD>"+"<TD>"+" "+"</TD>");
        		out.print("<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>"+"<TD>"+""+"</TD>");
        		for(int j=2;j<8;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=18;
        	}
        	else if(i==19)
        	{
        		out.print("<TR>");
        		for(int j=0;j<18;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=36;
        	}
        	else if(i==37)
        	{
        		out.print("<TR>");
        		for(int j=0;j<18;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=54;
        	}
        	else if(i==55)
        	{
        		out.print("<TR>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i]+"\"\'/></TD>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i+1]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+1]+"\"\'/></TD>");
        		out.print("<td>57-71</td>");
        		i=72;
        		for(int j=0;j<15;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=86;
        	}
        	else if(i==87)
        	{
        		out.print("<TR>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i]+"\"\'/></TD>");
        		out.print("<TD>"+"<input type='button' value='"+symbols[i+1]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+1]+"\"\'/></TD>");
        		out.print("<TD>89-103</TD>");
        		i=104;
        		for(int j=0;j<15;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=118;
        	}
        }
        
        out.print("</table><br/><br/><br/><TABLE border=1>");
        
        for(i=1;i<=symbols.length; i++)
        {
        	if(i==57)
        	{
        		out.print("<TR>");
        		out.print("<td>57-71</td>");
        		for(int j=0;j<18;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=71;
        	}
        	else if(i==89)
        	{
        		out.print("<TR>");
        		out.print("<td>89-104</td>");
        		for(int j=0;j<18;j++)
        		{
        			out.print("<TD>"+"<input type='button' value='"+symbols[i+j]+"' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptele?elem="+symbols[i+j]+"\"\'/></TD>");
        		}
        		out.print("</TR>");
        		i=103;
        	}
        }
        
        out.println("</table>");
        
        out.println("<br/><input type='button' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptgroups\"\' value='Browse Elements By Groups' />");
        out.println("<br/><input type='button' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptperiods\"\' value='Browse Elements By Periods' />");
        out.println("<br/><input type='button' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptblocks\"\' value='Browse Elements By Blocks' />");
        out.println("<br/><input type='button' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptclassifies\"\' value='Browse Elements By Classification' />");
        out.println("<br/><input type='button' onclick='location.href=\"http://tinman.cs.gsu.edu:8080/svuppala2/Sample/ptstates\"\' value='Browse Elements By Standard State' />");
        out.println("</center></BODY>");
        out.println("</HTML>");
      } 
      finally { 
          qe.close() ; 
        }
    }
    catch (Exception e) {
         e.printStackTrace();
        } 
  }

}