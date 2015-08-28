<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="ms_signup">
	<form class="ms_form" name="sign_up" method="post" action="user_control.jsp?action=new">
		<p class="fieldset">
			<label class="image_replace ms_email" for="signup_email">E-mail</label> 
			<input class="full_width has_padding has_border" id="signup_email" type="text" name="member_email" placeholder="E-mail(:this will be your login id)" />
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<label class="image_replace ms_id" for="signup_id">Nick Name</label> 
			<input class="full_width has_padding has_border" id="signup_id" type="text" name="member_id" placeholder="Nick Name" /> 
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<label class="image_replace ms_pw" for="signup_pw">Password</label> 
			<input class="full_width has_padding has_border" id="signup_pw" type="password" name="member_password" placeholder="Password" />
			<span class="ms_error_msg">Error</span>
		</p>
		<p class="fieldset">
			<label class="ms_select_label">KOSA SW Engineer Course Group:</label>
			<select name="group_id" class="ms_select">
				<option value="0">Please select your group: </option>
				<option value="1">Group 1</option>
				<option value="2">Group 2</option>
				<option value="3">Group 3</option>
				<option value="4">Group 4</option>
				<option value="5">Group 5</option>
			</select>
		</p>
		<p class="fieldset">
			<input class="full_width" type="submit" value="CREATE ACCOUNT" />
		</p>
	</form>
</div>