<%@page import="vo.PagingVO"%>
<%@page import="service.BoardService"%>
<%@page import="dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		width:40%;
		text-align:left;
	}
	#board_list span:nth-child(3){
		width:15%;
	}
	#board_list span:nth-child(4){
		width:15%;
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
</style>
<%
	int p = 1;
	if(request.getParameter("p") != null)
		p = Integer.parseInt(request.getParameter("p"));
	PagingVO vo = new PagingVO(BoardService.getInstance().getBoardTotal() , p);
	int pMax = vo.getPageTotal();
		
%>
<script>
	$(function(){
		$(".pagination li").eq(<%=(p-1) % vo.getPagePerGroup() %>).addClass("active");
	})
</script>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<jsp:include page="/template/board_h.jsp"></jsp:include>
	<div id="container">
		<div class="container">
			<div id="board_list">
			<p id="board_h">
				<span>번호</span><span style="text-align: center;">제목</span><span>글쓴이</span><span>작성일</span>
				<span>조회</span><span>좋아요</span><span>싫어요</span>
			</p>
			<%
				ArrayList<BoardDTO> li = BoardService.getInstance().selectBoards(
						p , vo.getBoardPerPage());
				for(BoardDTO bdto : li){
			%>
				<p>
					<span><%=bdto.getbNo() %></span>
					<span><a href="board_view.jsp?bNo=<%=bdto.getbNo()%>"><%=bdto.getTitle() %> [<%=BoardService.getInstance().selectAllComment(bdto.getbNo()).size() %>]</a></span>
					<span><%=bdto.getWriter() %></span>
					<span><%=bdto.getbDate() %></span>
					<span><%= bdto.getbCount()%></span>
					<span><%=bdto.getbLike() %></span>
					<span><%=bdto.getbHate() %></span>
				</p>
			
			<%
				}
			%>
			</div>
			<div id="board_f" class="row">
				<div class="col-xs-4">
					<ul class="pager">
					<%if(!vo.isFirstGroup()){%>
						<li class="previous"><a href="<%=request.getRequestURL()%>?p=<%=vo.getFirstPageOfGroup()-1%>">&lt;이전</a></li>
					<%}%>
					</ul>
				</div>
				<div class="col-xs-4 text-center">
					<ul class="pagination">
					<%
						for(int i=vo.getFirstPageOfGroup() ; i<=vo.getLastPageOfGroup() ; i++){
					%>
						<li><a href="<%=request.getRequestURL()%>?p=<%=i %>"><%=i %></a></li>
					<%
						}
					%>
					</ul>
				</div>
				<div class="col-xs-4 text-left">
					<ul class="pager">
					<%if(!vo.isLastGroup()){%>
						<li class="next"><a href="<%=request.getRequestURL()%>?p=<%=vo.getLastPageOfGroup()+1%>">다음&gt;</a></li>
					<%}%>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>