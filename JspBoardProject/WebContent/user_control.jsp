<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="member" class="project.member.vo.Member"/>
<jsp:useBean id="memberService" class="project.service.MemberService"/>

<%
	String action = request.getParameter("action");

	member.setMember_email(request.getParameter("member_email"));
	
	member.setMember_password(request.getParameter("member_password"));

	if(action.equals("new")) {
		member.setMember_id(request.getParameter("member_id"));
		int group_id = 0;
		out.println(request.getParameter("group"));
		/* int group_id = Integer.parseInt(request.getParameter("group")); */
		int uid = memberService.joinMember(member, group_id);
		if( uid > -1) {
			session.setAttribute("uid", uid);
			session.setAttribute("member_id", member.getMember_id());
			response.sendRedirect("project_main.jsp");
		} else {
			out.println("<script>alert('회원가입 실패');history.go(-1);</script>");
		}
	} else if(action.equals("login")) {
		member = memberService.login(member);
		if(member != null) {
			session.setAttribute("uid", member.getMember_uid());
			session.setAttribute("member_id", member.getMember_id());
			response.sendRedirect("project_main.jsp");
		} else {
			out.println("<script>alert('로그인 실패');history.go(-1);</script>");
		}
	} else if(action.equals("logout")) {
		session.removeAttribute("uid");
		session.removeAttribute("member_id");
		response.sendRedirect("project_main.jsp");
	}

%>