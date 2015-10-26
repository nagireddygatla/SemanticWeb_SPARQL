package Jena;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDB extends HttpServlet {

  

protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
      String prefix1 = "PREFIX nob:<http://swat.cse.lehigh.edu/resources/onto/nobel.owl#> "; 
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1+prefix2+prefix3;
      String queryString = prefix+
        "SELECT ?n ?y { ?s nob:WonPrize ?p. ?p rdf:type nob:PeacePrize. ?s nob:name ?n. ?p nob:yearWon ?y. } ORDER BY ?y" ;
      System.out.println(queryString);
      //String directory = "http://tinman.cs.gsu.edu/raj/public_html/8711/sp15/jena/NOBELDB" ;
      String directory = "C:/Users/Nagi Reddy/Desktop/NOBELDB" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
           
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      try {
        ResultSet rs = qe.execSelect() ;
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>SDB Query Servlet</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY BGCOLOR=\"#FFFFFF\">");
        out.println("<h3>Peace Prize Winners</h3>");
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String winner = qs.getLiteral("?n").toString();
          int year = qs.getLiteral("?y").getInt();
          out.println("<P>"+winner+" ("+year+")");
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
