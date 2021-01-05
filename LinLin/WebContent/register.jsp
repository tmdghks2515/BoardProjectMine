<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<style>
	#container{
		position:absolute;
		width:100%;
	}
	
	h1 a:visited, h1 a:link{
		text-decoration: none;
		color:#0080ff;
		font-weight: bold;
	}
	#container h1{
		color:#0080ff;
		font-family: 'Black Han Sans', 'sans-serif';
		text-align: center;
		margin:30px 0;
	}
	
	#reg{
		width: 400px;
		margin: 0 auto;
	}
	#reg input{
		margin-bottom:20px;
	}
</style>
<script>
	$(function(){
		$("input").eq(0).on({"keyup":function(){
			if($(this).val().length < 6 || $(this).val().length>15){
				$(this).css("margin","0");
				$(this).next().text("아이디는 6~15자 입니다.");
				$(this).next().addClass("false");
			}else{
				$(this).css("margin-bottom","20px");
				$(this).next().text("");
			}
		},"blur":function(){
			$.ajax({
				data:$("#frm").serialize(),
				url:"name_test.jsp",
				method:'get',
				success:function(data){
					if($("#frm input").eq(0).val().length < 6 || $("#frm input").eq(0).val().length > 15){
						$("#frm input").eq(0).css("margin",0);
						$("#frm input").eq(0).next().text("아이디는 6~15자 입니다.");
						$("#frm input").eq(0).next().css("color","red");
					}else if(data == 0){
						$("#frm input").eq(0).css("margin","0");
						$("#frm input").eq(0).next().text("사용 가능한 아이디 입니다.");
						$("#frm input").eq(0).next().css("color","blue");
					}else if(data == 1){
						$("#frm input").eq(0).css("margin","0");
						$("#frm input").eq(0).next().text("이미 사용중인 아이디 입니다.");
						$("#frm input").eq(0).next().css("color","red");
					}
				}
			})
		}})
		
		var alphaRex = /[a-zA-Z]/;
		var numRex = /[0-9]/;
		var speRex = /[`~!@#$%^&*_=+<>]/;
		$("input").eq(1).keyup(function(){
			if(alphaRex.test($(this).val()) && numRex.test($(this).val()) && speRex.test($(this).val()) && $(this).val().length >= 8 && $(this).val().length <= 15){
				$(this).css("margin",0);
				$(this).next().text("사용가능한 비밀번호.");
				$(this).next().css("color","blue");								
			}else{
				$(this).css("margin",0);
				$(this).next().text("비밀번호는 영문자,숫자,특수문자 조합의 8~15자 입니다.");
				$(this).next().css("color","red");								
			}
		})
		
		$("#frm input").eq(2).keyup(function(){
			if($(this).val() == $(this).prev().prev().val()){
				$(this).css("margin",0);
				$(this).next().text("일치");
				$(this).next().css("color","blue");
			}else{
				$(this).css("margin",0);
				$(this).next().text("불일치");
				$(this).next().css("color","red");
			}
		})
	})
</script>
</head>
<body>
	<jsp:include page="template/header.jsp"></jsp:include>
	<div id="container">
		<h1><a href="main.jsp">HIVER</a> 회원가입</h1>
		<div id="reg">
			<form id="frm" action="reg_process.jsp" method="post" class="form">
				<input type="text" name="id" placeholder="아이디" class="form-control">
				<small></small>
				<input type="password" name="pass" placeholder="비밀번호" class="form-control">
				<small></small>
				<input type="password" name="pass2" placeholder="비밀번호 확인" class="form-control">
				<small></small>
				<input type="text" name="name" placeholder="이름" class="form-control">
				<input type="text" name="age" placeholder="나이" class="form-control">
				<button class="btn btn-primary">회원가입</button>
				
			</form>
		</div>
	</div>
	
</body>
</html>