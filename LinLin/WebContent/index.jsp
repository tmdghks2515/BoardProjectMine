<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<style>
#container{
	width:100%;
	position:absolute;
}
</style>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div id="container">
		<jsp:include page="template/search.jsp"></jsp:include>
	</div>
</body>
</html>