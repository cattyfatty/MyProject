<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="ms_signin">
	<form class="ms_form" name="sign_in" method="post" action="">
		<p class="fieldset">
			<label class="image_replace ms_id" for="signin_id">ID</label>
			<input class="full_width has_padding has_border" id="signin_id" type="text" placeholder="ID"/>
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<label class="image_replace ms_pw" for="signin_pw">Password</label>
			<input class="full_width has_padding has_border" id="signin_pw" type="password" placeholder="Password"/>
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<input type="checkbox" id="remember_me"/>
			<label for="remember_me">Remember me</label>
		</p>
		<p class="fieldset">
			<input class="full_width" type="submit" value="SIGN IN"/>
		</p>
	</form>
	<p class="ms_form_bottom_msg"><a href="#0">Forgot your password?</a></p>
</div>