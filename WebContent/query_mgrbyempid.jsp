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
<title>Manager Query by Employee Id</title>
</head>
<body>
  
  <img border="0" src="ibmpos_blue.jpg">
  <h1>Get Employee Manager by Employee Id</h1>
	 <table class="table table-bordered table-striped" border=1>
        <thead>
            <tr>
            	<th>Manager Id</th>
            	<th>Manager Name</th>
                <th>Employee Id</th>
                <th>Employee Name</th>
            </tr>
        </thead>
        <tbody>
             <tr>
                 <td><c:out value="${empMgr.mgrId}" 	/></td>
                 <td><c:out value="${empMgr.mgrName}"   /></td>
                 <td><c:out value="${empMgr.empId}"    /></td>
                 <td><c:out value="${empMgr.empName}"  /></td>           
             </tr>
        </tbody>
    </table>
    <div class="buttons">
    	<p><a href="index.jsp">Go Back Home</a>
    </div>
</body>
</html>