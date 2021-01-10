<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	/*게시판 작성부분******************************************/
	.container{
		box-sizing: border-box;
		padding:5px;
	}
	.row{
		padding:10px;
	}
	.row:nth-child(2) input{
		width:100%;
		line-height: 25px;
	}
	.row:nth-child(3) textarea{
		width:100%;
		height:300px;	
		resize:none;
	}
	.row:nth-child(3) p{
		color:gray;
		text-align: right;
		margin-top:-30px;
		padding:5px;
	}
	.row:nth-child(4){
		padding-top:0;		
	}
	.row:nth-child(4) div{
		text-align: right;
	}
</style>
<script>
	$(function(){
		alert("<%=request.getRequestURI()%>")		
		$("textarea").keydown(function(){
			$(this).next().children().text($(this).val().length+"/500");
		})
		
		$("#btn").click(function(){
			var url = "process/board_write_process.jsp";
			var writer="<%=session.getAttribute("id")%>";
			var title = $("#title").val();
			var content = $("#content").val();
			$.ajax({
				url:url,
				method:'get',
				data:{"writer":writer,"title":title,"content":content},
				success:function(d){
					location.href = "board_view.jsp?bNo="+d;				
				},
				error:function(d){
					alert(d);
				}
			})
		})
	})
</script>
</head>
<%
	if(session.getAttribute("login")!=null && (boolean)session.getAttribute("login")){
%>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<div id="container">
		<h1><a href="<%=request.getContextPath() %>/index.jsp">Hiver</a> 게시판</h1>
		<div class="container">
				<div class="row">
					<div class="col-xs-1 col-xs-offset-2">작성자: </div>
					<div class="col-xs-6"><%=session.getAttribute("id")%><input type="hidden" name="writer" value="<%=session.getAttribute("id")%>"></div>
				</div>
				<div class="row">
					<div class="col-xs-1 col-xs-offset-2">제목: </div>
					<div class="col-xs-6"><input type="text" id="title" name="title"></div>
				</div>
				<div class="row">
					<div class="col-xs-1 col-xs-offset-2">내용: </div>
					<div class="col-xs-6">
						<textarea id="content" name="content" maxlength="500"></textarea>
						<p><small>0/500</small></p>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<button id="btn" class="btn btn-info">작성하기</button>
					</div>
				</div>
		</div>
	</div>
</body>
<%
	}else{
%>
	<script>
		alert("게시글 작성을 위해 로그인 화면 으로 이동하니다.");
		location="<%=request.getContextPath()%>/member/login.jsp?url=board/write_board.jsp";
	</script>	
<%
	}
%>
</html>