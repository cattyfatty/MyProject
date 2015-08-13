<%@ page contentType="text/html; charset=UTF-8" errorPage="addrbook_error.jsp"
    pageEncoding="UTF-8" import="example01.addrbook.*, java.util.*"%>
    
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="ab" scope="page" class="example01.addrbook.AddrBean" />
<jsp:useBean id="addrbook" class="example01.addrbook.AddrBook" />
<jsp:setProperty name="addrbook" property="*" />

<% 
	String action = request.getParameter("action");
	if(action.equals("list")) {
		ArrayList<AddrBook> list = ab.selectDBAll();
		request.setAttribute("list", list);
		pageContext.forward("addrbook_list.jsp");
	} else if(action.equals("insert")) {
		if(ab.insertDB(addrbook) > 0) {
			response.sendRedirect("addrbook_control.jsp?action=list");
		} else {
			throw new Exception("failed to insert");
		}
	} else if(action.equals("edit")) {
		AddrBook abook = ab.selectDBbyId(addrbook.getAb_id());
		if(!request.getParameter("upasswd").equals("1234")) {
			out.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");
		} else {
			request.setAttribute("ab", abook);
			pageContext.forward("addrbook_edit_form.jsp");
		}
	} else if(action.equals("update")) {
		if(ab.updateDB(addrbook) > 0) {
			response.sendRedirect("addrbook_control.jsp?action=list");
		} else {
			throw new Exception("failed to update");
		}
	} else if(action.equals("delete")) {
		if(ab.deleteDB(addrbook.getAb_id()) > 0){
			response.sendRedirect("addrbook_control.jsp?action=list");
		} else {
			throw new Exception("failed to delete");
		}
	} else {
		out.print("<script language='JavaScript'>alert('check action parameter')</script>");
	}
%>