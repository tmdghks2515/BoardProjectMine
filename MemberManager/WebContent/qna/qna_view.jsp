<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	#container{
		position:absolute;
		width:100%;
		margin-top:100px;
	}
	form{
		width:700px;
		margin:0 auto;
	}
	textarea{
		resize:none;
	}
	form p{
		text-align: center;
		padding:10px;
	}
</style>
<script>
	$(function(){
		$("#qna_list").text(${sessionScope.li[0]});
	})
</script>
</head>
<body>
<jsp:include page="${request.contextPath }/template/header.jsp"></jsp:include>
<jsp:include page="${request.contextPath }/template/ask_h.jsp"></jsp:include>
	<div id="container">
	<div class="container">
		<form action="ask.do" class="form-group">
			<label for="title"><small>제목</small></label>
			<input type="text" class="form-control" name="title" id="title">
			<label for="content"><small>문의내용</small></label>
			<textarea id="content" name="content"  class="form-control" rows="13"></textarea>
			<p><button class="btn btn-info">작성완료</button></p>
		</form>
		<hr>
		<div id="qna_list">
			
		</div>
	</div>
</div>
</body>
</html>