<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DTO.CommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="survice.MemberService"%>
<%@page import="vo.MemberVO"%>
<%@page import="DTO.BoardDTO"%>
<%@page import="survice.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%!
	public String getDateDiff(String date){
		String result = "";
		Date today = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date cmt_date = format1.parse(date);
			long diff = today.getTime() - cmt_date.getTime();
			long sec = diff/1000;
			if(sec < 60)
				result=sec+"초 전";
			else if(sec < 60*60)
				result=sec/60+"분 전";
			else if(sec < 60*60*24)
				result=sec/(60*60)+"시간 전";
			else if(sec < 60*60*24*30)
				result=sec/(60*60*24)+"일 전";
			else if(sec < 60*60*24*30*365)
				result=sec/(60*60*24*30)+"달 전";
			else
				result=sec/(60*60*24*30*365)+"년 전";

		}catch(Exception e){
			result = "오류";
		}
		return result;
	}
%>
<%
	int bNo = Integer.parseInt(request.getParameter("bNo"));
	BoardDTO dto = BoardService.getInstance().selectBoard(bNo);
	MemberVO vo = MemberService.getInstance().select(dto.getWriter());
	String id = "방문객";
	if(session.getAttribute("login") != null && (boolean)session.getAttribute("login"))
		id = (String)session.getAttribute("id");
%>
<style>
	#container{
		width:100%;
		position:absolute;
		margin-top:100px;
	}
	.container h3{
		margin:30px 0;
		font-family: 'Black Han Sans', 'sans-serif';
		color:#007fff;
	}
	#tit{
		border-left:4px solid #007fff;
		font-size: 17px;
		padding:5px;
		padding-left:20px;
		margin-bottom:0;
	}
	#info{
		margin-top:0;
		border-left:4px solid #007fff;
		padding:5px;
		padding-left:20px;
		font-size: 14px;
		color:gray;
	}
	#info span{
		float:right;
	}
	#content{
		padding:50px;
		height:500px;
	}
	/*댓글작성******************************/
	#cmt_content{
		width:100%;
		height:100px;
		resize:none;
		padding:5px 10px;
		outline: none;
	}
	#cmt_length{
		text-align: right;
		margin-top: -23px;
	}
	#cmt_length span{
		margin:5px;
	}
	#cmt_length+p{
		text-align: right;	
	}
	#cmt_write{
		border-bottom:1px solid #e8e8e8;
	}
	/*댓글*********************************************/
	#cmts{
		margin-top:50px;
	}
	#cmt_h{
		border-bottom:2px solid #007fff;
		padding:0;
	}
	#cmt_h span{
		display:inline-block;
		background-color: #007fff;
		color:white;
		margin:0;
		padding:10px;
	}
	.cmt{
		border-left:3px solid #007fff;
		margin:20px 0;
		padding-left:15px;
	}
</style>
<script>
	$(function(){
		$("#cmt_content").keydown(function(){
			var length = $(this).val().length;
			$("#cmt_length span").text(length+"/500");
		})
		
		$("#btn_write").click(function(){
			<%
				if(id.equals("방문객")){
			%>
				alert("댓글 작성을 위해 로그인화면 으로 이동합니다.");
				location.href="<%=request.getContextPath()%>/member/login.jsp";
			<%
				}else{
			%>
			var comment = $("#cmt_content").val();
			var writer = "<%=id%>";
			var bNo = <%=request.getParameter("bNo")%>;
			$.ajax({
				url:"<%=request.getContextPath()%>/board/process/comment_write_process.jsp",
				method:'get',
				data:{"comment":comment,"writer":writer,"bNo":bNo},
				success:function(d){
					location.reload();
				}
			})
			
			<%
				}
			%>
		})
	})
</script>

<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<jsp:include page="/template/board_h.jsp"></jsp:include>
	<div id="container">
		<div class="container">
			<h3>게시글 보기</h3>
			<div id="board">
				<p id="tit">
					제목: <%=dto.getTitle() %>
				</p>
				<p id="info">
					<%=vo.getName() %> | <%=dto.getbDate() %>
					<span>
						view <%=dto.getbCount() %> | like <%=dto.getbLike() %> | hate <%=dto.getbHate() %>
					</span>
				</p>
				<hr>
				<div id="content">
					<%=dto.getContent() %>
				</div>
				<hr>
			</div>
			<div id="cmt_write">
				<p>
				<%
					if(id.equals("방문객")){
				%>					
					<a href="<%=request.getContextPath() %>/member/login.jsp">로그인</a>
					하고 댓글 작성하기
				<%
					}else{
				%>
					<%=vo.getName()%>(<%=id%>)		
				<%
					}
				%>
				</p>
				<textarea id="cmt_content" name="cmt_content" maxlength="500"></textarea>
				<p id="cmt_length">
					<span>0/500</span>
				</p>
				<p>
					<button id="btn_write" class="btn btn-info">작성</button>
				</p>
			</div>
			<div id="cmts">
				<p id="cmt_h">
					<span>전체댓글: </span>
				</p>
				<%
					ArrayList<CommentDTO> li = 
						BoardService.getInstance().selectAllComment(bNo);
					for(CommentDTO cdto : li){
				%>
				<div class="cmt">
					<p>
						<%=MemberService.getInstance().select(cdto.getWriter()).getName() %> 
						<small><%=getDateDiff(cdto.getcDate()) %></small>
					</p>
					<p><%=cdto.getContent() %></p>
				</div>
				<hr>
				<%
					} 
				%>
			</div>
		</div>
	</div>
</body>
</html>