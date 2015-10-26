

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
@WebServlet("/ptblock")
public class Ptblock extends HttpServlet {

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
        String bname=request.getParameter("bn");
    	String queryString = prefix + "SELECT ?name WHERE { ?element table:name ?name; table:block table:"+bname+".} ";        
        
        String directory = "C:\\Users\\Nagi Reddy\\Desktop\\PERIODICTABLE" ;
        Dataset ds = TDBFactory.createDataset(directory) ;
           
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      try {
        ResultSet rs = qe.execSelect() ;
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Elements in block</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Elements in "+bname+"</h3>");
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String winner = qs.getLiteral("?name").getString();
          out.println("<P>"+winner+"</P>");
        }
        out.println("</BODY>");
        out.println("</HTML>");
      } finally { 
          qe.close() ; 
        }
      }catch (Exception e) {
         e.printStackTrace();
        } 
  }

}