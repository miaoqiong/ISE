package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentSummaryDAO {
	private static final String TBLNAME = "post_class_summary_answer";
	
//  public static void main(String[] args){
//		System.out.println("ssss");
//		StudentSummaryDAO sd = new StudentSummaryDAO();
//		HashMap<String,String> summarys = sd.retriveStudentSummary("jiaqi.yuan.2015",2);
//		for(String question:summarys.keySet()){
//			System.out.println(question+"--"+summarys.get(question));
//		}
//	}
	
	  private void handleSQLException(SQLException ex, String sql, String... parameters) {
	        String msg = "Unable to access data; SQL=" + sql + "\n";
	        for (String parameter : parameters) {
	            msg += "," + parameter;
	        }
	        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, msg, ex);
	        throw new RuntimeException(msg, ex);
	    }
	  
	  public HashMap<String,String> retriveStudentSummary (String smu_email_id){
		    Connection conn = null;
	        PreparedStatement stmt = null;
	        String sql = "";
	        HashMap<String,String> studentSummary = new HashMap<>();
	        ResultSet rs = null;

	        try {
	            conn = ConnectionManager.getConnection();

	            sql = "select question_hist, answer from " + TBLNAME + " where smu_email_id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, smu_email_id);
	          

	            rs = stmt.executeQuery();

	            while (rs.next()) {
	                String question_hist = rs.getString(1);
	                String answer = rs.getString(2);	                
	                studentSummary.put(question_hist, answer);
	            }

	        } catch (SQLException ex) {
	            handleSQLException(ex, sql, "Student Summary={" + studentSummary + "}");
	        } finally {
	            ConnectionManager.close(conn, stmt, rs);
	        }
	        return studentSummary;
	  }
	  public HashMap<String,String> retriveStudentSummary (String smu_email_id, int week){
		    Connection conn = null;
	        PreparedStatement stmt = null;
	        String sql = "";
	        HashMap<String,String> studentSummary = new HashMap<>();
	        ResultSet rs = null;

	        try {
	            conn = ConnectionManager.getConnection();

	            sql = "select question_hist, answer from " + TBLNAME + " where smu_email_id = ? and week = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, smu_email_id);
	            stmt.setInt(2, week);

	            rs = stmt.executeQuery();

	            while (rs.next()) {
	                String question_hist = rs.getString(1);
	                String answer = rs.getString(2);	                
	                studentSummary.put(question_hist, answer);
	            }

	        } catch (SQLException ex) {
	            handleSQLException(ex, sql, "Student Summary={" + studentSummary + "}");
	        } finally {
	            ConnectionManager.close(conn, stmt, rs);
	        }
	        return studentSummary;
	  }
}
