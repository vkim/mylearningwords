<head>
<title><g:message code='spring.security.ui.login.title'/></title>
<meta name='layout' content='main'/>
<g:javascript library='jquery' plugin='jquery' />
<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'reset.css')}"/>
	<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'spring-security-ui.css')}"/>
	<jqui:resources />
	<link rel="stylesheet" media="screen" href="${resource(dir:'css/smoothness',file:'jquery-ui-1.8.2.custom.css',plugin:'spring-security-ui')}"/>
	<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.jgrowl.css')}"/>
	<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'jquery.safari-checkbox.css')}"/>
	<link rel="stylesheet" media="screen" href="${resource(dir:'css',file:'auth.css')}"/>
</head>

<body>

<p/>

<div class="login s2ui_center ui-corner-all" style='text-align:center;'>
	<div class="login-inner">
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
	<fieldset>
	<h1><g:message code='spring.security.ui.login.signin'/></h1>

	<table>
		<tbody>
			<tr>
				<td><label for="username"><g:message code='user.email.label'/></label></td>
				<td><input class="text" type="text" name="j_username" id="username" size="20" /></td>
			</tr>
			<tr>
				<td><label for="password"><g:message code='spring.security.ui.login.password'/></label></td>
				<td><input class="text" type="password" name="j_password" id="password" size="20" /></td>
			</tr>
			<tr>
				<td colspan='2'>
					<input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
					<label for='remember_me'><g:message code='spring.security.ui.login.rememberme'/></label> |
					<span class="forgot-link">
						<g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan='2'>
					<s2ui:linkButton elementId='register' controller='register' messageCode='spring.security.ui.login.register'/>
				</td>
			</tr>
		</tbody>
	</table>
		<s2ui:submitButton elementId='loginButton' form='loginForm' messageCode='spring.security.ui.login.login'/>

		</fieldset>
	</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#username').focus();
});

<s2ui:initCheckboxes/>

</script>

</body>
