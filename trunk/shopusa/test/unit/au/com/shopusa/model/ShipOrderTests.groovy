package au.com.shopusa.model

import grails.test.*

class ShipOrderTests extends GrailsUnitTestCase {
	
    protected void setUp() {
        super.setUp()
		
		mockDomain(User)
		mockDomain(ShipOrder)
		
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreate() {

		def user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		user.save()
		
		def order = new ShipOrder(client: user, shippingInfo: new ShippingInfo())
		order.save()
		
		assertEquals 1, ShipOrder.count()
    }
	
	void testGetAllOrdersByUser() {
		def user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		user.save()
		
		mockDomain(ShipOrder, [new ShipOrder(client: user, shippingInfo: new ShippingInfo())
			,new ShipOrder(client: user, shippingInfo: new ShippingInfo())
			,new ShipOrder(client: user, shippingInfo: new ShippingInfo()) ])
		
		assertEquals(3, ShipOrder.findAllByClient(user).size())
	}
}
