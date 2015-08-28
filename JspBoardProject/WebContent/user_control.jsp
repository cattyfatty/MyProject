<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="member" class="project.member.vo.Member" scope="session"/>
<jsp:useBean id="group" class="project.member.vo.Group"/>
<jsp:useBean id="memberService" class="project.service.MemberService"/>
<jsp:setProperty name="member" property="*" />
<jsp:setProperty name="group" property="*" />
<%
	String action = request.getParameter("action");

	if(action.equals("new")){
		int uid = memberService.joinMember(member, group.getGroup_id());
		if(uid > -1) {
			out.println("goes in here");
			member.setMember_uid(uid);
			session.setAttribute("member", member);
		} else {
			out.println("<script>alert('id exist');history.go(-1);</script>");
		}
		response.sendRedirect("project_main.jsp");
	} else if(action.equals("signin")) {
		member = memberService.login(member);
		if(member != null) {
			out.println("goes in here");
			session.setAttribute("member", member);
			response.sendRedirect("project_main.jsp");
		}
		
	} else if(action.equals("signout")) {
		session.removeAttribute("member");
		response.sendRedirect("project_main.jsp");
	}

%>