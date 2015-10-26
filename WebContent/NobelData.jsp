<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hp.hpl.jena.query.*,com.hp.hpl.jena.tdb.*" %>
    
    
    
  <% 
    try {
      String prefix1 = "PREFIX nob:<http://swat.cse.lehigh.edu/resources/onto/nobel.owl#> "; 
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1+prefix2+prefix3;
      String queryString = prefix+
        "SELECT ?n ?y { ?s nob:WonPrize ?p. ?p rdf:type nob:PeacePrize. ?s nob:name ?n. ?p nob:yearWon ?y. } ORDER BY ?y" ;
      //System.out.println(queryString);
      //String directory = "http://tinman.cs.gsu.edu/raj/public_html/8711/sp15/jena/NOBELDB" ;
      String directory = "C:/Users/Nagi Reddy/Desktop/NOBELDB" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      try {
        ResultSet rs = qe.execSelect();
		%><table border = "1">
		<th>Winner</th>
		<th>Year</th>
		<% 
        while(rs.hasNext()){
          QuerySolution qs = rs.nextSolution();
          String winner = qs.getLiteral("?n").toString();
          int year = qs.getLiteral("?y").getInt();
         %>
      	<tr>   
         <td><%=winner%></td>
         <td><%=year%></td>
		</tr>
         <%
        }
		%></table><%

      } finally { 
          qe.close() ; 
        }
      }catch (Exception e) {
         e.printStackTrace();
        }%> 