jQuery(document).ready(function($) {
	var $form_modal = $('.ms_user_modal'), 
		$form_signin = $form_modal.find('#ms_signin'),
		$form_signup = $form_modal.find('#ms_signup'),
		$form_forgot_pw = $form_modal.find('#ms_reset_pw'),
		$form_edit_user = $form_modal.find('#ms_edit'),
		$form_edit_un = $form_edit_user.find('#ms_edit_un'),
		$form_edit_pw = $form_edit_user.find('#ms_edit_pw'),
		$form_edit_grp = $form_edit_user.find('#ms_edit_grp'),
		$form_modal_tab = $('.ms_switcher'),
		$tab_edit_name = $form_modal_tab.children('li').eq(0).children('a'),
		$tab_edit_pw = $form_modal_tab.children('li').eq(1).children('a'),
		$tab_edit_grp = $form_modal_tab.children('li').eq(2).children('a'),
		$forgot_pw_link = $form_signin.find('.ms_form_bottom_msg a'),
		$back_to_signin_link = $form_forgot_pw.find('.ms_form_bottom_msg a'),
		$main_nav = $('.main_nav');

	// open modal
	$main_nav.on('click', function(event) {
		$form_modal.addClass('is_visible');
		if($(event.target).is('.mn_signin')) {
			signin_selected();
		} else if($(event.target).is('.mn_signup')) {
			signup_selected();
		} else if($(event.target).is('.mn_edit')) {
			edit_user_selected();
		}
	});
	// close modal
	$('.ms_user_modal').on('click', function(event) {
		if ($(event.target).is($form_modal) || $(event.target).is('ms_close_form')) {
			$form_modal.removeClass('is_visible');
		}
	});
	// close modal with esc button
	$(document).keyup(function(event) {
		if (event.which == '27') {
			$form_modal.removeClass('is_visible');
		}
	});
	
	//switch tab to tab
	$form_modal_tab.on('click', function(event){
		event.preventDefault();
		if($(event.target).is($tab_edit_name)) {
			edit_un();
		} else if($(event.target).is($tab_edit_pw)) {
			edit_pw();
		} else {
			edit_grp();
		}
	});

	// show forgot-password form
	$forgot_pw_link.on('click', function(event) {
		event.preventDefault();
		forget_pw_selected();
	});
	// back to login form
	$back_to_signin_link.on('click', function(event) {
		event.preventDefault();
		signin_selected();
	});

	// functions
	function signin_selected() {
		$form_signin.addClass('is_selected');
		$form_signup.removeClass('is_selected');
		$form_forgot_pw.removeClass('is_selected');
		$form_edit_user.removeClass('is_selected');
		reset_tabs();
	}
	function signup_selected() {
		$form_signin.removeClass('is_selected');
		$form_signup.addClass('is_selected');
		$form_forgot_pw.removeClass('is_selected');
		$form_edit_user.removeClass('is_selected');
		reset_tabs();
	}
	function forget_pw_selected() {
		$form_signin.removeClass('is_selected');
		$form_signup.removeClass('is_selected');
		$form_forgot_pw.addClass('is_selected');
		$form_edit_user.removeClass('is_selected');
		reset_tabs();
	}
	function edit_user_selected() {
		$form_signin.removeClass('is_selected');
		$form_signup.removeClass('is_selected');
		$form_forgot_pw.removeClass('is_selected');
		$form_edit_user.addClass('is_selected');
		edit_un();
	}
	function reset_tabs() {
		$form_edit_un.removeClass('is_selected');
		$form_edit_pw.removeClass('is_selected');
		$form_edit_grp.removeClass('is_selected');
		$tab_edit_name.removeClass('selected');
		$tab_edit_pw.removeClass('selected');
		$tab_edit_grp.removeClass('selected');
	}
	function edit_un() {
		$form_edit_un.addClass('is_selected');
		$form_edit_pw.removeClass('is_selected');
		$form_edit_grp.removeClass('is_selected');
		$tab_edit_name.addClass('selected');
		$tab_edit_pw.removeClass('selected');
		$tab_edit_grp.removeClass('selected');
	}
	function edit_pw() {
		$form_edit_un.removeClass('is_selected');
		$form_edit_pw.addClass('is_selected');
		$form_edit_grp.removeClass('is_selected');
		$tab_edit_name.removeClass('selected');
		$tab_edit_pw.addClass('selected');
		$tab_edit_grp.removeClass('selected');
	}
	function edit_grp() {
		$form_edit_un.removeClass('is_selected');
		$form_edit_pw.removeClass('is_selected');
		$form_edit_grp.addClass('is_selected');
		$tab_edit_name.removeClass('selected');
		$tab_edit_pw.removeClass('selected');
		$tab_edit_grp.addClass('selected');
	}
});