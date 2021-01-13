<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		width:1200px;
		margin:0 auto;
	}
	textarea{
		resize:none;
	}
	form p{
		text-align: center;
		padding:10px;
	}
	/*문의목록******************************************/
	#qna_list p:first-child{
		
		background-color: #007fff;
		color:white;
	}
	#qna_list span{
		width:16.6%;
		display: block;
		float:left;
		text-align: center;
		padding:8px 0;
	}
	#qna_list p{
		clear:both;
		height:35.4px;
		margin:0;
		border-bottom: 1px solid gray;
	}
	.slide{
		display: none;
		height:150px;
		padding:50px;
		background-color: #eeeeee;
	}
	#qna_list #view_more{
		text-align: center;
		border:none;
	}
	#view_more small{
		padding:10px;
	}
</style>
<script>
	$(function(){
		$("#qna_list a").click(function(e){
			$(this).parents("p").next().slideToggle(300);
			e.preventDefault();
		})
		
		$("#view_more a").click(function(){
			$.ajax({
				url:"view_more.do",
				method:'get',
				success:function(d){
					location.reload();
				}
			})
		})
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
			<textarea id="content" name="content"  class="form-control" rows="16"></textarea>
			<p><button class="btn btn-info">작성완료</button></p>
		</form>
		<hr>
		<div id="qna_list">
			<p>
				<span>문의번호</span>
				<span>상태</span>
				<span>제목</span>
				<span>작성자</span>
				<span>날짜</span>
				<span>문의답장</span>
			</p>
			<c:forEach var="qdto" items="${sessionScope.li }">
				<p>
					<span><c:out value="${qdto.qNo }"></c:out></span>
					<span><c:out value="${qdto.status }"></c:out></span>
					<span><c:out value="${qdto.title }"></c:out></span>
					<span><c:out value="${qdto.writer }"></c:out></span>
					<span><c:out value="${qdto.wDate }"></c:out></span>
					<span><a href="#">보기</a></span>
				</p>
				<div class="slide">
					<c:out value="${qdto.content }"></c:out>
				</div>
			</c:forEach>
			<p id="view_more">
				<a href="#">
					<small class="glyphicon glyphicon-chevron-down">펼치기</small>
				</a>
			</p>
		</div>
	</div>
</div>
</body>
</html>