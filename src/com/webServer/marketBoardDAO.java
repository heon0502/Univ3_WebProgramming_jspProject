package com.webServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.SimpleDateFormat;

public class marketBoardDAO {
	DatabaseManager dm = new DatabaseManager();
	
	@SuppressWarnings("unchecked")
	public String getAllResult() throws SQLException {
		ResultSet rs = dm.dbLoad("SELECT * FROM market ORDER BY mid DESC", null, "select");
		JSONArray result = new JSONArray();
		SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd");
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("mid", rs.getString("mid"));
			obj.put("Writer", rs.getString("Writer"));
			obj.put("Title", rs.getString("Title"));
			obj.put("Content", rs.getString("Content"));
			obj.put("Price", rs.getString("Price"));
			obj.put("Date", rs.getString("Date"));
			obj.put("img_src", rs.getString("img_src"));
			obj.put("Date2", fm.format(rs.getObject("Date")));

			result.add(obj);
		}
		return result.toString();
	}

public boolean change_content(String boardNum, String Title, String Content, String price, String img_src) throws SQLException{
		
		JSONObject parameters = new JSONObject();
        
        parameters.put("1", Title);
        parameters.put("2", Content);
        parameters.put("3", price);
        parameters.put("4", img_src);
        parameters.put("5", boardNum);
        
        ResultSet rs = dm.dbLoad("UPDATE market Set Title=?, Content=?, Price=?, Date=now(), img_src=? WHERE mid=?", parameters, "insert");
		
		return true;
	}
	
	public JSONObject get_content(String board_Num) throws SQLException{
		JSONObject parameters = new JSONObject();
		String get_writer = null;
		String get_title = null;
		String get_content = null;
		String get_price = null;
		parameters.put("1", board_Num);
		ResultSet rs = dm.dbLoad("SELECT * FROM market WHERE mid=?", parameters, "select");
		while(rs.next()) {
			get_writer = rs.getString("Writer");
			get_title = rs.getString("Title");
			get_content = rs.getString("Content");
			get_price = rs.getString("Price");
		}
		
		JSONObject result = new JSONObject();
		result.put("1", get_writer);
		result.put("2", get_title);
		result.put("3", get_content);
		result.put("4", get_price);
		
		return result;
	}
	
	public boolean deleteToDB(String Num) throws SQLException{
		JSONObject parameters = new JSONObject();
		
		parameters.put("1", Num);
		
		ResultSet rs = dm.dbLoad("DELETE FROM marketcomment WHERE m_board_num=?", parameters, "insert");
		rs = dm.dbLoad("DELETE FROM market WHERE mid=?", parameters, "insert");
		
		return true;
	}
	
public boolean inputmarketToDB(String Writer, String Title, String Content, String Price, String img_src) throws SQLException {
    JSONObject parameters = new JSONObject();
    parameters.put("1", Writer);
    parameters.put("2", Title);
    parameters.put("3", Content);
    parameters.put("4", Price);
    parameters.put("5", img_src);
    System.out.println(Writer);
    System.out.println(Title);
    System.out.println(Content);
    System.out.println(Price);
    System.out.println(img_src);
//    System.out.println("hello holy " + accused_id);
	ResultSet rs = dm.dbLoad("INSERT INTO market(Writer,Title,Content,Price,Date,img_src) values(?,?,?,?,now(),?)", parameters, "insert");
//	JSONArray result = new JSONArray();
//	while(rs.next()) {
//		JSONObject obj = new JSONObject();
//		obj.put("nid", rs.getString("nid"));
//		obj.put("title", rs.getString("title"));
//		obj.put("writer", rs.getString("writer"));
//		result.add(obj);
//	}
//	return result.toString();
		return true;
	}
public String getOneMarketResults(String mid) throws SQLException {
    JSONObject parameters = new JSONObject();

    parameters.put("1", mid);
	SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:MM");
	ResultSet rs = dm.dbLoad("SELECT * FROM market WHERE mid=?", parameters, "select");
    
	JSONArray result = new JSONArray();
	while(rs.next()) {
		JSONObject obj = new JSONObject();
		obj.put("mid", rs.getString("mid"));
		obj.put("Writer", rs.getString("Writer"));
		obj.put("Title", rs.getString("Title"));
		obj.put("Content", rs.getString("Content"));
		obj.put("Price", rs.getString("Price"));
		obj.put("Date", fm.format(rs.getObject("Date")));
		obj.put("img_src", rs.getString("img_src"));

		
		result.add(obj);
	}
	return result.toString();
}
//////////////////////////////////////////////////////////////////////////////////////////////////
public String getCommentResult() throws SQLException {
	ResultSet rs = dm.dbLoad("SELECT * FROM marketcomment", null, "select");
	JSONArray result2 = new JSONArray();
	while(rs.next()) {
		JSONObject obj = new JSONObject();
		obj.put("m_cid", rs.getString("m_cid"));
		obj.put("M_Writer", rs.getString("M_Writer"));
		obj.put("M_Comment", rs.getString("M_Comment"));
		result2.add(obj);
	}
	return result2.toString();
}
public String getOneCommentResults(String mid) throws SQLException {
    JSONObject parameters = new JSONObject();

    parameters.put("1", mid);
	ResultSet rs = dm.dbLoad("SELECT * FROM marketcomment WHERE m_board_num=?", parameters, "select");
	SimpleDateFormat fm = new SimpleDateFormat("yy/MM/dd hh:mm");

	JSONArray result2 = new JSONArray();
	while(rs.next()) {
		System.out.println( fm.format(rs.getObject("M_Date")));
		JSONObject obj = new JSONObject();
		obj.put("m_cid", rs.getString("m_cid"));
		obj.put("M_Writer", rs.getString("M_Writer"));
		obj.put("M_Comment", rs.getString("M_Comment"));
		obj.put("M_Date", fm.format(rs.getObject("M_Date")));
		result2.add(obj);
	}
	return result2.toString();
}
public boolean inputcommentToDB(String M_Writer,String bn, String M_Comment) throws SQLException {
    JSONObject parameters = new JSONObject();
    parameters.put("1", M_Writer);
    parameters.put("2", bn);
    parameters.put("3", M_Comment);
    
	ResultSet rs = dm.dbLoad("INSERT INTO marketcomment(M_Writer, m_board_num, M_Comment, M_Date) values(?,?, ?, now())", parameters, "insert");
	

	return true;
	
}

	public String getSearchData(String search) throws SQLException {
		ResultSet rs = dm.dbLoad("SELECT * FROM market WHERE (Title LIKE '%"+search+"%' OR Content LIKE '%"+search+"%' or Writer LIKE '%"+search+"%');", null, "select");
		JSONArray result = new JSONArray();
		while(rs.next()) {
			JSONObject obj = new JSONObject();
			obj.put("mid", rs.getString("mid"));
			obj.put("Writer", rs.getString("Writer"));
			obj.put("Title", rs.getString("Title"));
			obj.put("Content", rs.getString("Content"));
			obj.put("Price", rs.getString("Price"));
			obj.put("Date", rs.getString("Date"));
			obj.put("img_src", rs.getString("img_src"));
			result.add(obj);
		}
		return result.toString();
	}
}
