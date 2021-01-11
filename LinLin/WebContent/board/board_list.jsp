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
	ArrayList<BoardDTO> li = BoardService.getInstance().selectAllBoards();
	int p = 0;
	if(request.getParameter("p") != null)
		p = Integer.parseInt(request.getParameter("p"));
	if(p < 0)
		p = 0;
	if(p > (int)Math.ceil(li.size()/(double)20)-1)
		p = (int)Math.ceil(li.size()/(double)20)-1;
%>
<script>
	$(function(){
		$(".pagination li").eq(<%=p %>).addClass("active");
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
				for(int i=p*20;i<20+p*20 && i<li.size();i++){
			%>
				<p>
					<span><%=li.get(i).getbNo() %></span>
					<span><a href="board_view.jsp?bNo=<%=li.get(i).getbNo()%>"><%=li.get(i).getTitle() %> [<%=BoardService.getInstance().selectAllComment(li.get(i).getbNo()).size() %>]</a></span>
					<span><%=li.get(i).getWriter() %></span>
					<span><%=li.get(i).getbDate() %></span>
					<span><%= li.get(i).getbCount()%></span>
					<span><%=li.get(i).getbLike() %></span>
					<span><%=li.get(i).getbHate() %></span>
				</p>
			
			<%
				}
			%>
			</div>
			<div id="board_f" class="row">
				<div class="col-xs-4">
					<ul class="pager">
						<li class="previous text-right"><a href="<%=request.getRequestURL()%>?p=<%=p-1%>">&lt;이전</a></li>
					</ul>
				</div>
				<div class="col-xs-4 text-center">
					<ul class="pagination">
					<%
						for(int i=0;i<Math.ceil(li.size()/(double)20);i++){
					%>
						<li><a href="<%=request.getRequestURL()%>?p=<%=i %>"><%=i+1 %></a></li>
					<%
						}
					%>
					</ul>
				</div>
				<div class="col-xs-4 text-left">
					<ul class="pager">
						<li class="next"><a href="<%=request.getRequestURL()%>?p=<%=p+1%>">다음&gt;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>