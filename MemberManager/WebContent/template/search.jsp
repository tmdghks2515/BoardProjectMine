<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 폰트 Black Han Sans -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<style>
@font-face{
	font-family:'Noto Sans Black';
	src: url(${pageContext.request.contextPath}/resource/font/NotoSansKR-Black.otf) format("truetype");
}
h1{
	text-align: center;
	font-family:'Noto Sans Black';
	font-weight: bold;
}
h1 a:link , h1 a:visited{
	text-decoration: none;
	color:#007fff;
}
/*검색창  **************************************/

#search{
	width:600px;
	margin:30px auto;
	border:2px solid #80d9ff;
	height:44px;
}
#search form{
	float:left;
	width:90%
}
#search input{
	outline:none;
	border:none;
	width:400px;
	height:40px;
	font-size: 14px;
}
#search span, #search input , #search div{
	padding:8px 10px;
}
#search a:link, #search a:visited{
	display: block;
	width:10%;
	text-decoration: none;
	color:white;
	background-color: #80d9ff;
	padding:5px 5px;
	float:left;
	text-align: center;
	padding:10px;
	transition:all 0.3s;
}
#search a:hover{
	background-color: #bfecff;
	color:black;
}
</style>
<body>
	<h1><a href="#">파이버</a></h1>
	<div id="search">
		<form action="#">
			<label>
				<span class="glyphicon glyphicon-search"></span>
				<input type="text" placeholder="검색할 내용 입력">
			</label>
		</form>
		<a href="#">검색</a>
	</div>
</body>