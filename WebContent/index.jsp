<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="styles.css">
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CMS Evaluation</title>
    </head>
    <body>
    	<img border="0" src="ibmpos_blue.jpg">
    	<h1><b>CMS Evaluation App</b></h1>
    	<div class="container-fluid text-center" id="links">
    	<table class="table table-bordered table-striped" border=1>
    		<thead>
    			<tr>
    				<th>Employees</th>
    				<th>Departments</th>
    				<th>Manager Association</th>
   				</tr>
   			</thead>
   			<tbody>
   				<tr>
	   				<td>
	   					<a href="HomeServlet?type=employee&action=listemployee">View/Edit Employees</a><br>
		    			<a href="HomeServlet?type=employee&action=empinsert">Add an Employee</a><br>
		    			<a href="HomeServlet?type=query&action=getempbydeptid">Find Employees by Department Id</a><br>
		    			<a href="HomeServlet?type=query&action=getempbymgrid">Find Employees by Manager Id</a>
   		
		    			
		    		</td>
		    		<td>
			    		<a href="HomeServlet?type=department&action=listdepartment">View/Edit Departments</a><br>
			    		<a href="HomeServlet?type=department&action=deptinsert">Add a Department</a><br>
			    		<a href="HomeServlet?type=query&action=getdeptbyempid">Find a Department by Employee Id</a>
		    		</td>
		    		<td>
			    		<a href="HomeServlet?type=manager&action=listmanager">View/Edit Managers</a><br>
			    		<a href="HomeServlet?type=manager&action=mgrinsert">Add a Manager</a><br>
			    		<a href="HomeServlet?type=query&action=getmgrbyempid">Find a Manager by Employee Id</a>
	   				</td>
   				</tr>
   			</tbody>
   		</table>
   		<a href ="HomeServlet?type=all&action=showall">Show All Tables</a>
   	</div>
    
    </body>
    
    
    </html>