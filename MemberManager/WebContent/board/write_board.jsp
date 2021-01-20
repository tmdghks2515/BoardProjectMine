<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	.row:nth-child(5){
		padding-top:0;		
	}
	.row:nth-child(5) div{
		text-align: right;
	}
	.row:nth-child(4){
		font-size: 12px;
	}
	.row:nth-child(4) input{
		display: inline-block;
		width:200px;
	}
	#plus,#minus{
		padding:1px 6px;
		margin:3px;
	}
</style>
<script>
	$(function(){
		var i = 1;
 		$("#plus").click(function(){
 			if(i==5) return;
			i++;
 			$("#files").append("<p><input type='file' name='file"+i+"'></p>");
 		})
 		
  		$("#minus").click(function(){
  			if(i>1) {
  				i--;
	  			$("#files p").last().remove();
  			}
 		})
		
		$("textarea").keydown(function(){
			$(this).next().children().text($(this).val().length+"/500");
		})
		
	})
</script>
</head>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<div id="container">
		<h1><a href="${pageContext.request.contextPath }/index.jsp">Hiver</a> 게시판</h1>
		<div class="container">
			<form action="boardWrite.do"  enctype="multipart/form-data" method="post" id="frm">
				<div class="row">
					<div class="col-xs-1 col-xs-offset-2">작성자: </div>
					<div class="col-xs-6">
						${sessionScope.id }
						<input type="hidden" name="writer" value="${sessionScope.id}">
					</div>
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
					<div class="col-xs-1 col-xs-offset-2">첨부파일: </div>
					<div class="col-xs-8" id="files">
						<p>
							<input type="file" name="file1"> <button type="button" id="plus">+</button> <button type="button" id="minus">-</button>
						</p>	
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<button id="btn" class="btn btn-info">작성하기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>