package dao;

import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;



public class StudentDAO {
	//for debugging purpose
	
//   public static void main(String[] args){
//		System.out.println("ssss");
//		StudentDAO sd = new StudentDAO();
//		ArrayList<Student> students = sd.retrieveStudentBySection("G10");
//		for(Student s:students)
//		{
//			System.out.println(s.getSmu_email_id());
//		}
//	}
	
	    private static final String TBLNAME = "student";
	
    private void handleSQLException(SQLException ex, String sql, String... parameters) {
        String msg = "Unable to access data; SQL=" + sql + "\n";
        for (String parameter : parameters) {
            msg += "," + parameter;
        }
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, msg, ex);
        throw new RuntimeException(msg, ex);
    }
    
    public Student retrieveStudent(String emailID, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        Student returnStudent = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();

            sql = "select smu_email_id, smu_email, tele_username, group_id, password, chat_id, veri_code, temp_smu_email_address,avatar_id from " + TBLNAME + " where smu_email_id = ? and password = SHA1(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, emailID);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String smu_email_id = rs.getString(1);
                String smu_email= rs.getString(2); 
                String tele_username= rs.getString(3); 
                String group_id = rs.getString(4); 
                String correctPassword = rs.getString(5);
    			int chat_id = rs.getInt(6); 
    			String veri_code = rs.getString(7); 
    			String temp_smu_email_address = rs.getString(8);
    			int avatar_id = rs.getInt(9);

              //  returnStudent = new Student(smu_email_id, tele_id, group_id,correctPassword);
                returnStudent = new Student(smu_email_id, smu_email, tele_username, group_id, correctPassword,	chat_id, veri_code, temp_smu_email_address,avatar_id);
            }
            //return resultUser;

        } catch (SQLException ex) {
            handleSQLException(ex, sql, "User={" + returnStudent + "}");
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return returnStudent;
    }
    
    public ArrayList<Student> retrieveStudentBySection(String group_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();

            sql = "select smu_email_id, smu_email, tele_username, group_id, password, chat_id, veri_code, temp_smu_email_address,avatar_id from " + TBLNAME + " where group_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, group_id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String smu_email_id = rs.getString(1);
                String smu_email= rs.getString(2); 
                String tele_username= rs.getString(3); 
                String correctPassword = rs.getString(5);
    			int chat_id = rs.getInt(6); 
    			String veri_code = rs.getString(7); 
    			String temp_smu_email_address = rs.getString(8);
    			int avatar_id = rs.getInt(9);

              //  returnStudent = new Student(smu_email_id, tele_id, group_id,correctPassword);
                students.add(new Student(smu_email_id, smu_email, tele_username, group_id, correctPassword,	chat_id, veri_code, temp_smu_email_address,avatar_id));
            }
            //return resultUser;

        } catch (SQLException ex) {
            handleSQLException(ex, sql, "students={" + students + "}");
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return students;
    }

}
