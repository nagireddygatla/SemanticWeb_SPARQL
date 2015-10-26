<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hp.hpl.jena.query.*,com.hp.hpl.jena.tdb.*,java.util.*" %>
       
  <% 
  
  //String subclass = request.getParameter("subclass");
  //String subclass1 = request.getParameter("subclass1");
    try {
      String prefix1 = "PREFIX table:<http://www.daml.org/2003/01/periodictable/PeriodicTable#>"; 
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1+prefix2+prefix3;
      String queryString1 = prefix+ "SELECT DISTINCT ?class ?grpname { ?Element table:group ?class. ?class table:name ?grpname. FILTER (str(?grpname) = \"Halogen\")}";
      
      
      //String queryString1 = prefix+ "SELECT DISTINCT ?elem ?symbol ?number {?element table:name ?elem. ?element table:symbol ?symbol. ?element table:atomicNumber ?number. ?element table:"+subclass+" table:"+subclass1+"}" ;
    //String queryString1 = prefix+ "SELECT DISTINCT ?elem ?symbol ?weight ?color {?element table:name ?elem. ?element table:symbol ?symbol. ?element table:atomicWeight ?weight. ?element table:color ?color. ?element table:"+subclass+"}" ;

      String directory = "C:/Users/Nagi Reddy/Desktop/PERIODICTABLE" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
      Query query = QueryFactory.create(queryString1);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      String fina = null;
      try {
        ResultSet rs = qe.execSelect();
    
        while(rs.hasNext()){
        	
          QuerySolution qs = rs.nextSolution();
          fina = qs.getResource("?class").getLocalName();
        String symb = qs.getLiteral("?grpname").getString();
           
          

        }%><Table>
        <% 
        System.out.println(fina);
        String queryString2 = prefix+ "SELECT ?grpname (COUNT(*) AS ?count) { ?Element table:group ?class. ?class table:name ?grpname} GROUP BY ?grpname";
        Query query2 = QueryFactory.create(queryString2);
        QueryExecution qe2 = QueryExecutionFactory.create(query2, ds) ;
        ResultSet rs2 = qe2.execSelect();
        while(rs2.hasNext()){
        	
            QuerySolution qs2 = rs2.nextSolution();
            String grpname = qs2.getLiteral("?grpname").getString();
            String count = qs2.getLiteral("?count").getString();
          %>
             
   <tr><td> <%=grpname%></td><td> <%=count%></td></tr>
<% 
          }
        
%></Table><%         
        
      } finally { 
          qe.close() ;
         
        }
      }catch (Exception e) {
         e.printStackTrace();
        }%> 