<%@page import="vo.MemberVO"%>
<%@page import="survice.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	MemberVO vo = MemberService.getInstance().login(id, pass);
	if(vo == null){
	%>
	<script>
		alert("아이디 비밀번호를 다시 확인하세요");
		history.back();
	</script>
	<%}else{
		session.setAttribute("login", true);
		session.setAttribute("id", vo.getId());
		session.setAttribute("name", vo.getName());
		session.setAttribute("grade", vo.getGrade());
		response.sendRedirect("main.jsp");
	}
%>