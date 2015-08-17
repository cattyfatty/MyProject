<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>404-error</title>
	</head>
	<jsp:useBean id="now" class="java.util.Date" />
	<body>
		<div align="center">
			<h1>404 NOT FOUND ERROR</h1>
			<hr>
			
			<table>
				<tr bgcolor="pink">
					<td>
						요청하신 파일을 찾을 수 없습니다.<br>
						URL 주소를 다시 확인해 주세요
					</td>
				</tr>
				<tr>
					<td>
						${now}<p>
						요청실패 URI: ${pageContext.errorData.requestURI }<br>
						상태코드: ${pageContext.errorData.statusCode }<br>
						</p>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>