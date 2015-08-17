<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Error</title>
	</head>
	<jsp:useBean id="now" class="java.util.Date" />
	<body>
		<div align = "center">
			<h2>Error Occured!</h2>
			<hr>
			
			<table>
				<tr bgcolor="pink">
					<td>관리자에게 문의해 주세요...<br>
					빠른 시일내 복구하겠습니다.</td>
				</tr>
				<tr>
					<td>
						${now}
						<p>요청 실패 URI: ${pageContext.errorData.requestURI}<br>
						상태코드: ${pageContext.errorData.statusCode}<br>
						예외유형: ${pageContext.errorData.throwable }
						</p>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>