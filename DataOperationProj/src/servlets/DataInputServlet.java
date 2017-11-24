package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBBean;
import beans.Student;
import javassist.bytecode.Descriptor.Iterator;

public class DataInputServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public DataInputServlet() {
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
		
		
		
		
		String filePath="C:\\Users\\Administrator\\Workspaces\\MyEclipse 2017 CI\\DataOperationProj\\data\\student.txt";
			File file = new File(filePath);  
			
		DBBean db=new DBBean();
			try {
				db.getConnection();
				
				BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
				String s = null;
				
				ArrayList<Student> studentlist= new ArrayList<Student>();
				Student student=new Student();
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	               String data[]=s.split("=");
	               if(data[0].equals("id")) student.setId(data[1]);
	               if(data[0].equals("name")) student.setName(data[1]);
	               if(data[0].equals("sex")) student.setSex(data[1]);
	               if(data[0].equals("hometown")) student.setHometown(data[1]);
	               if(data[0].equals("major")) student.setMajor(data[1]);
	            }
	            studentlist.add(student);
	            
	            
	            ArrayList<Student> studentlist1=new ArrayList<Student>();
	            studentlist1=db.getStudentsFromDB();
	            
	            int tag=0;
	            java.util.Iterator<Student> iterator=studentlist1.iterator();
	            while(iterator.hasNext()){	            
	        	   Student student1=iterator.next();
	        	   if(student1.getId().equals(student.getId()))tag=1; 
	           }
	            //查找数据库，判断是否已存入数据库
	            
	           if(tag==0)
	        	   db.insertDBStudents(studentlist);
	           //数据库中不存在，则插入

				studentlist=db.getStudentsFromDB();
				
				request.getSession().setAttribute("studentlist", studentlist);
				response.sendRedirect("/DataOperationProj/dataOperation.jsp");
				
			} catch (ClassNotFoundException e) {
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
