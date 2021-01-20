<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String fileName = request.getParameter("fileName");
	String writer = request.getParameter("writer");
	//다운로드할 파일 전체경로
	String path = "C:\\UploadedFiles\\"+writer+"\\"+fileName;
	File file = new File(path);
	FileInputStream fis = new FileInputStream(file);
	String encodingName = URLEncoder.encode(path,"utf-8");
	
	//다운로드시 나타낼 기본파일명
	response.setHeader("Content-Disposition", "attachment;filename="+fileName);
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setContentLengthLong(file.length());
	//사용자에게 파일을 전송하는 부분
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	//버퍼생성
	byte[] buffer = new byte[1024*1024*50];
	
	while(true){
		int size = fis.read(buffer);//읽어온 바이트수
		if(size == -1) break;//더이상 읽을 내용이 없다
		bos.write(buffer,0,size);
		bos.flush();
	}
	
	fis.close();
	bos.close();
%>
    