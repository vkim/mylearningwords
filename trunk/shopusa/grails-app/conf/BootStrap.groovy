import grails.util.Environment
import au.com.shopusa.model.Role
import au.com.shopusa.model.ShippingAddress
import au.com.shopusa.model.User
import au.com.shopusa.model.UserRole

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		switch(Environment.current) {

			case Environment.TEST:
				createIntegrationTestData()
				break

			case Environment.DEVELOPMENT:
			    createIntegrationTestData()
				break

			case Environment.PRODUCTION:
				break
		}

	}
	def destroy = {
	}


	void createIntegrationTestData() {

		//Security and users
		//test users
		def clientRole = new Role(authority: Role.ROLE_CLIENT).save(flush: true)
		def adminRole = new Role(authority: Role.ROLE_ADMIN).save(flush: true)

		assert Role.count() == 2

		//expert test user
		String password = springSecurityService.encodePassword('password')
		def testUser = new User(enabled: true, password: password, email: 'admin@com.au', shippingAddress: new ShippingAddress())
		testUser.save(flush: true)

		assert User.count() == 1

		UserRole.create testUser, adminRole, true
		UserRole.create testUser, clientRole, true

		assert UserRole.count() == 2

		//normal user
		def normalUser = new User(enabled: true, password: password, email: 'normal@com.au', shippingAddress: new ShippingAddress())
		normalUser.save(flush: true)
		assert User.count() == 2

		UserRole.create normalUser, clientRole, true
		assert UserRole.count() == 3
		
		println '####################### Data has been loaded ####################'
	}

}
