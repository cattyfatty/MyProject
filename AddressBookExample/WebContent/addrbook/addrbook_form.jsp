<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소록:작성화면</title>
<link rel="stylesheet" href="addrbook.css" type="text/css"
	media="screen" />
</head>
<body>
	<div align="center">
		<H2>주소록:작성화면</H2>
		<hr>
		[<a href="addrbook_list.jsp">주소록 목록으로</a>]
		<form name="form1" method="post" action="addrbook_control.jsp">
			<table border="1">
				<tr>
					<th>이 름</th>
					<td><input type="text" name="ab_name" maxlength="15"></td>
				</tr>
				<tr>
					<th>email</th>
					<td><input type="text" name="ab_email" maxlength="50"></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="ab_tel" maxlength="20"></td>
				</tr>
				<tr>
					<th>생 일</th>
					<td><input type="date" name="ab_birth"></td>
				</tr>
				<tr>
					<th>회 사</th>
					<td><input type="text" name="ab_comdepth" maxlength="20"></td>
				</tr>
				<tr>
					<th>메 모</th>
					<td><input type="text" name="ab_memo"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="저장">
						<input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>