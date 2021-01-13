<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- bootstrap cdn -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 폰트 Black Han Sans  	font-family: 'Black Han Sans', 'sans-serif';-->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<script>
	$(function(){
		$("#main").mouseenter(function(){
			$("#sub").stop().slideDown(400);
		})
		$("#sub").mouseleave(function(){
			$("#sub").stop().slideUp(400);
		})
		$("#")
	})
</script>
<style>
*{
	margin:0;
	padding:0;
	box-sizing: border-box;
}
/*메인메뉴 *************************************/
	nav{
		height:40px;
		background-color: #007fff;
	}
	ul,li{
		margin:0;
		padding:0;
		list-style-type: none;
	}
	#main{
		width:70%;
		float:left;
	}
	#main li{
		width:25%;
		float:left;
	}
	#main a:link  , #main a:visited{
		display: block;
		text-decoration: none;
		text-align: center;
		padding:10px 0;
		color:white;
		transition: all 0.5s;
	}
	#main a:hover{
		background-color: #00b2fe;
	}
	
<%
	if(session.getAttribute("login") != null && (boolean)session.getAttribute("login") == true){
%>

/*유저정보 *************************************/
#user{
	width:30%;
	float:right;
	color:white;
	padding-left:50px;
}
#user img{
	width:40px;
	height:40px;
	border-radius: 100px;
	float:right;
}
#user div{
	float:right;
	height:40px;
	line-height:20px;
	margin-right:30px;
	text-align: center;
}
#user a:link, #user a:visited{
	color:white;
}


<%}else{%>
#login{
	float:right;
	padding-right: 100px;
	height:40px;
	line-height:40px;
	text-align: center;
	color:white;
}
#login a:link, #login a:visited{
	text-decoration: none;
	color:white;
}
#login small{
	margin: 0 5px;
}

<%}%>

/*서브메뉴 **************************************/
#sub{
	clear:both;
	background-color: #007cb2;
	height:159.94px;
	display: none;
	position:relative;
	opacity: 0.5;
	z-index:2;
}
.sub{
	width:17.5%;
	float:left;
}
.sub a:link , .sub a:visited{
	display: block;
	text-decoration: none;
	color:white;
	transition: all 0.3s;
	padding:6px 0;
	text-align: center;
}
.sub a:hover{
	background-color: #bfecff;
	color:black;
}
</style>
<body>
	<nav>
		<ul id="main">
			<li><a href="<%=request.getContextPath()%>/board/board_list.jsp">게시판</a></li>
			<li><a href="qnaView.do">문의하기</a></li>
			<li><a href="#">메인메뉴3</a></li>
			<li><a href="#">메인메뉴4</a></li>
		</ul>
		
<%
	if(session.getAttribute("login") != null && (boolean)session.getAttribute("login") == true){
%>

		<div id="user">
			<div>
				<small>${sessionScope.name}(${sessionScope.grade}) 님이 로그인 했습니다</small><br>
				<small><a href="update.do">정보수정</a> | <a href="${pageContext.request.contextPath }/logout.do">로그아웃</a></small>
			</div>
			<img src="${pageContext.request.contextPath }/resource/img/dog.jpg">
		</div>
		
<%}else{%>
	
	<div id="login">
		<small><a href="${pageContext.request.contextPath}/member/login.jsp?url=${pageContext.request.requestURL}<%if(request.getParameter("bNo")!=null){%>&bNo=${requestScope.bNo} <%}%>">로그인</a></small> |
		<small><a href="<%=request.getContextPath() %>/member/register.jsp">회원가입</a></small>
	</div>
<%}%>

		<div id="sub">
			<ul class="sub">
				<li><a href="<%=request.getContextPath()%>/board/write_board.jsp">게시글 작성</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
			</ul>
			<ul class="sub">
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
			</ul>
			<ul class="sub">
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
			</ul>
			<ul class="sub">
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
				<li><a href="#">서브메뉴</a></li>
			</ul>
		</div>
	</nav>
</body>