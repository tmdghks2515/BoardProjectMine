<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
</head>
<body>
	<form action="upload.do" enctype="multipart/form-data" method="post">
		파일 : <input type="file" name="file1"><br>
		파일 : <input type="file" name="file2"><br>
		작성자 : <input type="text" name="param"><br>
		<button>업로드</button>
	</form>
</body>
</html>