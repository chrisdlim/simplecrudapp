<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Employee</title>
</head>
<body>
	<div class="forms">
    	<img border="0" src="ibmpos_blue.jpg">
		<h1>Employee Information</h1>
			<form method="POST" action='HomeServlet' name="frmAddEmployee">
	            <% String action = request.getParameter("action");%>
	            <input type="hidden" name="type" value="employee">
	            
	            <label>Employee Name</label> : <input
	              	type="text" name="name"
	                value="<c:out value="${employee.name}" />" /> <br /> 
	            
	            <% if (action.equalsIgnoreCase("empedit")) {%>
	            <label>Employee Id</label> : <input type="text" name="empId" 
	            				value="<c:out value="${employee.empId}" />" readonly="readonly" /> 
	            <%} else {%>
	            <label>Employee Id</label> : <input type="text" name="empId"
	                            value="<c:out value="${employee.empId}" />" /> <br />
	            <%}%>
	            
	            <label>Department ID</label> : <input
	                type="text" name="deptId"
	                value="<c:out value="${employee.deptId}" />" /> <br /> 
	               
	            <input  type="submit" value="Submit" />
	        </form>
	       
	        <div class="buttons">     
	            <p><a href="index.jsp">Go Back Home</a>
	        </div>
   	</div>
</body>
</html>