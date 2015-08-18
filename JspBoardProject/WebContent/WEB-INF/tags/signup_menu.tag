<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${uid != null }">
		<li><a class="mn_edit" href="#"><i class="fa fa-cog"></i>${uid}</a></li>
		<li>/</li>
		<li><a class="mn_signout" href="#"><i class="fa fa-sign-out"></i>SIGN OUT</a></li>
	</c:when>
	<c:otherwise>
		<li><a class="mn_signin" href="#"><i class="fa fa-sign-in"></i>SIGN IN</a></li>
		<li>/</li>
		<li><a class="mn_signup" href="#">SIGN UP</a></li>
	</c:otherwise>
</c:choose>
<li><i class="fa fa-facebook-square"></i></li>
<li><i class="fa fa-google-plus-square"></i></li>
<li><i class="fa fa-twitter-square"></i></li>