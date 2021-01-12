<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
#container{
	width:100%;
	position:absolute;
}
#container h1{
	text-align: center;
	font-weight: bold;
	font-family: 'Black Han Sans', 'sans-serif';
	color:#0080ff;
}
h1 a:link, h1 a:visited{
	text-decoration: none;
	color:#0080ff;
}
#frm{
	width:400px;
	margin:50px auto;
}
#frm input,#frm button{
	width:100%;
	margin-top: 15px;
	height:40px;
	line-height: 40px;
	border:1px solid #e8e8e8;
	outline: none;
}
#frm input{
	padding:10px;
}
#frm button{
	background-color: #a3daff;
	color:white;
}
#frm button:hover{
	box-shadow: 0 0 3px black;
}
</style>
</head>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<div id="container">
		<h1><a href="../index.jsp">HIVER</a> 로그인</h1>
		<form id="frm" action="../LoginServlet" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="password" name="pass" placeholder="비밀번호"><br>
			<button>로그인</button><br>
			<button type="button" onclick="location='register.jsp'">회원가입</button>
		</form>
	</div>
</body>
</html>