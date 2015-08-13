<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="addrbook_error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="addrbook.css" type="text/css"
	media="screen" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소록:목록화면</title>
</head>
<body>
	<div align="center">
		<H2>주소록:목록화면</H2>
		<HR>
		<form>
			<a href="addrbook_form.jsp">주소록 등록</a>

			<table border="1">
				<tr>
					<th>번호</th>
					<th>이 름</th>
					<th>전화번호</th>
					<th>생 일</th>
					<th>회 사</th>
					<th>메 모</th>
				</tr>
				<tr>
					<td><a href="addrbook_edit_form.jsp">1</a></td>
					<td>홍길동</td>
					<td>010-1234-1234</td>
					<td>1989-10-10</td>
					<td>(주)가나다</td>
					<td>사원</td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>