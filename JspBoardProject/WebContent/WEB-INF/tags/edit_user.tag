<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="ms_edit">
	<ul class="ms_switcher">
		<li><a href="#0">Change User Name</a></li>
		<li><a href="#0">Change Password</a></li>
		<li><a class="selected" href="#0">Change Group</a></li>
	</ul>
	
	<div id="ms_edit_un">
		<form class="ms_form" name="edit_userName" method="post" action="">
			<p class="fieldset">
				<label class="image_replace ms_id" for="edit_id">User Name</label> 
				<input class="full_width has_padding has_border" id="edit_id" type="text" placeholder="User Name" /> 
				<span class="ms_error_msg">Error</span>
			</p>
			<p class="fieldset">
				<input class="full_width" type="submit" value="CHANGE USER NAME" />
			</p>
		</form>
	</div>
	<div id="ms_edit_pw">
		<form class="ms_form" name="edit_pw" method="post" action="">
			<p class="fieldset">
				<label class="image_replace ms_pw" for="edit_pw">User Name</label> 
				<input class="full_width has_padding has_border" id="edit_pw" type="text" placeholder="Password" /> 
				<span class="ms_error_msg">Error</span>
			</p>
			<p class="fieldset">
				<label class="image_replace ms_pw" for="edit_cpw">User Name</label> 
				<input class="full_width has_padding has_border" id="edit_cpw" type="text" placeholder="Confirm Password" /> 
				<span class="ms_error_msg">Error</span>
			</p>
			<p class="fieldset">
				<input class="full_width" type="submit" value="CHANGE PASSWORD" />
			</p>
		</form>
	</div>
	<div id="ms_edit_grp" class="is_selected">
		<form class="ms_form" name="edit_group" method="post" action="">
			<p class="fieldset">
				<label class="ms_select_label">KOSA SW Engineer Course Group:</label>
				<select class="ms_select">
					<option value="0">Please select your group: </option>
					<option value="1">Group 1</option>
					<option value="2">Group 2</option>
					<option value="3">Group 3</option>
					<option value="4">Group 4</option>
					<option value="5">Group 5</option>
				</select>
			</p>
			<p class="fieldset">
				<input class="full_width" type="submit" value="CHANGE GROUP" />
			</p>
		</form>
	</div>
</div>