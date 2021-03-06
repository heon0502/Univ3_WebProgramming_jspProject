<%@page import="com.webServer.DatabaseManager"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage = "error.jsp"%>
<%@ include file="import/header.jsp" %>
<%session.removeAttribute("info_check");%>
<%session.removeAttribute("check_info");%>
<jsp:useBean id="serviceCenterDAO" scope="page" class="com.webServer.serviceCenterDAO"/>
<jsp:useBean id="report_DO" scope="page" class="com.webServer.reportDO"/>
<jsp:setProperty name="report_DO" property="*"/>

<% 
	if((String)session.getAttribute("student_num") == null){
		out.print("<script>alert('로그인 후 이용할 수 있는 기능입니다.');</script>");
		out.print("<script>window.history.back()</script>");
	}
	else{
		
		String user_id = (String)session.getAttribute("student_num");
%>
<%
	//DatabaseManager dm = new DatabaseManager();
	//String a = dm.LoadTest();
	//JSONParser parser = new JSONParser();
	//Object o = parser.parse(a);
	//JSONArray ja = (JSONArray)o;
	//JSONObject element = (JSONObject)ja.get(1);
	//String b = (String)element.get("title");
	//out.print(b);
	if(request.getParameter("accused")!=null && 
		request.getParameter("title")!=null &&
		request.getParameter("content")!=null){
		
		if(user_infoDAO.isChk_user(request.getParameter("accused")) == 1){
			out.println("<script>alert('등록되었습니다.')</script>");
			serviceCenterDAO.inputReportToDB(user_id,request.getParameter("accused"), request.getParameter("title"), request.getParameter("content"));
		}
		else{
			out.println("<script>alert('해당 ID를 가진 사용자가 없습니다.')</script>");
		}

	}
%>
<link href="public/stylesheets/serviceCenter.css" rel="stylesheet"/>
    <section class="SCcontent">
        <div class="imgarea">
            <img src="public/images/mainimg1.jpg">
            <div class="img_text">
                <h1>신고하기</h1>
                고객님의 크고 작은 목소리를 들려주세요. <br> 
                항상 고객님께서 최선의 서비스를 받으실 수 있도록 도와드리겠습니다.
            </div>
        </div>
        <form method="post">
        <table class="content">
            <tbody>
                <tr>
                    <%@ include file="import/navi.jsp" %>
                    <td id="main_content">
                        <div>
                            <h1>신 고 하 기</h1>
                            <table>
                                <tbody>
                                	<tr>
                                       <th>신고자 ID<br><small>(신고하는 자)</small></th>
                                       <th><%= user_id %></th> 
                                       <th>피신고자 ID<br><small>(신고받는 자)</small></th>
                                       <th>
                                      		<input type="text" name="accused" style="width:80%" placeholder="신고하려는 사람의 ID를 작성"/>
                                       </th>
                                    </tr>
                                    <tr>
                                        <th>제 목</th>
                                        <td colspan="3">
                                        	<input type="text" name="title" style="width:90%" placeholder="제목을 작성해주세요"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>내 용</th>
                                        <td colspan=3>
                                        	<textarea cols="30" name="content" rows="15" style="width: 90%; resize:none;" placeholder="내용을 작성해주세요" ></textarea> 
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
    
    <script>
    </script>
    <%
		}
    %>
    <%@ include file="import/footer.jsp" %>