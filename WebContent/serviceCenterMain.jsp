<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage = "error.jsp"%>
<%@ include file="import/header.jsp" %>
<%session.removeAttribute("info_check");%>
<%session.removeAttribute("check_info");%>
<link href="public/stylesheets/serviceCenter.css" rel="stylesheet"/>

<section class="SCcontent">
    <div class="imgarea">
        <img src="public/images/mainimg1.jpg">
        <div class="img_text">
            <h1>고객센터</h1>
            고객님의 크고 작은 목소리를 들려주세요. <br> 
            작은 소리는 무시하겠습니다.(?)
        </div>
    </div>
    <table class="content">
        <tbody>
            <tr>
				<%@ include file="import/navi.jsp" %>
                <td id="main_content">
                    <div>
                        <h1>고객센터</h1>
                        이곳에 설명을 쓸 것입니다.<br><br><br><br><br><br><br><br><br><br>
                        어떤 글귀를 쓸까요<br><br><br><br><br><br><br><br><br><br>
                        hello<br><br><br><br><br><br><br><br><br><br>
                        hello<br><br><br><br><br><br><br><br><br><br>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</section>
    