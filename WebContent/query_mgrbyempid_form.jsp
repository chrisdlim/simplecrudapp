<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager by Employee Id</title>
</head>
<body>
	<div class="forms">
    	<img border="0" src="ibmpos_blue.jpg">
		
		<h1>Get Manager by Employee Id</h1>
		<form method="POST" action='HomeServlet' name="frmQuery">
		
		    <input type="hidden" name="type" value="query">
		    <input type="hidden" name="action" value="getmgrbyempidquery">
			
			Type in an Employee's ID <input type="text" name="empId">	
			
			<input type="submit" name="Submit">
		</form>
		<div class="buttons">     
            <p><a href="index.jsp">Go Back Home</a>
        </div>
	</div>
</body>
</html>