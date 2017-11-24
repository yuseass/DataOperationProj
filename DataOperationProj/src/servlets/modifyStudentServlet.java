package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import dao.StudentDao;

public class modifyStudentServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public modifyStudentServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String id=(String) request.getParameter("id");
		String name=(String) request.getParameter("name");
		String sex=(String) request.getParameter("sex");
		String major=(String) request.getParameter("major");
		String hometown=(String) request.getParameter("hometown");
		
		Student student1=new Student();//表示新学生信息
		Student student=new Student();//表示原学生信息
		student=(Student) request.getSession().getAttribute("student");
		student1.setId(id);
		student1.setName(name);
		student1.setSex(sex);
		student1.setMajor(major);
		student1.setHometown(hometown);
		
		try {
			StudentDao sd=new StudentDao();
			if(sd.modifyStudent(student, student1)){
				
				sd.close();
				
				response.sendRedirect("/DataOperationProj/DataInputServlet");
				
				
			}
			
			
			
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
