<%@page import="DTO.BoardDTO"%>
<%@page import="survice.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../../error/error.jsp"  %>
<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	int bNo = BoardService.getInstance().insertBoard(writer,title,content);
	out.write(bNo);
%>