package com.webServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;

public class freeBoardDAO {
	DatabaseManager dm = new DatabaseManager();
	
	public String getAllResult() throws SQLException {
		ResultSet rs = dm.dbLoad("SELECT * FROM board ORDER BY Num DESC", null, "select");
		JSONArray result = new JSONArray();
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("Num", rs.getString("Num"));
			obj.put("Writer", rs.getString("Writer"));
			obj.put("Title", rs.getString("Title"));
			result.add(obj);
		}
		return result.toString();
	}
	
	public boolean change_content(String boardNum, String Title, String Content) throws SQLException{
		
		JSONObject parameters = new JSONObject();

        parameters.put("1", Title);
        parameters.put("2", Content);
        parameters.put("3", boardNum);
        
        ResultSet rs = dm.dbLoad("UPDATE board Set Title=?, Content=?, Date=now() WHERE Num=?", parameters, "insert");
		
		return true;
	}
	
	public JSONObject get_content(String board_Num) throws SQLException{
		JSONObject parameters = new JSONObject();
		String get_writer = null;
		String get_title = null;
		String get_content = null;
		parameters.put("1", board_Num);
		ResultSet rs = dm.dbLoad("SELECT * FROM board WHERE Num=?", parameters, "select");
		while(rs.next()) {
			get_writer = rs.getString("Writer");
			get_title = rs.getString("Title");
			get_content = rs.getString("Content");
		}
		
		JSONObject result = new JSONObject();
		result.put("1", get_writer);
		result.put("2", get_title);
		result.put("3", get_content);
		
		return result;
	}
	
	public boolean deleteToDB(String Num) throws SQLException{
		JSONObject parameters = new JSONObject();
		
		parameters.put("1", Num);
		
		ResultSet rs = dm.dbLoad("DELETE FROM comment WHERE board_num=?", parameters, "insert");
		rs = dm.dbLoad("DELETE FROM board WHERE Num=?", parameters, "insert");
		
		return true;
	}
	
	public boolean inputboardToDB(String Writer, String Title, String Content) throws SQLException {
        JSONObject parameters = new JSONObject();
        parameters.put("1", Writer);
        parameters.put("2", Title);
        parameters.put("3", Content);
//        System.out.println("hello holy " + accused_id);
		ResultSet rs = dm.dbLoad("INSERT INTO board(Writer,Title,Content,Date) values(?,?,?,now())", parameters, "insert");
//		JSONArray result = new JSONArray();
//		while(rs.next()) {
//			JSONObject obj = new JSONObject();
//			obj.put("nid", rs.getString("nid"));
//			obj.put("title", rs.getString("title"));
//			obj.put("writer", rs.getString("writer"));
//			result.add(obj);
//		}
//		return result.toString();
		return true;
	}
	public String getOneFreeResults(String Num) throws SQLException {
        JSONObject parameters = new JSONObject();

        parameters.put("1", Num);
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:MM");
		ResultSet rs = dm.dbLoad("SELECT * FROM board WHERE Num=?", parameters, "select");
        
		JSONArray result = new JSONArray();
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("Num", rs.getString("Num"));
			obj.put("Title", rs.getString("Title"));
			obj.put("Writer", rs.getString("Writer"));
			obj.put("Content", rs.getString("Content"));
			obj.put("Date", fm.format(rs.getObject("Date")));
			
			result.add(obj);
		}
		return result.toString();
}
/////////////////////////////////commentDAO/////////////////////////////////////////////////////////////////////////////
	
	public String getCommentResult() throws SQLException {
		ResultSet rs = dm.dbLoad("SELECT * FROM comment", null, "select");
		JSONArray result2 = new JSONArray();
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("cid", rs.getString("cid"));
			obj.put("C_Writer", rs.getString("C_Writer"));
			obj.put("Comment", rs.getString("Comment"));
			result2.add(obj);
		}
		return result2.toString();
	}
	public String getOneCommentResults(String Num) throws SQLException {
        JSONObject parameters = new JSONObject();

        parameters.put("1", Num);
		ResultSet rs = dm.dbLoad("SELECT * FROM comment WHERE board_num=?", parameters, "select");
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm");

		JSONArray result2 = new JSONArray();
		while(rs.next()) {
			System.out.println( fm.format(rs.getObject("C_DATE")));
			JSONObject obj = new JSONObject();
			obj.put("cid", rs.getString("cid"));
			obj.put("C_Writer", rs.getString("C_Writer"));
			obj.put("Comment", rs.getString("Comment"));
			obj.put("C_Date", fm.format(rs.getObject("C_Date")));
			result2.add(obj);
		}
		return result2.toString();
}
	public boolean inputcommentToDB(String C_Writer,String bn, String Comment) throws SQLException {
        JSONObject parameters = new JSONObject();
        parameters.put("1", C_Writer);
        parameters.put("2", bn);
        parameters.put("3", Comment);
        
		ResultSet rs = dm.dbLoad("INSERT INTO comment(C_Writer, board_num, Comment, C_Date) values(?,?, ?, now())", parameters, "insert");
		

		return true;
		
	}
}
