<%@page import="survice.BoardService"%>
<%@page import="DTO.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../../error/error.jsp" %>
<%
	String writer = request.getParameter("writer");
	String content = request.getParameter("comment");
	int bNo = Integer.parseInt(request.getParameter("bNo"));
	CommentDTO cdto = new CommentDTO(bNo,content,writer);
	BoardService.getInstance().insertComment(cdto);
%>