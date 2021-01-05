<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#container{
		position:absolute;
		width:100%;
	}
	#container>h1{
		text-align: center;
		font-family: 'Black Han Sans', 'sans-serif';
		margin:30px 0;
		color:#007fff;
	}
	h1>a:link, h1>a:visited{
		font-weight: bold;
		text-decoration:none;
		color:#007fff;
	}
	.container{
		border:1px solid gray;
		box-sizing: border-box;
		padding:5px;
	}
	.row{
		padding:10px;
	}
	.row input{
	}
	
</style>
</head>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<div id="container">
		<h1><a href="<%=request.getContextPath() %>/index.jsp">Hiver</a> 게시판</h1>
		<div class="container">
			<form action="">
				<div class="row">
					<div class="col-xs-1">작성자: </div>
					<div class="col-xs-5"><%=session.getAttribute("id") %></div>
				</div>
				<div class="row">
					<div class="col-xs-1">글제목: </div>
					<div class="col-xs-5"><input type="text" name=""></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>