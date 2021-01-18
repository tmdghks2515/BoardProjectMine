<%@page import="vo.PagingVO"%>
<%@page import="service.BoardService"%>
<%@page import="dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	#container{
		width:100%;
		position:absolute;
		margin-top:150px;
	}
	#board_list span{
		display: block;
		float:left;
		text-align: center;
		padding:8px;
	}
	#board_list p{
		clear:both;
		height:35px;
		border-bottom:1px solid #e8e8e8;
		transition: all 0.1s;
		margin:0;
	}
	#board_list p:hover{
		background-color: #a5dff9;
	}
	#board_list span:first-child{
		width:7%;
	}
	#board_list span:nth-child(2){
		width:30%;
		text-align:left;
	}
	#board_list span:nth-child(3){
		width:15%;
	}
	#board_list span:nth-child(4){
		width:25%;
	}
	#board_list span:nth-child(5){
		width:7%;
	}
	#board_list span:nth-child(6){
		width:9%;
	}
	#board_list span:nth-child(7){
		width:7%;
	}
	#board_list span:nth-child(8){
		width:7%;
	}
	#board_list{
		border-top:2px solid #007fff;
		border-bottom:2px solid #007fff;
	}
	#board_list #board_h{
		border-bottom:1px double #007fff;
		padding:5px;
		font-size:16px;
		fint-weight:bold;
		height:44px;
		background-color:#007edd;
		color:white;
	}
	#board_h a:link,#board_h a:visited{
		color:white;
	}
</style>
<script>
	$(function(){
		$(".pagination li").eq(${(sessionScope.pvo.currentPage)%sessionScope.pvo.pagePerGroup}-1).addClass("active");
			
	})
</script>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<jsp:include page="/template/board_h.jsp"></jsp:include>
	<div id="container">
		<div class="container">
			<div id="board_list">
			<p id="board_h">
				<span><a href="../board_list.do?mode=bno">번호</a></span><span style="text-align: center;">제목</span><span>글쓴이</span><span>작성일</span>
				<span>조회</span><span><a href="../board_list.do?mode=blike">좋아요</a></span><span><a href="../board_list.do?mode=bhate">싫어요</a></span>
			</p>
			<c:forEach var="bdto" items="${sessionScope.li }">
				<p>
					<span><c:out value="${bdto.bNo }"></c:out></span>
					<span><a href="boardView.do?bNo=${bdto.bNo }">${bdto.title}</a></span>
					<span><c:out value="${bdto.writer}"></c:out></span>
					<span><c:out value="${bdto.bDate }"></c:out></span>
					<span><c:out value="${bdto.bCount }"></c:out></span>
					<span><c:out value="${bdto.bLike }"></c:out></span>
					<span><c:out value="${bdto.bHate }"></c:out></span>
				</p>
			</c:forEach>
			</div>
			<div id="board_f" class="row">
				<div class="col-xs-4">
					<ul class="pager">
					<c:if test="${!sessionScope.pvo.firstGroup }">
						<li class="previous"><a href="../board_list.do?page=${pvo.firstPageOfGroup - 1 }&mode=${sessionScope.pvo.mode}">&lt;이전</a></li>
					</c:if>
					</ul>
				</div>
				<div class="col-xs-4 text-center">
					<ul class="pagination">
					<c:forEach var="i" begin='${sessionScope.pvo.firstPageOfGroup }' end='${sessionScope.pvo.lastPageOfGroup }'>
						<c:choose>
						<c:when test="${sessionScope.pvo.currentPage == i }">
							<li><a>${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="../board_list.do?page=${i}&mode=${sessionScope.pvo.mode}">${i }</a></li>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</ul>
				</div>
				<div class="col-xs-4 text-left">
					<ul class="pager">
					<c:if test="${!sessionScope.pvo.lastGroup }">
						<li class="next"><a href="../board_list.do?page=${sessionScope.pvo.lastPageOfGroup + 1}&mode=${sessionScope.pvo.mode}">다음&gt;</a></li>
					</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>