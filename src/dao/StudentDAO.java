package dao;

import entity.*;
import java.sql.*;
import java.util.logging.*;



public class StudentDAO {
	//for debugging purpose
	
/*	public static void main(String[] args){
		System.out.println("ssss");
		StudentDAO sd = new StudentDAO();
		Student s = sd.retrieveStudentViaAvatarID(1);//("andy.aw.2014","password");
		System.out.println("email: "+s.getSmu_email());
		System.out.println("emailID: "+s.getSmu_email_id());
		
		System.out.println("avatarID "+ s.getAvatar_id());
		System.out.println("teleUserName "+ s.getTele_username());
		System.out.println("grpID "+s.getGroup_id());
		System.out.println("pwd "+ s.getPassword());
		System.out.println("chatID "+ s.getChat_id());
		System.out.println("veriCODE and tempMailAdd "+s.getVeri_code() +" "+s.getTemp_smu_email_address());
		
		
	}*/
	
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

            sql = "select * from " + TBLNAME + " where smu_email_id = ? and password = SHA1(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, emailID);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	String smu_email= rs.getString(1);
            	String smu_email_id = rs.getString(2);
                int avatar_id = rs.getInt(3);
                String tele_username = rs.getString(4); 
                String group_id = rs.getString(5);
                String correctPassword = rs.getString(6);
                int chat_id = rs.getInt(7);
                String veri_code =rs.getString(8);
                String temp_smu_email_address = rs.getString(9);                
                
      

              //  returnStudent = new Student(smu_email_id, tele_id, group_id,correctPassword);
                returnStudent = new Student(smu_email, smu_email_id, avatar_id, tele_username, group_id,
            			correctPassword, chat_id, veri_code, temp_smu_email_address);
            }
            //return resultUser;

        } catch (SQLException ex) {
            handleSQLException(ex, sql, "User={" + returnStudent + "}");
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return returnStudent;
    }
    
    public Student retrieveStudentViaAvatarID(int avatarID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        Student returnStudent = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();

            sql = "select * from " + TBLNAME + " where avatar_id = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, avatarID);
         

            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	String smu_email= rs.getString(1);
            	String smu_email_id = rs.getString(2);
                int avatar_id = rs.getInt(3);
                String tele_username = rs.getString(4); 
                String group_id = rs.getString(5);
                String correctPassword = rs.getString(6);
                int chat_id = rs.getInt(7);
                String veri_code =rs.getString(8);
                String temp_smu_email_address = rs.getString(9);                
                
      

              //  returnStudent = new Student(smu_email_id, tele_id, group_id,correctPassword);
                returnStudent = new Student(smu_email, smu_email_id, avatar_id, tele_username, group_id,
            			correctPassword, chat_id, veri_code, temp_smu_email_address);
            }
            //return resultUser;

        } catch (SQLException ex) {
            handleSQLException(ex, sql, "User={" + returnStudent + "}");
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return returnStudent;
    }


}
