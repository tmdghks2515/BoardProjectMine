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
	#reg{
		width: 400px;
		margin: 0 auto;
	}
	#reg input{
		margin-bottom:20px;
	}
</style>
</head>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<div id="container">
		<jsp:include page="/template/search.jsp"></jsp:include>
		<div id="reg">
			<form id="frm" action="updateAction.do" method="post" class="form">
				<small>아이디</small>
				<input type="text" name="id" placeholder="아이디" class="form-control" value="${requestScope.vo.id }" readonly>
				<small>비밀번호</small>
				<input type="password" name="pass" placeholder="비밀번호" class="form-control" value="${requestScope.vo.pass }">
				<small>이름</small>
				<input type="text" name="name" placeholder="이름" class="form-control" value="${requestScope.vo.name }">
				<small>나이</small>
				<input type="text" name="age" placeholder="나이" class="form-control" value="${requestScope.vo.age }">
				<button class="btn btn-primary">수정</button>
			</form>
		</div>
	</div>
</body>
</html>