package beans;

import java.sql.*;



import java.util.*;

import javax.activation.DataSource;
import javax.enterprise.context.spi.Context;
import javax.naming.*;

import javassist.bytecode.Descriptor.Iterator;

public class DBBean {
		private Connection con;
		private Statement stmt;
		private ResultSet rs;
		private ResultSet resultset;
		
		public Connection getConnection() throws ClassNotFoundException, SQLException {
			String driver = "com.mysql.jdbc.Driver";
			String dburl = "jdbc:mysql://localhost:3306/studentinfo?useUnicode=true&amp;characterEncoding=UTF-8";
			String username = "root";
			String password = "";
			
			Class.forName(driver); // 加载驱动程序
			// 创建连接对象
			con = DriverManager.getConnection(dburl, username, password);
			stmt=con.createStatement();
			return con;
			//从数据源获得数据库连接
		}
		public ArrayList getStudentsFromDB() throws SQLException{
			ArrayList<Student> StudentList = new ArrayList<Student>();
			
			String sql = "SELECT * FROM student";
			
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Student student=new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setHometown(rs.getString("hometown"));
				student.setMajor(rs.getString("major"));
				StudentList.add(student);
			}
			
			return StudentList;
			//从数据库表中读取数据到ArrayList中
		}
		public void insertDBStudents(ArrayList<Student> students) throws SQLException{
			//将ArrayList中的数据存储到数据库表中
			
			java.util.Iterator<Student> it=students.iterator();
			while (it.hasNext()) {
				Student student=(Student) it.next();
				
				String id=student.getId();
				String name=student.getName();
				String sex=student.getSex();
				String hometown=student.getHometown();
				String major=student.getMajor();
				
				String sql="INSERT INTO student VALUES('"+id+"','"
						+name+"','"+sex+"','"+major+"','"+hometown+"');";
				
				
				
				stmt.executeUpdate(sql);
			}
			
	
		}

}
