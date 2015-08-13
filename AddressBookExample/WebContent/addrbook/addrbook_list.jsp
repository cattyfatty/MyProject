<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="addrbook_error.jsp" 
	import="java.util.*, example01.addrbook.*" %>
	
	
<jsp:useBean id="list" scope="request" class="java.util.ArrayList" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="addrbook.css" type="text/css"
	media="screen" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소록:목록화면</title>
<script language="JavaScript">
	function check(ab_id) {
		pwd = prompt('수정/삭제 하려면 비밀번호를 넣으세요');
		document.location.href="addrbook_control.jsp?action=edit&ab_id=" + ab_id
				+ "&upasswd="+pwd;
	}
</script>
</head>
<body>
	<div align="center">
		<H2>주소록:목록화면</H2>
		<HR>
		<form>
			<a href="addrbook_form.jsp">주소록 등록</a>

			<table border="1">
				<tr bgcolor="#99ccff">
					<th>번호</th>
					<th>이 름</th>
					<th>email</th>
					<th>전화번호</th>
					<th>생 일</th>
					<th>회 사</th>
					<th>메 모</th>
				</tr>
				<%
					for(AddrBook ab : (ArrayList<AddrBook>)list) {
				%>
				<tr>
					<td><a href="javascript:check(<%=ab.getAb_id() %>)"><%=ab.getAb_id() %></a></td>
					<td><%=ab.getAb_name() %></td>
					<td><%=ab.getAb_email() %></td>
					<td><%=ab.getAb_tel() %></td>
					<td><%=ab.getAb_birth() %></td>
					<td><%=ab.getAb_comdept() %></td>
					<td><%=ab.getAb_memo() %></td>
				</tr>
				<%} %>
			</table>
		</form>
	</div>


</body>
</html>