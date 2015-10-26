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
@WebServlet("/ptele")
public class Ptele extends HttpServlet {

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
    	String elem=request.getParameter("elem");
        String queryString = prefix + "SELECT * WHERE { ?element table:name ?name. ?element table:symbol ?symbol .";       
        queryString= queryString+ "?element table:atomicWeight ?weight.";
        queryString= queryString+ "?element table:group ?groupno.";
        queryString= queryString+ "?element table:period ?period.";
        queryString= queryString+ "?element table:block ?block.";
        queryString= queryString+ "?element table:standardState ?state.";
        queryString= queryString+ "?element table:color ?color.";
        queryString= queryString+ "?element table:classification ?classification.";
        queryString= queryString+ "?element table:casRegistryID ?casRegId.";
        
        queryString= queryString+ "?element table:atomicNumber ?number.  FILTER(str(?symbol)=\""+elem+"\") } ";
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
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Element Properties</h3>");
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String ename = qs.getLiteral("?name").getString();
          //int year = qs.getLiteral("?p").getInt();
          out.println("<P>Element:&nbsp;"+ename+"</P>");
          out.println("<P> Symbol:&nbsp;"+qs.getLiteral("?symbol").getString()+"</P>");
          out.println("<P> Atomic Number:&nbsp; "+qs.getLiteral("?number").getString()+"</P>");
          out.println("<P> Atomic Weight:&nbsp; "+qs.getLiteral("?weight").getString()+"</P>");
          out.println("<P> Group:&nbsp; "+qs.getResource("?groupno").toString().substring(56)+"</P>");
          out.println("<P> Period:&nbsp; "+qs.getResource("?period").toString().substring(56)+"</P>");
          out.println("<P> Block:&nbsp; "+qs.getResource("?block").toString().substring(56)+"</P>");
          out.println("<P> Standard State:&nbsp; "+qs.getResource("?state").toString().substring(56)+"</P>");
          out.println("<P> Color:&nbsp; "+qs.getLiteral("?color").getString()+"</P>");
          out.println("<P> Classification:&nbsp; "+qs.getResource("?classification").toString().substring(56)+"</P>");
          out.println("<P> CasRegistryID:&nbsp; "+qs.getLiteral("?casRegId").getString()+"</P>");
          
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