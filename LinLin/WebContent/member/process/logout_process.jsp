<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();
	if(request.getParameter("bNo") == null)
		response.sendRedirect(request.getParameter("url"));
	else
		response.sendRedirect(request.getParameter("url")+"?bNo="+request.getParameter("bNo"));
%>