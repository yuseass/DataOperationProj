package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;

import beans.Student;

import javax.naming.*;

public class StudentDao {
		DataSource dataSource;
		public StudentDao() throws NamingException{
			
			Context context = new InitialContext();
			dataSource =(DataSource)context.lookup("java:comp/env/jdbc/sampleDS");
			//查找数据源
		}
		
		public void close() throws SQLException{		
			dataSource.getConnection().close();
		}
		
		
		// 返回一个连接对象
		public Connection getConnection() throws Exception{
			return dataSource.getConnection(); 
		}
		
		
		//从数据库表中读取数据到ArrayList中
		public ArrayList getStudentsFromDB() throws SQLException{
			ArrayList<Student> StudentList = new ArrayList<Student>();
			
			String sql = "SELECT * FROM student";
			
			Connection con = dataSource.getConnection();
	   		PreparedStatement pstmt = con.prepareStatement(sql);
	   		ResultSet rs=pstmt.executeQuery();
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
			
		}
		
		//添加一个学生到数据库
		public boolean addStudent(Student student){
			String sql = "INSERT INTO student VALUES(?,?,?,?,?)";
			Connection con;
			try {
				//连接，准备sql语句
				con = dataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				
		   		pstmt.setString(1, student.getId());
		   		pstmt.setString(2, student.getName());
		   		pstmt.setString(3, student.getSex());
		   		pstmt.setString(4, student.getMajor());
		   		pstmt.setString(5, student.getHometown());
				
				pstmt.executeUpdate();//执行
				return true;//成功则返回true
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		//按id查找学生
		public Student findStudent(String id){
			String sql = "SELECT * FROM student WHERE id=?";
			Student student=new Student();
			
			Connection con;
			try {
				con = dataSource.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					student.setId(rs.getString("id"));
					student.setName(rs.getString("name"));
					student.setSex(rs.getString("sex"));
					student.setMajor(rs.getString("major"));
					student.setHometown(rs.getString("hometown"));	
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return student;
		}
		
		//修改一个学生
		public boolean modifyStudent(Student student,Student student1){
			//student为原数据，student1为新数据
			
			String sql="UPDATE student SET "
					+ "id=?,name=?,sex=?,major=?,hometown=? WHERE "
					+ "id=?";
			Connection conn;
			try {
				conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, student1.getId());
				pstmt.setString(2, student1.getName());
				pstmt.setString(3, student1.getSex());
				pstmt.setString(4, student1.getMajor());
				pstmt.setString(5, student1.getHometown());
				pstmt.setString(6, student.getId());
				
				pstmt.executeUpdate();
				return true;
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}	
			
		}
		

		//删除一个学生
		public boolean deleteStudent(Student student){
			
			String sql="DELETE FROM student WHERE id=?";
			//sql删除语句
			Connection conn;
			try {
				conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		   		pstmt.setString(1, student.getId());
		   		pstmt.executeUpdate();
		   		
		   		return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	
		}

		
		
		
		
		
		
		
		
		
		
}
