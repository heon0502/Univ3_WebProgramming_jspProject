<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.webServer.DatabaseManager"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<jsp:useBean id="freeBoard_DAO" scope="page" class="com.webServer.freeBoardDAO"/>
<%@include file="import/header.jsp" %>

<%
	if(	request.getParameter("Writer")!=null &&
		request.getParameter("Title")!=null && 
		request.getParameter("Content")!=null){
		
		out.println("<script>alert('등록되었습니다.'); location.href='freeboard.jsp?pnum=1'</script>");
		freeBoard_DAO.inputboardToDB(request.getParameter("Writer"),request.getParameter("Title"), request.getParameter("Content"), "File");

	}
%>

<link href="public/stylesheets/serviceCenter.css" rel="stylesheet"/>
    <section class="SCcontent">
        <div class="imgarea">
            <img src="public/images/mainimg1.jpg">
            <div class="img_text">
                <h1>게시글 작성</h1>s
                	자유로운 게시글을 작성해주세요
            </div>
        </div>
        <form method="post">
        <table class="content">
            <tbody>
                <tr>
                    <td id="main_content">
                        <div>                           
                            <table>
                                <tbody>
                                	<tr>
                                		<h1>자  유  게  시  글  작  성</h1>
                                	</tr>
                                	<tr>
                                       <th>작성자 ID</th>
                                       <td colspan="3">
                                            <input type="text" name="Writer" style="width:90%" placeholder="본인의 ID를 작성해주세요"/>
                                       </td>
                                    </tr>
                                    <tr>
                                        <th>제 목</th>
                                        <td colspan="3">
                                        	<input type="text" name="Title" style="width:90%" placeholder="제목을 작성해주세요"/>
                                        </td>
                                    </tr>	
                                    <tr>
                                        <th>내 용</th>
                                        <td colspan=3>
                                        	<textarea cols="30" name="Content" rows="25" style="width: 90%; resize:none;" placeholder="내용을 작성해주세요" ></textarea> 
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="bottom_btn" align="right" style="margin-right:10vw">
                            	<input id="submitBtn" type="submit" class="bt" value="등록"/>
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
    </section>
    
  