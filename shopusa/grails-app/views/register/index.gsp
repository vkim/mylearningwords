<head>
	<meta name='layout' content='main'/>
	<title><g:message code='spring.security.ui.register.title'/></title>
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

<g:form action='register' name='registerForm'>
	<fieldset>
	<g:if test='${emailSent}'>
	<br/>
	<g:message code='spring.security.ui.register.sent'/>
	</g:if>
	<g:else>
	
	<g:if test="${flash.error}">
     	<div class="message">${flash.error}</div>
    </g:if>

	<br/>

	<table>
		<tbody>
	
				<s2ui:textFieldRow name='username' class="text" bean="${command}" value="${command.username}"
		                   size='40' labelCode='user.email.label' labelCodeDefault='E-mail'/>
		                   
		        <s2ui:passwordFieldRow name='password' class="text" labelCode='user.password.label' bean="${command}"
                             size='40' labelCodeDefault='Password' value="${command.password}"/>

				<s2ui:passwordFieldRow name='password2' class="text" labelCode='user.password2.label' bean="${command}"
		                             size='40' labelCodeDefault='Password (again)' value="${command.password2}"/>
	
		</tbody>
	</table>

	<s2ui:submitButton elementId='create' form='registerForm' messageCode='spring.security.ui.register.submit'/>

	</g:else>
	</fieldset>

</g:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
