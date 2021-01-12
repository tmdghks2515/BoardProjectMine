<%@page import="service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("id") == null){
		%>
		<script>
			alert("로그인이 필요한 작업입니다.");
		</script>
		<%
	}else{
		String id = (String)session.getAttribute("id");
		int cNo = Integer.parseInt(request.getParameter("cNo"));
		int index = Integer.parseInt(request.getParameter("index"));
		BoardService.getInstance().cLikeHate(id,cNo,index);
	}
%>