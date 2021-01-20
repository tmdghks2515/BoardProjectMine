<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
<script>
	$(function(){
		var i = 1;
 		$("#btn1").click(function(){
 			if(i==5) return;
			i++;
 			$("#files").append("<p><input type='file' name='file"+i+"'></p>");
 		})
 		
  		$("#btn2").click(function(){
  			if(i>1) {
  				i--;
	  			$("#files p").last().remove();
  			}
 		})
	})
</script>
</head>
<body>
	<form action="upload.do" enctype="multipart/form-data" method="post">
		<input type="text" name="writer" placeholder="작성자"><br>
		<div id="files">
			<p>
				<input type="file" name="file1">
				<button type="button" id="btn1">+</button> <button type="button" id="btn2">-</button>
			</p>
		</div>
		<br>
		<button>업로드</button>
	</form>
</body>
</html>