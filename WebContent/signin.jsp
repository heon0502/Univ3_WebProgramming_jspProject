<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<jsp:useBean id="user_infoDAO" scope="page" class="com.webServer.user_infoDAO"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./public/stylesheets/signin.css">

    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
	<form method="post">
		<div class="form">
        	<a href="#"><img class="prev" src="./public/images/exit1.png" alt="" onclick="back()"></a><br>
        	<a href="#"><img class="logo" src="./public/images/logo.PNG"></a><br>
        	<input class="id" type="text" placeholder="학번" id="num" name="num"><br>
	        <hr width="250px">
        	<input class="pw" type="password" placeholder="비밀번호" id="pw" name="pw"><br>
        	<hr width="250px">
        	<p align="left"><input type="checkbox" style="margin-left: 60px;"> ID 저장</p>
        	<input type="submit" class="bt" value="로그인"><br>
        	<a href="./signup.jsp"><button class="bt">회원가입</button></a><br>
	        <a href=""><div class="mg">비밀번호를 잊으셨나요?</div></a>
    	</div>
    </form>
</body>

<%
if(request.getParameter("num")!=null && request.getParameter("pw")!=null){
	if(user_infoDAO.signin(request.getParameter("num"), request.getParameter("pw"))){
		out.println("<script>alert('Success')</script>");
	}
	else{
		out.println("<script>alert('Fail')</script>");
	}
}
%>

<script type="text/javascript">

function back(){
    window.history.back();
}

</script>

</html>