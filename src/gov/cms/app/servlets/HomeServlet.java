package gov.cms.app.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.*;
import com.test.model.*;

//@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private ManagerDao mgrDao;
	private EmployeeDepartmentDao empDeptDao;
	private EmployeeManagerDao empMgrDao;
	private EmployeeDepartmentManagerDao empDeptMgrDao;
	
	private static String EMP_INSERT_OR_EDIT 		= "/employee.jsp";
	private static String DEPT_INSERT_OR_EDIT 		= "/department.jsp";
	private static String MGR_INSERT_OR_EDIT 		= "/manager.jsp";
	private static String LIST_MANAGER 				= "/listmanager.jsp";
	private static String LIST_EMPLOYEE				= "/listemployee.jsp";
	private static String LIST_DEPARTMENT	 		= "/listdepartment.jsp";
	private static String QUERY_DEPTBYEMPID_FORM	= "/query_deptbyempid_form.jsp";
	private static String QUERY_DEPTBYEMPID			= "/query_deptbyempid.jsp";
	private static String QUERY_EMPBYDEPTID_FORM 	= "/query_empbydeptid_form.jsp"; 
    private static String QUERY_EMPBYDEPTID 		= "/query_empbydeptid.jsp";
    private static String QUERY_EMPBYMGRID_FORM		= "/query_empbymgrid_form.jsp";
    private static String QUERY_EMPBYMGRID			= "/query_empbymgrid.jsp";
    private static String QUERY_MGRBYEMPID_FORM  	= "/query_mgrbyempid_form.jsp";
    private static String QUERY_MGRBYEMPID 			= "/query_mgrbyempid.jsp";
    private static String SHOW_ALL 					= "/showall.jsp";
			
    public HomeServlet() {
    	super();
    	empDao = new EmployeeDao();
    	deptDao = new DepartmentDao();
    	mgrDao = new ManagerDao();
    	empDeptDao = new EmployeeDepartmentDao();
    	empMgrDao = new EmployeeManagerDao();
    	empDeptMgrDao = new EmployeeDepartmentManagerDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String type = request.getParameter("type");
		
		//employee operations
		if(type.equalsIgnoreCase("employee")){
			
			switch(action){
			case "empdelete":
				servletDeleteEmployee(request, response);
				break;
			case "empedit":
				servletEditEmployee(request, response);
				break;
			case "listemployee":
				servletShowEmployees(request, response);
				break;
			case "empinsert":
				servletInsertEmployee(request, response);
				break;
			default: 
				servletShowEmployees(request, response);
				break;
			}
		} 
		else if (type.equalsIgnoreCase("department")){
			switch(action){
			
			case "deptdelete":
				servletDeptDelete(request, response);
				break;
			case "deptedit":
				servletDeptEdit(request, response);
				break;
			case "listdepartment":
				servletShowDepartments(request, response);
				break;
			case "deptinsert":
				servletDeptInsert(request, response);
				break;
			default:
				servletShowDepartments(request, response);
				break;
			}
		}
		else if (type.equalsIgnoreCase("manager")){
			switch(action){
			
			case "mgrdelete":
				servletMgrDelete(request, response);
				break;
			case "mgredit":
				servletMgrEdit(request, response);
				break;
			case "listmanager":
				servletShowManagers(request, response);
				break;
			case "mgrinsert":
				servletMgrInsert(request, response);
				break;
			default:
				servletShowManagers(request, response);
				break;
			}
		}
		else if (type.equalsIgnoreCase("query")){			
			switch(action){
			case "getdeptbyempid":
				servletGetDeptByEmpIdQuery(request, response);
				break;
			case "getempbydeptid":
				servletGetEmpByDeptIdQuery(request, response);
				break;
			case "getempbymgrid":
				servletGetEmpByMgrId(request, response);
				break;
			case "getmgrbyempid":
				servletGetMgrByEmpId(request, response);
				break;
			default:
				System.out.println("Default");
				break;
			}
		}
		else if(type.equalsIgnoreCase("all")){
			String forward = SHOW_ALL;
			 request.setAttribute("all", empDeptMgrDao.showAll());
			 RequestDispatcher view = request.getRequestDispatcher(forward);
			 view.forward(request, response);		
		}
	}

	private void servletGetMgrByEmpId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = QUERY_MGRBYEMPID_FORM;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);		
	}

	private void servletGetEmpByMgrId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = QUERY_EMPBYMGRID_FORM;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletGetEmpByDeptIdQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = QUERY_EMPBYDEPTID_FORM;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletGetDeptByEmpIdQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = QUERY_DEPTBYEMPID_FORM;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletMgrInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String forward = MGR_INSERT_OR_EDIT;
	   RequestDispatcher view = request.getRequestDispatcher(forward);
	   view.forward(request, response);
		
	}

	private void servletShowManagers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_MANAGER;
        request.setAttribute("managers", mgrDao.getAllManagers());
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletMgrEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = MGR_INSERT_OR_EDIT;
        String mgrAsId = request.getParameter("mgrAsId");
        Manager manager = mgrDao.getManagerById(mgrAsId);
        request.setAttribute("manager", manager);
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletMgrDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_MANAGER;
        String mgrAsId = request.getParameter("mgrAsId");
        mgrDao.deleteManager(mgrAsId);
        request.setAttribute("manager", mgrDao.getAllManagers()); 
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletDeptInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = DEPT_INSERT_OR_EDIT;
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletShowDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = LIST_DEPARTMENT;
         request.setAttribute("departments", deptDao.getAllDepartments());
         RequestDispatcher view = request.getRequestDispatcher(forward);
 		 view.forward(request, response);
	}

	private void servletDeptEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = DEPT_INSERT_OR_EDIT;
        String deptId = request.getParameter("deptId");
        Department department = deptDao.getDepartmentById(deptId);
        request.setAttribute("department", department);
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletDeptDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_DEPARTMENT;
		String deptId = request.getParameter("deptId");
        deptDao.deleteDepartment(deptId);
        request.setAttribute("departments", deptDao.getAllDepartments());
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletDeleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_EMPLOYEE;;
		String empId = request.getParameter("empId");
        empDao.deleteEmployee(empId);
        request.setAttribute("employees", empDao.getAllEmployees()); 
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	
	private void servletEditEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = EMP_INSERT_OR_EDIT;
        String empId = request.getParameter("empId");
        Employee employee = empDao.getEmployeeById(empId);
        request.setAttribute("employee", employee);
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	private void servletShowEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = LIST_EMPLOYEE;
        request.setAttribute("employees", empDao.getAllEmployees());
        RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	private void servletInsertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = EMP_INSERT_OR_EDIT;
        RequestDispatcher view = request.getRequestDispatcher(forward);
 		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String action = request.getParameter("action");
		
		//employee operations
		if(type.equalsIgnoreCase("employee")){
			Employee employee = new Employee();
			String name 	  = request.getParameter("name");
			String empId 	  = request.getParameter("empId");
			String deptId     = request.getParameter("deptId");
			
			employee.setName(name);
			employee.setEmpId(empId);
			employee.setDeptId(deptId);
			
			if(empId == null || empId.isEmpty())
	        {
	            empDao.addEmployee(employee);
	        }
	       else
	        {
	            employee.setEmpId(empId);
	            empDao.checkEmployee(employee);
	        }
			request.setAttribute("employees", empDao.getAllEmployees());
			RequestDispatcher view = request.getRequestDispatcher(LIST_EMPLOYEE);
			view.forward(request,response);
		}
		
		//department operations
		else if(type.equalsIgnoreCase("department")){
			Department department = new Department();
			String deptName 	  = request.getParameter("deptName");
			String deptId 		  = request.getParameter("deptId");
			String deptDesc		  = request.getParameter("deptDesc");
			
			department.setDeptName(deptName);
			department.setDeptId(deptId);
			department.setDeptDesc(deptDesc);

			if(deptId == null || deptId.isEmpty())
	        {
	            deptDao.addDepartment(department);
	        }
	       else
	        {
	            department.setDeptId(deptId);
	            deptDao.checkDepartment(department);
	        }
			request.setAttribute("departments", deptDao.getAllDepartments());
			RequestDispatcher view = request.getRequestDispatcher(LIST_DEPARTMENT);
			view.forward(request,response);
		}
		else if(type.equalsIgnoreCase("manager")){
			Manager manager = new Manager();
			String mgrAsId  = request.getParameter("mgrAsId");
			String mgrId 	= request.getParameter("mgrId");
			String empId 	= request.getParameter("empId");
			
			manager.setMgrAsId(mgrAsId);
			manager.setMgrId(mgrId);
			manager.setEmpId(empId);
			
			if(mgrAsId == null || mgrAsId.isEmpty())
	        {
	            mgrDao.addManager(manager);
	        }
	       else
	        {
	    	    manager.setMgrAsId(mgrAsId);
	            mgrDao.checkManager(manager);
	        }
			request.setAttribute("managers", mgrDao.getAllManagers());
			RequestDispatcher view = request.getRequestDispatcher(LIST_MANAGER);
			view.forward(request,response);
		}
		//query operations
		else if(type.equalsIgnoreCase("query")){
			switch(action){
			case "getdeptbyempidquery":
				postGetDeptByEmpIdQuery(request, response);
				break;
			case "getempbydeptidquery":
				postGetEmpByDeptIdQuery(request, response);
				break;
			case "getempbymgridquery":
				postGetEmpByMgrId(request, response);
				break;
			case "getmgrbyempidquery":
				postGetMgrByEmpId(request, response);
				break;
			default:
				System.out.println("Default");
				break;
			}
		}
	}

	private void postGetMgrByEmpId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		
		//check that mgr exists
		if(empMgrDao.checkEmployee(empId)){
			//show employee's manager
			EmployeeManager empMgr = empMgrDao.getEmployeeManagerByEmpId(empId);
			request.setAttribute("empMgr", empMgr);
			RequestDispatcher view = request.getRequestDispatcher(QUERY_MGRBYEMPID);
			view.forward(request,  response);
		}
		
	}

	private void postGetEmpByMgrId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mgrId = request.getParameter("mgrId");
		
		//check that mgr exists
		if(empMgrDao.checkManager(mgrId)){
			//show employees for this manager
			List<EmployeeManager> employees = empMgrDao.getEmployeesByMgrId(mgrId);
			request.setAttribute("empMgr", employees);
			RequestDispatcher view = request.getRequestDispatcher(QUERY_EMPBYMGRID);
			view.forward(request,  response);
		}
			
	}

	private void postGetEmpByDeptIdQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		
		//check that department exists
		if(empDeptDao.checkDepartment(deptId)){
			//show employees in department
			List<EmployeeDepartment> employeeDepartments = empDeptDao.getEmployeeDepartmentByDeptId(deptId);
			request.setAttribute("empDepts", employeeDepartments);
			RequestDispatcher view = request.getRequestDispatcher(QUERY_EMPBYDEPTID);
			view.forward(request, response);
		}
	}

	private void postGetDeptByEmpIdQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		
		//check that employee exists
		if(empDeptDao.checkEmployee(empId)){
			//show employee's department information
			EmployeeDepartment employeeDepartment = empDeptDao.getEmployeeDepartmentByEmpId(empId);
			request.setAttribute("employeedepartment", employeeDepartment);
			RequestDispatcher view = request.getRequestDispatcher(QUERY_DEPTBYEMPID);
			view.forward(request, response);
		}		
	}
}
