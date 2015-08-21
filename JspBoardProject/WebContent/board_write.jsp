<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/board_style.css">
		
		<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/prefixfree.min.js"></script>
	</head>
	<body>
		<div class="board_container">
			<div id="content">
				<form class="ms_form" name="board_write" method="post" action="">
					<p class="fieldset">
						<label class="write_title" for="ba_title">Title:</label>
						<input class="full_width has_border has_padding" id="ba_title" type="text" placeholder="Enter your title here" />
					</p>
					<p class="fieldset">
						<textarea class="full_width has_border has_padding"></textarea>
					</p>
					<hr/>
					<p class="fieldset">
						<label class="write_tag" for="ba_tags">Tags:</label>
						<input type="checkbox" name="ba_tags" value="1"/>java
						<input type="checkbox" name="ba_tags" value="2"/>jsp
						<input type="checkbox" name="ba_tags" value="3"/>jQuery
						<input type="checkbox" name="ba_tags" value="4"/>javascript
						<input type="checkbox" name="ba_tags" value="5"/>sql
						<input type="checkbox" name="ba_tags" value="6"/>html
						<input type="checkbox" name="ba_tags" value="7"/>css
						<input type="checkbox" name="ba_tags" value="8"/>spring
					</p>
					<p class="fieldset">
						<input class="full_width" type="submit" value="Post your Thread" />
					</p>
				</form>     
			</div>
		</div>
	</body>
</html>