<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="project.member.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<!DOCTYPE html>
<html class="no-js">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>KOSA Group Project</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/style.css">
		
		<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/prefixfree.min.js"></script>
		<script type="text/javascript" src="js/custom.js"></script>
		<script type="text/javascript" src="js/select.js"></script>
	</head>
	<body class="main">
		<!-- Logo & Menu -->
		<header role="banner">
			<div id="main_logo">
				<h1 class="logo"><a href="#0">I WANTED TO SEE THE UNIVERSE</a></h1>
				<span class="logo">-so I stole a time-lord</span>
			</div>
			
			<nav class="main_nav">
				<ul>
					<tag:signup_menu/>
				</ul>
			</nav>
		</header>
		
		<!-- Login Modal -->
		<div class="ms_user_modal">
			<div class="ms_user_modal_container">
				<tag:sign_in/>
				<tag:sign_up/>
				<tag:reset_pw/>
				<tag:edit_user/>
			</div>
		</div>
		
		<div class="bg_container">
			<!-- <img class="background" src="img/bg.png" alt=""/> -->
			<!-- <img class="galaxy" id="glx1" src="img/galaxy2.png" />
			<img class="galaxy" id="glx2" src="img/galaxy3.png" />
			<img class="background galaxy" id="glx3" src="img/galaxy4.png" />
			<img class="background galaxy" id="glx4" src="img/galaxy5.png" />
			<img class="background galaxy" id="glx5" src="img/galaxy6.png" /> -->
		</div>
		
		<div class="content">
			<jsp:include page="board_list.jsp"/>
		</div>
	</body>
</html>