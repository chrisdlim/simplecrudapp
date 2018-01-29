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
<title>Show All Tables</title>
</head>
<body>
<img border="0" src="ibmpos_blue.jpg">
<h1>Show All Tables</h1>
	 <table class="table table-bordered table-striped" border=1>
        <thead>
            <tr>
                <th>Employee Id</th>
                <th>Employee Name</th>
                <th>Employee Department Id</th>
                <th>Department Name</th>
                <th>Department Description</th>
                <th>Manager Association Id</th>
                <th>Manager Id</th>
                <th>Manager Name</th>
            </tr>
        </thead>
        <tbody>
             <c:forEach items="${all}" var="all">
             <tr>
                 <td><c:out value="${all.empId}" 	/></td>
                 <td><c:out value="${all.empName}"   /></td>
                 <td><c:out value="${all.deptId}"    /></td>
                 <td><c:out value="${all.deptName}"  /></td>                                     
                 <td><c:out value="${all.deptDesc}"  /></td>
                 <td><c:out value="${all.mgrAsId}"  /></td>
                 <td><c:out value="${all.mgrId}"  /></td>
                 <td><c:out value="${all.mgrName}"  /></td>
             </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="buttons">     
        <p><a href="index.jsp">Go Back Home</a>
    </div>
</body>
</html>