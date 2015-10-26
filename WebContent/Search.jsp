<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hp.hpl.jena.query.*,com.hp.hpl.jena.tdb.*,java.util.*" %>
       
  <% 
  
  String Element = request.getParameter("value");
  String type = request.getParameter("type");
  Element = Element.toLowerCase();
  
  String ununtrium = null,
  ununpentium = null,
  ununhexium = null,
  ununseptium = null,
  ununoctium = null;
  if(type.equalsIgnoreCase("name")){
	  
	  
	  ununtrium = "ununtrium";
	  ununpentium = "ununpentium";
	  ununhexium = "ununhexium";
	  ununseptium = "ununseptium";
	  ununoctium = "ununoctium";
	  
  }
  
if(type.equalsIgnoreCase("symbol")){
	
	  ununtrium = "uut";
	  ununpentium = "uup";
	  ununhexium = "uuh";
	  ununseptium = "uus";
	  ununoctium = "uuo";
	  String temp = Element.substring(0, 1);
	  int size = Element.length();
	  temp = temp.toUpperCase();
	  if (size >= 2){ 
	  Element = temp + Element.substring(1, size);
	  }
	  else{
		  Element = temp;
	  }
	  
  }
if(type.equalsIgnoreCase("atomicnumber")){
	  
	  
	  ununtrium = "113";
	  ununpentium = "115";
	  ununhexium = "116";
	  ununseptium = "117";
	  ununoctium = "118";
}
  //System.out.println(Element);
  
    try {
      String prefix1 = "PREFIX table:<http://www.daml.org/2003/01/periodictable/PeriodicTable#>"; 
      String prefix2 = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> ";
      String prefix3 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";
      String prefix = prefix1+prefix2+prefix3;
      
      String queryString1 = null;
      

      if(Element.equalsIgnoreCase(ununtrium) || Element.equalsIgnoreCase(ununpentium) || Element.equalsIgnoreCase(ununseptium) || Element.equalsIgnoreCase(ununhexium) || Element.equalsIgnoreCase(ununoctium)){
      	
      	if(Element.equalsIgnoreCase(ununtrium) || Element.equalsIgnoreCase(ununpentium) || Element.equalsIgnoreCase(ununseptium)){
      	queryString1 = prefix+ "SELECT ?name ?symbol ?atomicnumber ?stdstate ?group ?period ?block { ?element table:name ?name. ?element table:symbol ?symbol. ?element table:atomicNumber ?atomicnumber. ?element table:standardState ?stdstate. ?element table:period ?period. ?element table:group ?group. ?element table:block ?block. FILTER (str(?"+type+") = \"" + Element + "\") } ORDER BY ?number";
      	}
      	
      	if(Element.equalsIgnoreCase(ununhexium) || Element.equalsIgnoreCase(ununoctium)){
      		
      		//System.out.println("into ununhexium");
      	
      	queryString1 = prefix+ "SELECT ?name ?symbol ?atomicnumber ?color ?classification ?casRegistryID ?stdstate ?group ?period ?block { ?element table:name ?name. ?element table:symbol ?symbol. ?element table:atomicNumber ?atomicnumber. ?element table:standardState ?stdstate. ?element table:period ?period. ?element table:group ?group. ?element table:block ?block. ?element table:color ?color. ?element table:casRegistryID ?casRegistryID. ?element table:classification ?classification. FILTER (str(?"+type+") = \"" + Element + "\") } ORDER BY ?number";
      	}
      }
      
      else{
      	
    	  queryString1 = prefix+ "SELECT ?name ?symbol ?atomicnumber ?atomicWeight ?color ?classification ?casRegistryID ?stdstate ?group ?period ?block { ?element table:name ?name. ?element table:symbol ?symbol. ?element table:atomicNumber ?atomicnumber. ?element table:atomicWeight ?atomicWeight. ?element table:standardState ?stdstate. ?element table:period ?period. ?element table:group ?group. ?element table:block ?block. ?element table:color ?color. ?element table:casRegistryID ?casRegistryID. ?element table:classification ?classification. FILTER (str(?"+type+") = \"" + Element + "\") } ORDER BY ?atomicnumber";
      	
      }
      
        String directory = "C:/Users/Nagi Reddy/Desktop/PERIODICTABLE" ;
        Dataset ds = TDBFactory.createDataset(directory) ;
        Query query = QueryFactory.create(queryString1);
        QueryExecution qe = QueryExecutionFactory.create(query, ds) ;
       
        try {
          ResultSet rs = qe.execSelect(); 
  		%><table border = "1">
  		<caption>Element</caption>
  		<th>Property</th>
  		<th>Detail</th>

  		
  		<% 
  	
          while(rs.hasNext()){
        
            QuerySolution qs = rs.nextSolution();
            String name = qs.getLiteral("?name").getString();
            String symbol = qs.getLiteral("?symbol").getString();
            String atomicnumber = qs.getLiteral("?atomicnumber").getString();
            String atomicWeight = "";
            //System.out.println(Element);
            //if((Element != "ununtrium") && (Element != "ununpentium") && (Element != "ununseptium") && (Element != "ununhexium") && (Element != "ununoctium")){
          	  if(!(Element.equalsIgnoreCase(ununtrium) || Element.equalsIgnoreCase(ununpentium) || Element.equalsIgnoreCase(ununseptium) || Element.equalsIgnoreCase(ununhexium) || Element.equalsIgnoreCase(ununoctium))){
          	  //System.out.println("should not go");
            atomicWeight = qs.getLiteral("?atomicWeight").getString();
            }
            
            String group = qs.getResource("?group").getLocalName();
            String period = qs.getResource("?period").getLocalName();
            String block = qs.getResource("?block").getLocalName();
            String stdstate = qs.getResource("?stdstate").getLocalName();
            String color = "";
            String classification = "";
            String casRegistryID = "";
            if(!(Element.equalsIgnoreCase(ununtrium) || Element.equalsIgnoreCase(ununpentium) || Element.equalsIgnoreCase(ununseptium))){
            color = qs.getLiteral("?color").getString();
            classification = qs.getResource("?classification").getLocalName();
            casRegistryID = qs.getLiteral("?casRegistryID").getString();
            
          }

           %>
        	<tr><td>Name</td><td><%=name%></td></tr>
        	<tr><td>Symbol</td><td><%=symbol%></td></tr>
        	<tr><td>Atomic Number</td><td><%=atomicnumber%></td></tr>
        	<tr><td>Atomic Weight</td><td><%=atomicWeight%></td></tr>
        	<tr><td>Group</td><td><%=group%></td></tr>
        	<tr><td>Period</td><td><%=period%></td></tr>
        	<tr><td>Block</td><td><%=block%></td></tr>
        	<tr><td>Standard State</td><td><%=stdstate%></td></tr>
        	<tr><td>Color</td><td><%=color%></td></tr>
        	<tr><td>Classification</td><td><%=classification%></td></tr>
        	<tr><td>CasRegistryID</td><td><%=casRegistryID%></td></tr>
           <%
          }
  		%></table><%

        } finally { 
            qe.close() ;
           
          }
        }catch (Exception e) {
           e.printStackTrace();
          }%> 