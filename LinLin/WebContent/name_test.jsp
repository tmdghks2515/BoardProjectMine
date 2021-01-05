<%@page import="survice.MemberService"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	MemberVO vo = MemberService.getInstance().select(id);
	if(vo == null)
		out.write("0");
	else
		out.write("1");
%>