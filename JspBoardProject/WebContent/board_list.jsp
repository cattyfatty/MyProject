<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="css/board_style.css">
	</head>
	<body class="board_list">
		<div class="board_container">
			<div id="content">
				<div class="header">
					<h1>Questions &amp; Talks</h1>
				</div>
				<div id="list_wrapper">
					<div id="lists">
						<%/* some java code here or some tags */ %>
						<tag:board_list_summary/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>