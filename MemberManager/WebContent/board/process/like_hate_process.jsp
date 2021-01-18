<%@page import="service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../../error/error.jsp" %>
<%
	int flag = Integer.parseInt(request.getParameter("flag"));
	String id = request.getParameter("id");
	int bNo = Integer.parseInt(request.getParameter("bNo"));
	
	if(id.equals("방문객")){
%>
	<script>
		alert("로그인이 필요한 작업입니다.");
		history.back();
	</script>
<%
	}else{
		boolean check = BoardService.getInstance().boardLikeCheck(bNo, id);
		if(check){
		%>
			<script>
				alert("좋아요는 한번만 누를수 있습니다.");
				history.back();
			</script>
		<%
		}else{
			BoardService.getInstance().boardLike(bNo,id,flag);
			response.sendRedirect(request.getContextPath()+"/board/board_view.jsp?bNo="+bNo);
		}
	}
	
%>