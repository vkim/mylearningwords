package au.com.shopusa.model

import grails.test.*

class OrderItemTests extends GrailsUnitTestCase {
	
	def order
	
    protected void setUp() {
        super.setUp()
		mockDomain(OrderItem)
		mockDomain(ShipOrder)
		mockDomain(User)
		
		def user = new User(email: 'email@com.ru', password: 'qioewprqer', shippingAddress: new ShippingAddress())
		order = new ShipOrder(client: user, shippingInfo: new ShippingInfo())
		order.save()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveItself() {

		def item = new OrderItem(name: 'simple tapki', shipOrder: order)
		item.save()
		
		assertEquals(1, OrderItem.count())
    }
	
	void testGetAllItemsByOrder() {
		
		OrderItem.metaClass.static.findAll = { query, array ->
			assertEquals("from OrderItem as i where i.shipOrder = ?", query)
			assertEquals([order], array);
			return [new OrderItem(name: 'item1', shipOrder: order)
			,new OrderItem(name: 'item2', shipOrder: order)]
		}
		
		assertEquals(2, OrderItem.findAll("from OrderItem as i where i.shipOrder = ?", [order]).size() )
	}
}
