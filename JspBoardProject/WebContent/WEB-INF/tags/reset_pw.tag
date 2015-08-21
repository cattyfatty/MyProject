<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="ms_reset_pw">
	<p class="ms_form_msg">
		Lost your password? Please enter your email address. You will redirected to reset-password page.
	</p>
	<form class="ms_form" name="reset_pw" method="post" action="">
		<p class="fieldset">
			<label class="image_replace ms_email" for="reset_pw_email">E-mail</label>
			<input class="full_width has_padding has_border" id="reset_pw_email" type="text" placeholder="E-mail"/>
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<input class="full_width" type="submit" value="RESET PASSWORD"/>
		</p>
	</form>
	<p class="ms_form_bottom_msg"><a href="#0">Back to Sign-in</a></p>
</div>