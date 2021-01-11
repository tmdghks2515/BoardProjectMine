<%@page import="service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cNo = Integer.parseInt(request.getParameter("cNo"));
	int index = Integer.parseInt(request.getParameter("index"));
	
	BoardService.getInstance().cLikeHate(cNo,index);
%>