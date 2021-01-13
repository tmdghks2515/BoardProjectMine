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
	/*문의목록******************************************/
	table{
		width:100%;
		border-collapse: collapse;
		border:1px solid gray;
	}
	tr td{
		padding:10px 0;
		text-align: center;
		width:16.66%;
		border-bottom:1px solid gray;
	}
	#con_and_re{
		height:150px;
		display: none;
	}
	#con_and_re td{
		border:1px solid gray;
	}
</style>
<script>
	$(function(){
		$("table a").click(function(){
			$(this).parents("tr").next().slideToggle();
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
			<textarea id="content" name="content"  class="form-control" rows="13"></textarea>
			<p><button class="btn btn-info">작성완료</button></p>
		</form>
		<hr>
		<div id="qna_list">
			<table>
				<tr>
					<td>문의번호</td>
					<td>상태</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>문의답장</td>
				</tr>
				<c:forEach var="qdto" items="${sessionScope.li }">
					<tr>
						<td><c:out value="${qdto.qNo }"/></td>
						<c:choose>
							<c:when test="${qdto.status == 0 }">
								<td>안읽음</td>
							</c:when>
							<c:when test="${qdto.status == 1 }">
								<td>읽음</td>
							</c:when>
							<c:otherwise>
								<td>답장완료</td>
							</c:otherwise>
						</c:choose>
						<td><c:out value="${qdto.title }"/></td>
						<td><c:out value="${qdto.writer }"/></td>
						<td><c:out value="${qdto.wDate }"/></td>
						<td><a href="#">보기</a></td>
					</tr>
					<tr id="con_and_re">
						<td colspan="3"><c:out value="${qdto.content }"></c:out></td>
						<td colspan="3"><c:out value="${qdto.response }"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>