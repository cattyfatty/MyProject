jQuery(document).ready(function($){
	var $form_modal = $('.ms_user_modal'),
		$form_signin = $form_modal.find('#ms_signin'),
		$form_signup = $form_modal.find('#ms_signup'),
		$form_forgot_pw = $form_modal.find('#ms_reset_pw'),
		$forgot_pw_link = $form_signin.find('.ms_form_bottm_msg a'),
		$back_to_signin_link = $form_forgot_pw.find('.ms_from_bottom_msg a'),
		$main_nav = $('.main_nav');
	
	//open modal
	$main_nav.on('click', function(event) {
		$form_modal.addClass('is_visible');
		($(event.target).is('.mn_signin')) ? signin_selected() : signup_selected();
	});
	//close modal
	$('.ms_user_modal').on('click', function(event) {
		if($(event.target).is($form_modal) || $(event.target).is('ms_close_form')) {
			$form_modal.removeClass('is_visible');
		}
	});
	//close modal with esc button
	$(document).keyup(function(event) {
		if(event.which=='27') {
			$form_modal.removeClass('is_visible');
		}
	});
	
	//show forgot-password form
	$forgot_pw_link.on('click', function(event){
		event.preventDefault();
		forget_pw_selected();
	});
	//back to login form
	$back_to_signin_link.on('click', function(event){
		event.preventDefault();
		signin_selected();
	});
	
	//functions
	function signin_selected() {
		$form_signin.addClass('is_selected');
		$form_signup.removeClass('is_selected');
		$form_forgot_pw.removeClass('is_selected');
	}
	function signup_selected() {
		$form_signin.removeClass('is_selected');
		$form_signup.addClass('is_selected');
		$form_forgot_pw.removeClass('is_selected');
	}
	function forget_pw_selected() {
		$form_signin.removeClass('is_selected');
		$form_signup.removeClass('is_selected');
		$form_forgot_pw.addClass('is_selected');
	}
});