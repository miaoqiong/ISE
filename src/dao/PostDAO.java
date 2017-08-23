package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jmx.snmp.Timestamp;

import entity.*;


public class PostDAO {
	//for debugging purpose
	
		public static void main(String[] args){
		/*	System.out.println("ssss");
			PostDAO pd = new PostDAO();
			HashMap<Integer, Post> map = pd.retrieveAll();
		    //System.out.println(map.size());
			for (Integer key : map.keySet()) {
			    System.out.println(key + " " + map.get(key) + " " +map.get(key).getAvatar_id() +"   "+ map.get(key).getPost_id());
			}
			
			HashMap<Integer,Post> aMap = pd.retrieveAPost(4);
			System.out.println("*****************************");
			for (Integer key : aMap.keySet()) {
			    System.out.println(key + " " + aMap.get(key) + " " +aMap.get(key).getAvatar_id() +"   "+ aMap.get(key).getParent_id());
			}

	*/
		}
	
		private static final String TBLNAME = "post";
		    
		
		
	    private void handleSQLException(SQLException ex, String sql, String... parameters) {
	        String msg = "Unable to access data; SQL=" + sql + "\n";
	        for (String parameter : parameters) {
	            msg += "," + parameter;
	        }
	        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, msg, ex);
	        throw new RuntimeException(msg, ex);
	    }
	    
	    
	    public HashMap<Integer, Post> retrieveAll() {
	        HashMap<Integer, Post> postMap = new HashMap<>();

	        Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement preStmt = null;
	        Post tempPost = null;
	        try {
	            conn = ConnectionManager.getConnection();
	            String sql = "select * from " + TBLNAME + " where parent_id = post_id";
	            preStmt = conn.prepareStatement(sql);
	            rs = preStmt.executeQuery();

	            while (rs.next()) {
	            	int avatar_id = rs.getInt(1);
	            	int parent_id = rs.getInt(2);
	            	int level = rs.getInt(3);
	            	int post_id = rs.getInt(4);
	            	String post_title = rs.getString(5);
	            	String post_content = rs.getString(6);
	            	boolean is_question = rs.getBoolean(7);
	            	boolean is_bot = rs.getBoolean(8);
	            	boolean is_qa_bountiful = rs.getBoolean(9);
	            	String timestamp = rs.getString(10);
	            	int time_limit_qa = rs.getInt(11);
	            	int time_limit_bot = rs.getInt(12);
	            	float qa_coin_basic = rs.getFloat(13);
	            	float qa_coin_bounty = rs.getFloat(14);
	            	float thoughfulness_score = rs.getFloat(15);
	            	boolean no_show = rs.getBoolean(16);
	            	int previous_version = rs.getInt(17);
	            	int number_of_upvotes = rs.getInt(18);
	            	int number_of_downvotes = rs.getInt(19);

	             
	            	tempPost = new Post(avatar_id, parent_id, level, post_id, post_title, post_content, is_question, is_bot, is_qa_bountiful, timestamp, time_limit_qa, time_limit_bot, qa_coin_basic, qa_coin_bounty, thoughfulness_score,
	            		 no_show, previous_version, number_of_upvotes, number_of_downvotes);
	            	
	            	System.out.println(tempPost);	       
	                postMap.put(parent_id, tempPost);
	                System.out.println(postMap.size());
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionManager.close(conn, preStmt, rs);
	            return postMap;
	        }
	    }
	    
	    public HashMap<Integer, Post> retrieveAPost(int parentID) {
	        HashMap<Integer, Post> postMap = new HashMap<>();

	        Connection conn = null;
	        ResultSet rs = null;
	        PreparedStatement preStmt = null;
	        Post tempPost = null;
	        try {
	            conn = ConnectionManager.getConnection();
	            String sql = "select * from " + TBLNAME + " where parent_id = ?";
	            preStmt = conn.prepareStatement(sql);
	            preStmt.setInt(1, parentID);
	            rs = preStmt.executeQuery();

	            while (rs.next()) {
	            	int avatar_id = rs.getInt(1);
	            	int parent_id = rs.getInt(2);
	            	int level = rs.getInt(3);
	            	int post_id = rs.getInt(4);
	            	String post_title = rs.getString(5);
	            	String post_content = rs.getString(6);
	            	boolean is_question = rs.getBoolean(7);
	            	boolean is_bot = rs.getBoolean(8);
	            	boolean is_qa_bountiful = rs.getBoolean(9);
	            	String timestamp = rs.getString(10);
	            	int time_limit_qa = rs.getInt(11);
	            	int time_limit_bot = rs.getInt(12);
	            	float qa_coin_basic = rs.getFloat(13);
	            	float qa_coin_bounty = rs.getFloat(14);
	            	float thoughfulness_score = rs.getFloat(15);
	            	boolean no_show = rs.getBoolean(16);
	            	int previous_version = rs.getInt(17);
	            	int number_of_upvotes = rs.getInt(18);
	            	int number_of_downvotes = rs.getInt(19);

	             
	            	tempPost = new Post(avatar_id, parent_id, level, post_id, post_title, post_content, is_question, is_bot, is_qa_bountiful, timestamp, time_limit_qa, time_limit_bot, qa_coin_basic, qa_coin_bounty, thoughfulness_score,
	            		 no_show, previous_version, number_of_upvotes, number_of_downvotes);
	                  
	                postMap.put(post_id, tempPost);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            ConnectionManager.close(conn, preStmt, rs);
	            return postMap;
	        }
	    }
	    
	    public int lastPostID(){
	        Connection conn = null;
	          PreparedStatement stmt = null;
	          String sql = "";
	          Integer returnPostID = null;
	          ResultSet rs = null;

	          try {
	              conn = ConnectionManager.getConnection();

	              sql = "select MAX(post_id) FROM " + TBLNAME;
	              stmt = conn.prepareStatement(sql);           
	            
	              
	              rs = stmt.executeQuery();

	              while (rs.next()) {              
	                returnPostID = rs.getInt(1);
	               
	                
	              }
	              //return resultUser;

	          } catch (SQLException ex) {
	              handleSQLException(ex, sql, "PostID={" + returnPostID + "}");
	          } finally {
	              ConnectionManager.close(conn, stmt, rs);
	          }
	          return returnPostID;
	      }
	    
	   public void addNewPost(int avatar_id, String post_title, String post_content){
	   
	    	Connection conn = null;
		    PreparedStatement stmt = null;
		    String sql = "";
		    int parent_id = lastPostID()+1;
		    int post_id = parent_id;
		    
		    try {
		    	conn = ConnectionManager.getConnection();
		    	sql = "insert into post values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        stmt = conn.prepareStatement(sql);
		       
		        stmt.setInt(1,avatar_id);
		        stmt.setInt(2,parent_id);
		        stmt.setInt(3,0); //level 
		        stmt.setInt(4,post_id); 
		        stmt.setString(5,post_title); 
		        stmt.setString(6,post_content); 
		        stmt.setInt(7,0); //is_question
		        stmt.setInt(8,0); //is_bot
		        stmt.setInt(9,0); //is_qa_bountiful
		        stmt.setTimestamp(10,getCurrentTimeStamp()); 
		        stmt.setInt(11,0); // time_limit_qa
		        stmt.setInt(12,0); // time_limit_bot
		        stmt.setFloat(13,0);//qa_coin_basic 
		        stmt.setFloat(14,0);//qa_coin_bounty 
		        stmt.setFloat(15,0);//thoughfulness_score 
		        stmt.setInt(16,0); 
		        stmt.setInt(17,parent_id); //previous_version
		        stmt.setInt(18,0); // num_of_upvotes
		        stmt.setInt(19,0); //num_of_upvotes
		        
		        stmt.executeUpdate();

	        } catch (SQLException ex) {
	        	 String msg = "An exception occurs when adding new post";
		         handleSQLException(ex, sql, "msg={" + msg + "}");
		    } finally {
		            ConnectionManager.close(conn, stmt);
		    }
	   }

	   private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	  }	
	    

}
