<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.CommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.MemberService"%>
<%@page import="vo.MemberVO"%>
<%@page import="dto.BoardDTO"%>
<%@page import="service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
		border:1px solid #007fff;
		box-shadow:0 0 2px #007fff;
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
	/*좋아요싫어요******************************************************/
	#btns{
		text-align: center;
	}
	#btns span{
		display: inline-block;
		width:64px;
		text-align: center;
		margin:20px; 
	}
	#btns img{
		border-radius: 50px;
	}
	#btns img:hover{
		box-shadow: 0 0 3px black;
	}
</style>
<script>
	$(function(){
		$("#cmt_content").keydown(function(){
			var length = $(this).val().length;
			$("#cmt_length span").text(length+"/500");
		})
		
		$("#btn_write").click(function(){
			if(${sessionScope.login==null || !sessionScope.login}){
				alert("댓글 작성을 위해 로그인화면 으로 이동합니다.");
				location.href="../member/login.jsp?url=boardView.do&bNo=${sessionScope.bdto.bNo}";
			}
				
			var comment = $("#cmt_content").val();
			var writer = "${sessionScope.id}";
			var bNo = ${sessionScope.bdto.bNo};
			$.ajax({
				url:"commentWrite.do",
				method:'get',
				data:{"comment":comment,"writer":writer,"bNo":bNo},
				success:function(d){
					location.reload();
				}
			})
			
		})
		
 		$(".clike a").click(function(e){
			e.preventDefault();
			if(${sessionScope.login==null}){
				alert("로그인이 필요한 작업입니다.");
			}else{
				var index = $(this).index()%2;
				var cNo = $(this).next().val();
				$.ajax({
					url:"commentLike.do",
					data:{"cNo":cNo,"index":index},
					method:'get',
					success:function(d){
						location.reload();
					}
				})
			}
		})
		
		$("#btns a").click(function(){
			var index = $("#btns a").index($(this));
			$.ajax({
				url:"boardLike.do",
				data:{"index":index},
				method:'get',
				success:function(d){
					if(d!=""){
						alert(d);
					}
					location.reload();
				}
			})			
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
					제목: ${sessionScope.bdto.title }
				</p>
				<p id="info">
					${sessionScope.bdto.getName() }(${sessionScope.bdto.getHiddenId() }) | ${sessionScope.bdto.bDate }
					<span>
						view ${sessionScope.bdto.bCount } | like ${sessionScope.bdto.bLike } | hate ${sessionScope.bdto.bHate }
					</span>
				</p>
				<hr>
				<div id="content">
					${sessionScope.bdto.content }
				</div>
				<div id="btns">
					<span><a href="#"><img src="/resource/img/like.png"></a>좋아요</span>
					<span><a href="#"><img src="/resource/img/hate.png"></a>싫어요</span>
				</div>
				<hr>
			</div>
			<div id="cmt_write">
				<p>
				<c:choose>
				<c:when test="${sessionScope.login==null }">
					<a href="${pageContext.request.contextPath }/member/login.jsp?url=boardView.do&bNo=${sessionScope.bdto.bNo}">로그인</a>
					하고 댓글 작성하기
				</c:when>
				<c:otherwise>
					${sessionScope.name }(${sessionScope.hiddenId})		
				</c:otherwise>
				</c:choose>
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
					<span>전체댓글: ${sessionScope.cli.size() }개</span>
				</p>
				<c:forEach var="cdto" items="${sessionScope.cli }">
				<div class="cmt">
					<p>
						${cdto.getName() }(${cdto.getHiddenId() })
						<small>${cdto.getDateDiff()}</small>
					</p>
					<p>${cdto.content }</p>
					<p class="clike">
						<a href="#"><span class="glyphicon glyphicon-thumbs-up">좋아요</span></a><input type="hidden" value="${cdto.cNo }"> <span id="like_count">${cdto.cLike }</span> 
						<a href="#"><span class="glyphicon glyphicon-thumbs-down">싫어요</span></a><input type="hidden" value="${cdto.cNo }"> <span id="hate_count">${cdto.cHate }</span>
					</p>
				</div>
				<hr>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>