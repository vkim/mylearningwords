package au.com.shopusa.web

import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode

import au.com.shopusa.model.ShippingAddress
import au.com.shopusa.model.User


class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {

	static defaultAction = 'index'
	
	def index = {
		
		[command: new RegisterCommand()]
	}
	
	def register = { RegisterCommand command ->

		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}

		String salt = saltSource instanceof NullSaltSource ? null : command.username
		String password = springSecurityService.encodePassword(command.password, salt)

		def user = lookupUserClass().newInstance(username: command.username,
				password: password, accountLocked: true, enabled: true,
				shippingAddress: command.shippingAddress)

		if (!user.validate() || !user.save()) {

		}
		else {

			def registrationCode = new RegistrationCode(username: user.username).save()
			String url = generateLink('verifyRegistration', [t: registrationCode.token])

			def conf = SpringSecurityUtils.securityConfig
			def body = conf.ui.register.emailBody
			if (body.contains('$')) {
				body = evaluate(body, [user: user, url: url])
			}

			mailService.sendMail {
				to command.username
				from conf.ui.register.emailFrom
				subject conf.ui.register.emailSubject
				html body.toString()
			}
		}

		render view: 'index', model: [emailSent: true]
	}


	def verifyRegistration = {

		def conf = SpringSecurityUtils.securityConfig
		String defaultTargetUrl = conf.successHandler.defaultTargetUrl

		String token = params.t

		def registrationCode = token ? RegistrationCode.findByToken(token) : null
		if (!registrationCode) {
			
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect(action:'index')
			return
		}

		def user
		RegistrationCode.withTransaction { status ->
			user = lookupUserClass().findByUsername(registrationCode.username)
			if (!user) {
				return
			}
			user.accountLocked = false
			user.save()
			def UserRole = lookupUserRoleClass()
			def Role = lookupRoleClass()
			for (roleName in conf.ui.register.defaultRoleNames) {
				UserRole.create user, Role.findByAuthority(roleName)
			}
			registrationCode.delete()
		}

		if (!user) {
			flash.error = message(code: 'spring.security.ui.register.badCode')
			redirect action:'index'
			return
		}

		springSecurityService.reauthenticate user.username

		flash.message = message(code: 'spring.security.ui.register.complete')
		redirect uri: conf.ui.register.postRegisterUrl ?: defaultTargetUrl
	}
}

class RegisterCommand {

	String username 
	String password
	String password2
	ShippingAddress shippingAddress = new ShippingAddress()

	static constraints = {
		username blank: false, email: true, validator: { value, command ->
			if (value) {
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		password blank: false, minSize: 6, maxSize: 64 
		password2 validator: RegisterController.password2Validator
	}
}

class ResetPasswordCommand {
	String username
	String password
	String password2

	static constraints = {
		password blank: false, minSize: 6, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
	}
}
