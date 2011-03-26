package au.com.shopusa.model

import grails.test.*

class UserTests extends GrailsUnitTestCase {
	
    protected void setUp() {
        super.setUp()
		mockDomain(User)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreate() {

		def user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		user.save()
		
		assertEquals 1, User.count()
    }
}
