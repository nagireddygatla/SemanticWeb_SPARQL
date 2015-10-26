<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hp.hpl.jena.query.*,com.hp.hpl.jena.tdb.*,java.util.*" %>
       
  <%
  String subclass = request.getParameter("id");
    try {
      String prefix1 = "PREFIX table:<http://www.daml.org/2003/01/periodictable/PeriodicTable#>"; 
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1+prefix2+prefix3;
      String queryString = null;
      if(!(subclass.equalsIgnoreCase("element"))){
      queryString = prefix+ "SELECT DISTINCT ?class { ?Element table:standardState ?class.}" ;
      }
      
      else
    	  
      {
    	  
    	  queryString = prefix+ "SELECT DISTINCT ?class {?element table:name ?class. ?element table:atomicNumber ?number.}" ;
      }
    

      String directory = "C:/Users/Nagi Reddy/Desktop/PERIODICTABLE" ;
      Dataset ds = TDBFactory.createDataset(directory) ;
      Query query = QueryFactory.create(queryString);
      QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
      QueryExecution qe1 = null;
      try {
      ResultSet rs = qe.execSelect();
        ResultSet rs1 = null;
    
        if(subclass.equalsIgnoreCase("group")){
        	
        	  
                  String queryString1 = prefix+ "SELECT DISTINCT ?class ?name { ?Element table:group ?class. ?class table:name ?name.}";
                  
              Query query1 = QueryFactory.create(queryString1);
              qe1 = QueryExecutionFactory.create(query1, ds) ;
              
      			rs1 = qe1.execSelect();
        }
		%><table border = "1">
		<%if(subclass.equalsIgnoreCase("element")){ %>
		<caption>Elements</caption><%}else{ %>
		<caption>Type</caption>
		<% }%>
		<th><%=subclass%></th>
	
		
		<% 
		List<String> list = new ArrayList<String>();
        if(subclass.equalsIgnoreCase("group")){
            while(rs1.hasNext()){
          	  
          	  QuerySolution qs1 = rs1.nextSolution();
          	  String nam = qs1.getLiteral("?name").getString();
          	  String grp = qs1.getResource("?class").getLocalName();
          	  String namgrp = nam+"-"+grp;
          	  list.add(namgrp);
          		
            }
          	  
            } 
	
        while(rs.hasNext()){
        	
          QuerySolution qs = rs.nextSolution();
          String fina = null;
          String othruse = null;
          if(subclass.equalsIgnoreCase("element")){
        	  
        	  fina = qs.getLiteral("?class").getString();
              othruse = qs.getLiteral("?class").getString();
        	  
          }else{
        	  
          fina = qs.getResource("?class").getLocalName();
          othruse = qs.getResource("?class").getLocalName();
          }
          ///System.out.println("hello");
          if(subclass.equalsIgnoreCase("group")){
          for (int i = 0; i < list.size(); i++) {
        	  
        	 String temp = list.get(i);
        	 String[] parts = temp.split("-");
        	 if(parts[1].equalsIgnoreCase(fina)){
        		 
        		 fina = fina + "/"+parts[0];
        		 
        	 }
        	 
          }
          }
          
          if(subclass.equalsIgnoreCase("element")){
  
         %>
         
         <tr>
         <td>
         <a href="#" id = "elementData.jsp?Element=<%=fina%>" onclick="elementdata(this.id);">
         <%=fina%>
         </a></td>
		</tr>
      
         <%
          }
          else{%>
          	<tr>
         <td>
         <a href="#" id = "classelmts.jsp?subclass1=<%=othruse%>&subclass=<%=subclass%>" onclick="classelements(this.id);">
         <%=fina%>
         </a></td>
		</tr>
        <%}}
		%></table><%

      } finally { 
          qe.close() ;
          if(subclass.equalsIgnoreCase("group")){
          qe1.close();
          }
        }
      }catch (Exception e) {
         e.printStackTrace();
        }%> 