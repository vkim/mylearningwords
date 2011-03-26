package au.com.shopusa.model

import grails.test.*


class UserIntegrationTests extends GroovyTestCase {
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreate() {
		
		def count = User.count()
		
		def user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		
		user.save()

		assertEquals(count + 1, User.count())
    }
}
